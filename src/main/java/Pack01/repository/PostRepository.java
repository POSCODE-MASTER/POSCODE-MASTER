package Pack01.repository;

import Pack01.domain.Post;
import Pack01.repository.dto.PostAndUserName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class PostRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PostRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //save
    public void save(Post post) {
        String query = "INSERT INTO post (problem_id, user_id, title, content, written_date) " +
                "VALUES (?, ?, ?, ?, ?);";

        jdbcTemplate.update(
                query,
                post.getProblem_id(),
                post.getUser_id(),
                post.getTitle(),
                post.getContent(),
                String.valueOf(LocalDateTime.now())
        );

        System.out.println("Post 삽입 성공");
    }


    public void update(Post post, Long post_id) {
        String query = "UPDATE post SET title = ?, content = ? WHERE post_id = ?";
        jdbcTemplate.update(
                query,
                post.getTitle(),
                post.getContent(),
                post_id
        );

        System.out.println("Post 수정 성공");
    }

    public void delete(Long post_id) {
        String query = "DELETE FROM post WHERE post_id = ?";
        jdbcTemplate.update(
                query,
                post_id
        );

        System.out.println("Post 수정 성공");
    }

    public List<PostAndUserName> findByProblemId(Long problemId) {
        List<PostAndUserName> postList = new ArrayList<>();
//        String query = "SELECT * FROM post WHERE problem_id = ?";

        String query = "SELECT p.*, u.name " +
                "FROM post p " +
                "JOIN user u ON p.user_id = u.user_id " +
                "WHERE problem_id = ?";

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(query, problemId);
        for (Map<String, Object> row : rows) {
            postList.add(new PostAndUserName(
                    (long) row.get("post_id"),
                    (long) row.get("problem_id"),
                    (long) row.get("user_id"),
                    (String) row.get("title"),
                    (String) row.get("content"),
                    (LocalDateTime) row.get("written_date"),
                    (String) row.get("name")
            ));
        }

        return postList;
    }

    //postId로 Post 조회
    public Post findByPostId(Long postId) {
        String sql = "SELECT * FROM post WHERE post_id = ?";

        RowMapper<Post> rowMapper = getRowMapper();

        List<Post> posts = jdbcTemplate.query(sql, ps -> {
            ps.setLong(1, postId);
        }, rowMapper);

        //return jdbcTemplate.queryForObject(sql, rowMapper, postId);

        return DataAccessUtils.singleResult(posts);
    }

    private static RowMapper<Post> getRowMapper() {
        RowMapper<Post> rowMapper = (resultSet, rowNum) -> new Post(
                resultSet.getLong("post_id"),
                resultSet.getLong("problem_id"),
                resultSet.getLong("user_id"),
                resultSet.getString("title"),
                resultSet.getString("content"),
                resultSet.getTimestamp("written_date").toLocalDateTime()
        );
        return rowMapper;
    }


    public List<Post> findByUserId(Long user_id) {
        List<Post> postList = new ArrayList<>();
        String query = "SELECT * FROM post WHERE post_id = ?";

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(query, user_id);
        for (Map<String, Object> row : rows) {
            postList.add(new Post(
                    (long) row.get("post_id"),
                    (long) row.get("problem_id"),
                    (long) row.get("user_id"),
                    (String) row.get("title"),
                    (String) row.get("content"),
                    (LocalDateTime) row.get("written_date")
            ));
        }
        return postList;
    }

}
