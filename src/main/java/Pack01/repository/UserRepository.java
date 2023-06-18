package Pack01.repository;

import Pack01.domain.User;
import Pack01.repository.dto.ProblemHistoryDto;
import Pack01.repository.dto.SolvedProblemDto;
import Pack01.repository.dto.UserTrialDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 저장
    public User save(User user) {
        String sql = "INSERT INTO user (id, password, level, name, role) VALUES (?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"user_id"});
            ps.setString(1, user.getId());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getLevel());
            ps.setString(4, user.getName());
            ps.setString(5, user.getRole());
            return ps;
        }, keyHolder);

        // 채워진 PK를 다시 User 객체에 설정
        long key = keyHolder.getKey().longValue();
        user.setUserId(key);

        return user;
    }

    //id로 조회
    public Optional<User> findById(String id) {
        String sql = "SELECT * FROM user WHERE id = ?";

        try {
            User user = jdbcTemplate.queryForObject(sql, userRowMapper(), id);

            return Optional.of(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }

    }

    private RowMapper<User> userRowMapper() {
        return (rs, rowNum) -> {
            User user = new User();
            user.setUserId(rs.getLong("user_id"));
            user.setId(rs.getString("id"));
            user.setPassword(rs.getString("password"));
            user.setLevel(rs.getString("level"));
            user.setName(rs.getString("name"));
            user.setRole(rs.getString("role"));
            return user;
        };
    }


    // userId(PK)로 조회
    public User findByUserId(Long userId) {
        String sql = "SELECT * FROM user WHERE user_id = ?";

        try {
            return jdbcTemplate.queryForObject(sql, userRowMapper(), userId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }


    // 내가 맞은 문제 리스트
    public List<SolvedProblemDto> selectSolvedProblem(Long userId){
        String query =
                "SELECT DISTINCT problem.*, correct_count.user_id AS solver " +
                "FROM (SELECT problem_id, solve_time, user_id, COUNT(*) AS correct_count " +
                "      FROM testcase AS te, trial AS tr " +
                "      WHERE te.testcase_id = tr.testcase_id " +
                "        AND tr.is_solved = 1 " +
                "        AND tr.user_id = ? " +
                "      GROUP BY problem_id, solve_time) AS correct_count, " +
                "(SELECT p.problem_id, COUNT(*) AS total_testcase " +
                " FROM problem p, testcase te " +
                " WHERE p.problem_id = te.problem_id " +
                " GROUP BY p.problem_id) AS total_testcase, problem " +
                "WHERE correct_count.correct_count = total_testcase.total_testcase " +
                "  AND correct_count.problem_id = total_testcase.problem_id " +
                "  AND correct_count.problem_id = problem.problem_id " +
                "  AND total_testcase.problem_id = problem.problem_id";

        return jdbcTemplate.query(query, new Object[]{userId}, (rs, rowNum) -> mapProblemDTO(rs));
    }


    //내가 시도했지만 맞추지 못한 문제
    public List<SolvedProblemDto> selectNotSolvedProblem(long userId){
        String query = "select DISTINCT p.*, u.user_id as solver\n" +
                "FROM problem as p,\n" +
                "    testcase as te,\n" +
                "    trial as tr, \n" +
                "     user as u\n" +
                "WHERE p.problem_id = te.problem_id\n" +
                "AND te.testcase_id = tr.testcase_id\n" +
                "AND tr.user_id = u.user_id\n" +
                "AND u.user_id = ?\n" +
                "AND p.problem_id NOT IN(\n" +
                "select DISTINCT problem.problem_id\n" +
                "from (\n" +
                "select problem_id, solve_time, count(*) as correct_count\n" +
                "from testcase as te, trial as tr\n" +
                "where te.testcase_id = tr.testcase_id\n" +
                "and tr.user_id = ?\n" +
                "and tr.is_solved = 1\n" +
                "group by problem_id, solve_time) as correct_count,\n" +
                "(select p.problem_id, count(*) total_testcase \n" +
                "from problem p, testcase te \n" +
                "where p.problem_id = te.problem_id\n" +
                " group by p.problem_id) as total_testcase,\n" +
                "problem \n" +
                "where correct_count.correct_count = total_testcase\n" +
                "and correct_count.problem_id = total_testcase.problem_id\n" +
                "and correct_count.problem_id = problem.problem_id\n" +
                "and total_testcase.problem_id = problem.problem_id\n" +
                ")";

        return jdbcTemplate.query(query, new Object[]{userId, userId}, (rs, rowNum) -> mapProblemDTO(rs));
    }

    public Integer findSolvedUserNumByProblemId(Long problemId){
        String query = "select count(DISTINCT correct_count.user_id) as correctUser\n" +
                "from (\n" +
                "select problem_id, user_id, solve_time, count(*) as correct_count\n" +
                "from testcase as te, trial as tr\n" +
                "where te.testcase_id = tr.testcase_id\n" +
                "and tr.is_solved = 1\n" +
                "and te.problem_id = ?\n" +
                "group by problem_id, user_id, solve_time) as correct_count,\n" +
                "(select p.problem_id, count(*) total_testcase \n" +
                "from problem p, testcase te \n" +
                "where p.problem_id = te.problem_id\n" +
                "and p.problem_id = ?\n" +
                "group by p.problem_id\n" +
                ") as total_testcase";

        return jdbcTemplate.queryForObject(query, new Object[]{problemId, problemId}, Integer.class);

    }

    public Integer findTriedUserNumByProblemId(Long problemId){
        String query = "select COUNT(distinct user_id) as triedUser from testcase, trial\n" +
                "where problem_id = ?\n" +
                "and testcase.testcase_id = trial.testcase_id";

        return jdbcTemplate.queryForObject(query, new Object[]{problemId}, Integer.class);

    }

    //유저가 해당 문제를 푼 기록들(로그)
    public List findUserProblemSolveLog(Long userId, Long problemId){
        String query = "select A.code as code ,A.solve_time as solveTime,\n" +
                "CASE \n" +
                "WHEN A.correct_count = B.total_testcase THEN 'SOLVED'\n" +
                " WHEN A.correct_count != B.total_testcase THEN 'NOT SOLVED'\n" +
                " END as result\n" +
                "from (\n" +
                "select problem_id, solve_time, tr.code,count(*) as correct_count\n" +
                "from testcase as te, trial as tr\n" +
                "where te.testcase_id = tr.testcase_id\n" +
                "and tr.user_id = ?\n" +
                "and tr.is_solved = 1\n" +
                "and te.problem_id = ?\n" +
                "group by problem_id, solve_time, tr.code) as A,\n" +
                "(select p.problem_id, count(*) total_testcase \n" +
                "from problem p, testcase te \n" +
                "where p.problem_id = te.problem_id\n" +
                " group by p.problem_id) as B,\n" +
                "problem \n" +
                "where A.problem_id = B.problem_id\n" +
                "and A.problem_id = problem.problem_id\n" +
                "and B.problem_id = problem.problem_id order by solveTime desc;\n";
        return jdbcTemplate.query(query, new Object[]{userId, problemId}, (rs, rowNum) -> mapUserTrialDto(rs));
    }



    private SolvedProblemDto mapProblemDTO(ResultSet rs) throws SQLException {
        SolvedProblemDto problemDTO = new SolvedProblemDto();
        problemDTO.setProblemId(rs.getLong("problem_id"));
        problemDTO.setUserId(rs.getLong("user_id"));
        problemDTO.setTitle(rs.getString("title"));
        problemDTO.setDescription(rs.getString("description"));
        problemDTO.setWrittenDate(rs.getObject("written_date", LocalDateTime.class));
        problemDTO.setUpdateDate(rs.getObject("update_date", LocalDateTime.class));
        problemDTO.setLevel(rs.getInt("level"));
        problemDTO.setSolver(rs.getLong("solver"));
        return problemDTO;
    }

    private UserTrialDto mapUserTrialDto(ResultSet rs) throws SQLException {
        UserTrialDto userTrialDto = new UserTrialDto(
                rs.getString("code"),
                rs.getObject("solveTime", LocalDateTime.class),
                rs.getString("result")
        );
        return userTrialDto;
    }







}
