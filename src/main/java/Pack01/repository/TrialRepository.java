package Pack01.repository;

import Pack01.domain.Trial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;

@Repository
public class TrialRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TrialRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //저장
    public Trial save(Trial trial) {
        String sql = "INSERT INTO trial (testcase_id, user_id, is_solved, memory, cpu_time, output, code, solve_time) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"trial_id"});
            ps.setLong(1, trial.getTestcaseId());
            ps.setLong(2, trial.getUserId());
            ps.setBoolean(3, trial.getSolved());
            ps.setLong(4, trial.getMemory());
            ps.setDouble(5, trial.getCpuTime());
            ps.setString(6, trial.getOutput());
            ps.setString(7, trial.getCode());
            ps.setObject(8, trial.getSolveTime());
            return ps;
        }, keyHolder);

        // 채워진 PK를 다시 User 객체에 설정
        long key = keyHolder.getKey().longValue();
        trial.setTrialId(key);

        return trial;
    }


}
