package Pack01.controller;

import JDoole.JDoodle;
import Pack01.controller.form.ProblemForm;
import Pack01.domain.Problem;
import Pack01.domain.User;
import Pack01.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class ProblemController {

    private final ProblemService problemService;

    @Autowired
    public ProblemController(ProblemService problemService) {
        this.problemService = problemService;
    }

    @RequestMapping("/solveProblem")
    @ResponseBody
    public String solve(@RequestBody Map<String, String> request, Model model){
        String value = request.get("value");
        String result = JDoodle.apiCall("1", value, "java", "3");
        model.addAttribute("result", result);

        return "solve";
    }


    /**
     * 문제 생성 (화면 이동)
     */
    @GetMapping("/problemCreate")
    public String problemCreate() {
        return "problemCreate";
    }

    /**
     * 문제 생성 (처리)
     */
    @PostMapping("/problemCreate")
    public String problemCreateOk(@SessionAttribute(name = "loginUser", required = false) User loginUser, ProblemForm problemForm) {
        Problem saveProblem = problemService.save(loginUser.getUserId(), problemForm);

        return "redirect:/testcaseCreate/" + saveProblem.getProblemId();
    }
}
