package Pack01.controller;

import Pack01.controller.form.PostForm;
import Pack01.domain.User;
import Pack01.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/problemBoardCreate")
    public String problemBoardCreate(@RequestParam Long problemId){
        return "problemBoardCreate";
    }

    @PostMapping("/problemBoardCreate")
    public String problemBoardCreateOk(@RequestParam Long problemId, @SessionAttribute(name = "loginUser", required = false) User loginUser, PostForm postForm) {
        postService.save(problemId, loginUser.getUserId(), postForm);

        return "redirect:/problemBoardList?problemId" + problemId;
    }


}
