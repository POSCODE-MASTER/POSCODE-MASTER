package Pack01.controller;

import Pack01.auth.LoginForm;
import Pack01.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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
     * 로그인
     */
//    @PostMapping("/login")
//    public String loginV4(@ModelAttribute LoginForm form,
//                          @RequestParam(defaultValue = "/") String redirectURL,
//                          HttpServletRequest request) {
//
//        User loginMember = userService.login(form.getLoginId(), form.getPassword());
//
//        if(loginMember == null) {
//            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");     //글로벌 오류 생성
//            return "login/loginForm";
//        }
//
//        //로그인 성공 처리
//        //세션이 있으면 있는 세션 반환, 없으면 신규 세션을 생성
//        HttpSession session = request.getSession();
//        //세션에 로그인 회원 정보 보관
//        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);
//
//        return "redirect:" + redirectURL;
//    }


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

        return "TigerView";
    }

}
