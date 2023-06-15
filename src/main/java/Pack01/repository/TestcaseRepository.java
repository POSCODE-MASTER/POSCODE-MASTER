package Pack01.repository;

import Pack01.domain.Testcase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class TestcaseRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TestcaseRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // testcase 저장
    public Testcase save(Testcase testcase) {
        String sql = "INSERT INTO testcase (problem_id, input, output) VALUES (?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"testcase_id"});
            ps.setLong(1, testcase.getProblemId());
            ps.setString(2, testcase.getInput());
            ps.setString(3, testcase.getOutput());
            return ps;
        }, keyHolder);

        long generatedId = keyHolder.getKey().longValue();
        testcase.setTestcaseId(generatedId);

        return testcase;
    }




    // problem_id로 testcase 리스트 조회
    public List<Testcase> findByProblemId(Long problemId) {
        String sql = "SELECT * FROM testcase WHERE problem_id = ?";
        return jdbcTemplate.query(sql, testcaseRowMapper(), problemId);
    }

    private RowMapper<Testcase> testcaseRowMapper() {
        return (rs, rowNum) -> {
            Testcase testcase = new Testcase();
            testcase.setTestcaseId(rs.getLong("testcase_id"));
            testcase.setProblemId(rs.getLong("problem_id"));
            testcase.setInput(rs.getString("input"));
            testcase.setOutput(rs.getString("output"));
            return testcase;
        };
    }




    // testcase 수정
    public void updateTestcase(Long testcaseId, Testcase testcase) {
        String sql = "UPDATE testcase SET input = ?, output = ? WHERE testcase_id = ?";
        jdbcTemplate.update(sql, testcase.getInput(), testcase.getOutput(), testcaseId);
    }





}
