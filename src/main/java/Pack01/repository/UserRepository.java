package Pack01.repository;

import Pack01.domain.User;
import Pack01.repository.dto.SolvedProblemDto;
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
                "SELECT problem.*, correct_count.user_id AS solver, correct_count.solve_time " +
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
        problemDTO.setSolveTime(rs.getObject("solve_time", LocalDateTime.class));
        return problemDTO;
    }





}
