package Pack01.controller;

import Pack01.domain.Comment;
import Pack01.domain.User;
import Pack01.repository.CommentRepository;
import Pack01.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    public String submitComment(@RequestParam Long postId, @SessionAttribute(name = "loginUser", required = false) User loginUser, @RequestParam("comment") String comment) {

        Comment save = commentService.save(postId, loginUser.getUserId(), comment);

        return "redirect:/problemBoard?postId=" + postId;
    }
}
