package Pack01.controller;

import Pack01.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import Pack01.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 로그인 (화면 이동)
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }


    /**
     * 로그인 (처리)
     */
    @PostMapping("/login")
    public String loginOk(LoginForm form, HttpServletRequest request) {

        User loginUser = userService.login(form.getId(), form.getPassword());

        if(loginUser == null) {
            return "redirect:/login";  // 로그인 실패 시 /login으로 리다이렉트
        }

        //로그인 성공 처리
        //getSession(): 세션이 있으면 있는 세션 반환, 없으면 신규 세션을 생성
        HttpSession session = request.getSession();
        //세션에 로그인 회원 정보 보관
        session.setAttribute("loginUser", loginUser);


        return "problemList";  // 로그인 성공 시 /main으로 리다이렉트
    }

    /**
     * 메인 (화면 이동)
     */
    @GetMapping("/main")
    public String main() {
        return "main";
    }


    /**
     * 회원 가입 (화면 이동)
     */
    @GetMapping("/register")
    public String register(){
        return "register";
    }

    /**
     * 회원 가입 (처리)
     */
    @PostMapping("/register")
    public String registerOk(User user) {

        userService.save(user);

        return "login";
    }

}
