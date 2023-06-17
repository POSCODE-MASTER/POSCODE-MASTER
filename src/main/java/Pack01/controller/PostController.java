package Pack01.controller;

import Pack01.controller.form.PostForm;
import Pack01.domain.Comment;
import Pack01.domain.User;
import Pack01.repository.CommentRepository;
import Pack01.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {

    private final PostService postService;
    private final CommentRepository commentRepository;

    @Autowired
    public PostController(PostService postService, CommentRepository commentRepository) {
        this.postService = postService;
        this.commentRepository = commentRepository;
    }

    /**
     * 질문하기 (화면 이동)
     */
    @GetMapping("/problemBoardCreate")
    public String problemBoardCreate(@RequestParam Long problemId){
        return "problemBoardCreate";
    }

    /**
     * 질문하기 (처리)
     */
    @PostMapping("/problemBoardCreate")
    public String problemBoardCreateOk(@RequestParam Long problemId, @SessionAttribute(name = "loginUser", required = false) User loginUser, PostForm postForm) {
        postService.save(problemId, loginUser.getUserId(), postForm);

        return "redirect:/problemBoardList?problemId=" + problemId;
    }

    /**
     * 게시글 상세 (화면 이동)
     */
    @GetMapping("/problemBoard")
    public String problemBoard(@RequestParam(name="postId") Long postId,
                               Model model){
        List<Comment> commentList = commentRepository.selectAllComment(postId);
        model.addAttribute("commentList", commentList);
        return "problemBoard";
    }


}
