package Pack01.controller;

import JDoole.JDoodle;
import Pack01.controller.form.ProblemForm;
import Pack01.domain.Problem;
import Pack01.domain.User;
import Pack01.service.ProblemService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class ProblemController {

    private final ProblemService problemService;

    @Autowired
    public ProblemController(ProblemService problemService) {
        this.problemService = problemService;
    }

    @PostMapping("/solveProblem")
    public ResponseEntity<JsonObject> solve(@RequestBody Map<String, String> request) {
        String value = request.get("value");
        System.out.println("SOLVEProblem URLPOST");
        System.out.println(value);
        String result = JDoodle.apiCall("1", value, "java", "3");
        System.out.println("SOLVEProblem URL");
        System.out.println(result);

        // Create a JSON response using Gson
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonObject jobj = (JsonObject)parser.parse(result);
        System.out.println(jobj);
        return ResponseEntity.ok(jobj);
    }

    @GetMapping("/solveProblem")
    public String solveGet(Map<String, String> request, Model model){
        String value = request.get("value");
        System.out.println(value);
        String result = JDoodle.apiCall("1", value, "java", "3");
        System.out.println("SOLVEProblem URL GET");
        System.out.println(result);
        model.addAttribute("result", result);

        return "solve";
    }

    @GetMapping("/problemList")
    public String problemList(@RequestParam(name = "page", defaultValue = "1") int page,
                              @RequestParam(name = "level", defaultValue = "") String level,
                              Model model){
        List<Problem> problemList = problemService.selectAll(page, level);
        model.addAttribute("problemList", problemList);
        model.addAttribute("page", page);
        model.addAttribute("level", level);
        return "problemList";
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
