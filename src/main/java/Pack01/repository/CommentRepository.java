package Pack01.repository;

import Pack01.domain.Comment;
import Pack01.repository.dto.CommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class CommentRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CommentRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    //comment 저장
    public Comment save(Comment comment){
        String sql = "INSERT INTO problem(post_id, user_id, comment)" +
                "VALUES (?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"post_comment_id"});
            ps.setLong(1, comment.getPostId());
            ps.setLong(2, comment.getUserId());
            ps.setString(3, comment.getComment());
            return ps;
        }, keyHolder);

        long generatedId = keyHolder.getKey().longValue();
        comment.setPostCommentId(generatedId);

        return comment;
    }

    //comment 전체 조회
    public List<Comment> selectAllComment(Long postId){
        String sql = "SELECT * FROM post_comment WHERE post_id = ?";
        return jdbcTemplate.query(sql, commentRowMapper(), postId);
    }

    private RowMapper<Comment> commentRowMapper() {
        return (rs, rowNum) -> {
            Comment comment = new Comment();
            comment.setPostCommentId(rs.getLong("post_comment_id"));
            comment.setPostId(rs.getLong("post_id"));
            comment.setUserId(rs.getLong("user_id"));
            comment.setComment(rs.getString("comment"));
            return comment;
        };
    }


    //postId로 comment정보, user의 name 조회
    public List<CommentDto> selectPostDetail(Long postId) {
        String sql = "SELECT u.name, pc.* " +
                    "FROM post_comment pc " +
                    "JOIN user u ON pc.user_id = u.user_id " +
                    "WHERE pc.post_id = ? " +
                    "ORDER BY pc.post_comment_id";

        RowMapper<CommentDto> rowMapper = (resultSet, rowNum) -> {
            CommentDto commentDto = new CommentDto(
                    resultSet.getLong("post_comment_id"),
                    resultSet.getLong("post_id"),
                    resultSet.getLong("user_id"),
                    resultSet.getString("comment")
            );
            return commentDto;
        };

        return jdbcTemplate.query(sql, rowMapper, postId);
    }



}
