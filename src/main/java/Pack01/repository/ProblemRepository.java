package Pack01.repository;

import Pack01.domain.Problem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ProblemRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProblemRepository(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    // 전체 조회 (페이징)
    public List<Problem> selectAll(int page) {
        int pageSize = 10;
        int offset = (page - 1) * pageSize; // 페이지의 시작 오프셋 계산

        String sql = "SELECT * FROM problem LIMIT ? OFFSET ?";
        return jdbcTemplate.query(sql, problemRowMapper(), pageSize, offset);
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
            return problem;
        };
    }

}
