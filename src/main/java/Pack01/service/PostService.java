package Pack01.service;

import Pack01.controller.form.PostForm;
import Pack01.domain.Post;
import Pack01.repository.CommentRepository;
import Pack01.repository.PostRepository;
import Pack01.repository.dto.PostAndUserName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Autowired
    public PostService(PostRepository postRepository, CommentRepository commentRepository){
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    public void save(Long problemId, Long userId, PostForm postForm){
        Post post = new Post(problemId, userId, postForm.getTitle(), postForm.getContent(), LocalDateTime.now());

        postRepository.save(post);
    }

    public void update(Post post, Long post_id){
        postRepository.update(post, post_id);
    }

    public void delete(Long post_id){
        postRepository.delete(post_id);
    }

    public List<PostAndUserName> findByProblemId(Long problem_id){
        return postRepository.findByProblemId(problem_id);
    }

    public List<Post> findByUserId(Long user_id){
        return postRepository.findByUserId(user_id);
    }

    //상세 게시글
    public Post detailPost(Long postId) {
        return postRepository.findByPostId(postId);
    }

}
