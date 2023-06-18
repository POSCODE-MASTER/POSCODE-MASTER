package Pack01.controller;

import Pack01.controller.form.LoginForm;
import Pack01.domain.Problem;
import Pack01.domain.User;
import Pack01.domain.UserRanking;
import Pack01.repository.dto.SolvedProblemDto;
import Pack01.repository.dto.UserTrialDto;
import Pack01.service.ProblemService;
import Pack01.service.UserRankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import Pack01.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {

    private final UserService userService;
    private final UserRankingService userRankingService;

    private final ProblemService problemService;

    @Autowired
    public UserController(UserService userService, UserRankingService userRankingService, ProblemService problemService) {
        this.userService = userService;
        this.userRankingService = userRankingService;
        this.problemService = problemService;
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

        return "redirect:/problemList?page=1&level=";
    }

    /**
     * 로그아웃
     */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session != null) {
            session.invalidate();       //세션 초기화
        }
        return "redirect:/";
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

    /**
     * 마이페이지 (화면 이동)
     */
    @GetMapping("/myPage")
    public String myPage(@SessionAttribute(name = "loginUser", required = false) User loginUser, Model model){

        User user = userService.findByUserId(loginUser.getUserId());
        model.addAttribute("user", user);

        List<SolvedProblemDto> solvedProblem = userService.selectSolvedProblem(loginUser.getUserId());
        model.addAttribute("solvedProblems", solvedProblem);

        List<SolvedProblemDto> notSolvedProblem = userService.selectNotSolvedProblem(loginUser.getUserId());
        model.addAttribute("notSolvedProblems", notSolvedProblem);

        UserRanking userRanking = userRankingService.findUserRankingById(loginUser.getUserId());
        model.addAttribute("userRanking",userRanking);

        return "myPage";
    }

    @GetMapping("/problemSolvedLog")
    public String problemSolvedLog(
            @SessionAttribute(name = "loginUser", required = true) User loginUser,
            @RequestParam(name="problemId") Long problemId,
            Model model){

        User user = userService.findByUserId(loginUser.getUserId());
        model.addAttribute("user", user);

        List<UserTrialDto> userTrialDtoList = userService.findUserProblemSolveLog(user.getUserId(), problemId);
        model.addAttribute("problemLogs",userTrialDtoList);

        Problem problem = problemService.findProblemByProblemId(problemId);

        model.addAttribute("problem", problem);
        model.addAttribute("solvedUserNum", userService.findSolvedUserNumByProblemId(problemId));
        model.addAttribute("triedUserNum",userService.findTriedUserNumByProblemId(problemId));

        return "problemLog";
    }

    @GetMapping("/header")
    public String header(@SessionAttribute(name="loginUser") User loginUser, Model model){
        User user = userService.findByUserId(loginUser.getUserId());
        model.addAttribute("userRole", user.getRole());
        return "header";
    }

}
