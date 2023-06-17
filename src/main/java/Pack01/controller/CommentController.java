package Pack01.controller;

import Pack01.domain.Comment;
import Pack01.domain.Post;
import Pack01.repository.CommentRepository;
import Pack01.repository.PostRepository;
import Pack01.repository.ProblemRepository;
import Pack01.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CommentController {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentController(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

//    @GetMapping("/problemBoard")
//    public String problemBoard(@RequestParam(name="postId") Long postId,
//                               Model model){
//        List<Comment> commentList = commentRepository.selectAllComment(postId);
//        model.addAttribute("commentList", commentList);
//        return "problemBoard";
//    }
}
