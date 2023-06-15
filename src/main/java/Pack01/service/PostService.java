package Pack01.service;

import Pack01.controller.PostForm;
import Pack01.domain.Post;
import Pack01.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    void save(PostForm postForm){
        postRepository.save(postForm);
    }

    void update(PostForm postForm, Long post_id){
        postRepository.update(postForm, post_id);
    }

    void delete(Long post_id){
        postRepository.delete(post_id);
    }

    public List<Post> findByProblemId(Long problem_id){
        return postRepository.findByProblemId(problem_id);
    }

    public List<Post> findByUserId(Long user_id){
        return postRepository.findByUserId(user_id);
    }
}
