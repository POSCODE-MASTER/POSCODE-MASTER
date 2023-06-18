package Pack01.service;

import Pack01.domain.Comment;
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


    public Comment save(Long postId, Long userId, String comment){
        Comment saveComment = new Comment(postId, userId, comment);
        return commentRepository.save(saveComment);
    }

    public List<CommentDto> selectComment(Long postId) {
        return commentRepository.selectPostDetail(postId);
    }
}
