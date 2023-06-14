package Pack01.controller;

import Pack01.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import Pack01.service.UserService;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    /**
     * 로그인
     */


    /**
     * 회원 가입
     */
    @GetMapping("/register")
    public String register(){
        return "register";
    }

    /**
     * 회원 가입 처리
     */
    @PostMapping("/register")
    public String registerOk(User user) {
        userService.save(user);

        return "TigerView";
    }

}
