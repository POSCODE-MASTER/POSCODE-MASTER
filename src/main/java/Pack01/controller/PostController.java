package Pack01.controller;

import Pack01.controller.form.PostForm;
import Pack01.domain.Post;
import Pack01.domain.User;
import Pack01.repository.dto.CommentDto;
import Pack01.service.CommentService;
import Pack01.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {

    private final PostService postService;
    private final CommentService commentService;

    @Autowired
    public PostController(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
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

        Post post = postService.detailPost(postId);
        model.addAttribute("post", post);

        List<CommentDto> commentList = commentService.selectComment(postId);
        model.addAttribute("commentList", commentList);

        return "problemBoard";
    }


}
