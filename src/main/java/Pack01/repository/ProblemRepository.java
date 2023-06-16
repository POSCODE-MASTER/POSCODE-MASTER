package Pack01.repository;

import Pack01.domain.Problem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProblemRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProblemRepository(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    // problem 저장
    public Problem save(Problem problem){
        String sql = "INSERT INTO problem (user_id, title, description, written_date, update_date, level) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"problem_id"});
            ps.setLong(1, problem.getUserId());
            ps.setString(2, problem.getTitle());
            ps.setString(3, problem.getDescription());
            ps.setObject(4, problem.getWrittenDate());
            ps.setObject(5, problem.getUpdateDate());
            ps.setInt(6, problem.getLevel());
            return ps;
        }, keyHolder);

        long generatedId = keyHolder.getKey().longValue();
        problem.setProblemId(generatedId);

        return problem;

    }



    // userId로 problem 리스트 출력 (자신이 출제한 problem들 출력)
    public List<Problem> findByUserId(Long userId) {
        String sql = "SELECT * FROM problem WHERE user_id = ?";

        return jdbcTemplate.query(sql, problemRowMapper(), userId);
    }

    // problem 수정
    public void updateProblem(Long updateProblemId, Problem problem) {
        String sql = "UPDATE problem SET title = ?, description = ?, update_date = ?, level = ? WHERE problem_id = ?";
        jdbcTemplate.update(sql, problem.getTitle(), problem.getDescription(), problem.getUpdateDate(), problem.getLevel(), updateProblemId);
    }



    // 전체 조회 (페이징 + level 필터)
    public List<Problem> selectAll(int page, String level) {
        int pageSize = 9;
        int offset = (page - 1) * pageSize; // 페이지의 시작 오프셋 계산

        StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM problem");
        List<Object> params = new ArrayList<>();

        //레벨 필터
        if (level != null && !level.trim().isEmpty()) {
            sqlBuilder.append(" WHERE level = ?");
            params.add(level);
        }

        //페이징
        sqlBuilder.append(" LIMIT ? OFFSET ?");
        params.add(pageSize);
        params.add(offset);

        String sql = sqlBuilder.toString();
        return jdbcTemplate.query(sql, problemRowMapper(), params.toArray());
    }

    private RowMapper<Problem> problemRowMapper() {
        return (rs, rowNum) -> {
            Problem problem = new Problem();
            problem.setProblemId(rs.getLong("problem_id"));
            problem.setUserId(rs.getLong("user_id"));
            problem.setTitle(rs.getString("title"));
            problem.setDescription(rs.getString("description"));
            problem.setWrittenDate(rs.getObject("written_date", LocalDateTime.class));
            problem.setUpdateDate(rs.getObject("update_date", LocalDateTime.class));
            problem.setLevel((rs.getInt("level")));
            return problem;
        };
    }

    //내가 시도한 문제 리스트
    public List<Problem> getSolvedProblems(long userId) {
        String sql = "SELECT DISTINCT p.problem_id, p.title " +
                "FROM problem AS p, testcase AS te, trial AS tr, user AS u " +
                "WHERE p.problem_id = te.problem_id " +
                "AND te.testcase_id = tr.testcase_id " +
                "AND tr.user_id = u.user_id " +
                "AND u.user_id = ?";

        return jdbcTemplate.query(sql, solvedProblemRowMapper(), userId);
    }

    private RowMapper<Problem> solvedProblemRowMapper() {
        return (rs, rowNum) -> {
            Problem problem = new Problem();
            problem.setProblemId(rs.getLong("problem_id"));
            problem.setTitle(rs.getString("title"));
            return problem;
        };
    }

}
