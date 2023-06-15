package Pack01.repository;

import Pack01.domain.Problem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProblemRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProblemRepository(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    //전체 조회
//    public List<Problem> selectAll() {
//
//    }

}
