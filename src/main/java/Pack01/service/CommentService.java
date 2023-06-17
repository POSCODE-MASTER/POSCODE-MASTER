package Pack01.service;

import Pack01.repository.CommentRepository;
import Pack01.repository.dto.CommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository){this.commentRepository = commentRepository;}


    public List<CommentDto> selectComment(Long postId) {
        return commentRepository.selectPostDetail(postId);
    }
}
