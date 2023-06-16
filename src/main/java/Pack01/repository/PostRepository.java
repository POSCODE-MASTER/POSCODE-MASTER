package Pack01.repository;

import Pack01.controller.form.PostForm;
import Pack01.domain.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
    public void save(PostForm postForm){
        String query = "INSERT INTO post (problem_id, user_id, title, content, written_date) " +
                       "VALUES (?, ?, ?, ?, ?);";

        jdbcTemplate.update(
                query,
                postForm.getProblem_id(),
                postForm.getUser_id(),
                postForm.getTitle(),
                postForm.getContent(),
                String.valueOf(LocalDateTime.now())
                );

        System.out.println("Post 삽입 성공");
    }


    public void update(PostForm postForm, Long post_id){
        String query = "UPDATE post SET title = ?, content = ? WHERE post_id = ?";
        jdbcTemplate.update(
                query,
                postForm.getTitle(),
                postForm.getContent(),
                post_id
        );

        System.out.println("Post 수정 성공");
    }

    public void delete(Long post_id){
        String query = "DELETE FROM post WHERE post_id = ?";
        jdbcTemplate.update(
                query,
                post_id
        );

        System.out.println("Post 수정 성공");
    }

    public List<Post> findByProblemId(Long problem_id){
        List<Post> postList = new ArrayList<>();
        String query = "SELECT * FROM post WHERE post_id = ?";

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(query, problem_id);
        for(Map<String, Object> row : rows) {
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

    public List<Post> findByUserId(Long user_id){
        List<Post> postList = new ArrayList<>();
        String query = "SELECT * FROM post WHERE post_id = ?";

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(query, user_id);
        for(Map<String, Object> row : rows) {
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
