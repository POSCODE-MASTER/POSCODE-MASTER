package Pack01.repository;

import Pack01.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
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
}
