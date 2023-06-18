package Pack01.controller;

import Pack01.domain.Comment;
import Pack01.domain.User;
import Pack01.repository.CommentRepository;
import Pack01.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    @PostMapping("/submitComment")
    public String submitComment(@RequestParam(name = "postId") Long postId, @RequestParam(name = "problemId") Long problemId, @SessionAttribute(name = "loginUser", required = false) User loginUser, @RequestParam("comment") String comment) {

        Comment save = commentService.save(postId, loginUser.getUserId(), comment);

        return "redirect:/problemBoard?problemId=" + problemId + "&postId=" + postId;
    }

    @GetMapping("/deleteComment")
    public String deleteComment(
            @RequestParam(name = "commentId") Long commentId, @SessionAttribute(name = "loginUser", required = false) User loginUser,
            @RequestParam(name = "postId") Long postId, @RequestParam(name = "problemId") Long problemId) {

        commentService.delete(loginUser.getUserId(), commentId);

        return "redirect:/problemBoard?problemId=" + problemId + "&postId=" + postId;
    }
}
