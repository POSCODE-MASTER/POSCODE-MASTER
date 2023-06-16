package Pack01.controller;

import JDoole.JDoodle;
import Pack01.domain.Problem;
import Pack01.domain.Testcase;
import Pack01.domain.Trial;
import Pack01.service.ProblemService;
import Pack01.service.TestCaseService;
import Pack01.service.TrialService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
@Controller
public class TrialController {

    private final TestCaseService testCaseService;
    private final ProblemService problemService;


    @Autowired
    public TrialController(TestCaseService testCaseService, ProblemService problemService) {
        this.testCaseService = testCaseService;
        this.problemService = problemService;
    }

    @PostMapping("/executeUserCode")
    public ResponseEntity<JsonObject> executeUserCode(@RequestBody Map<String, String> request) {

        String value = request.get("value");
        Long problemId = Long.parseLong(request.get("problemId"));
        int testNumber = Integer.parseInt(request.get("testNumber"));
        List<Testcase> testcases = testCaseService.findByProblemId(problemId);
        Testcase testcase = testcases.get(testNumber);
        JsonParser parser = new JsonParser();
        JsonObject input = (JsonObject)parser.parse(testcase.getInput());
        String stdin = input.get("input").toString();
        
        String processedValue = value.replaceAll("//.*", "")
                .replaceAll("/\\*.*?\\*/", "")
                .replace("\n","")
                .replace("\\","\\\\")
                .replace("\"","\\\"");

        String result = JDoodle.apiCall(stdin, processedValue, "java", "3");
        System.out.println("SOLVEProblem URL");
        System.out.println(result);

        // Create a JSON response using Gson
        Gson gson = new Gson();
        //JsonParser parser = new JsonParser();
        JsonObject jobj = (JsonObject)parser.parse(result);
        System.out.println(jobj);
        return ResponseEntity.ok(jobj);
    }

    @GetMapping("solve")
    public String solvePage(@RequestParam(name="problemId") Long problemId, Model model){
        Problem problem = problemService.findProblemByProblemId(problemId);
        model.addAttribute("problem", problem);

        List<Testcase> testcases = testCaseService.findByProblemId(problemId);
        JsonParser parser = new JsonParser();
        JsonObject input = (JsonObject)parser.parse(testcases.get(0).getInput());
        JsonObject output = (JsonObject)parser.parse(testcases.get(0).getOutput());

        model.addAttribute("exampleInput",input);
        model.addAttribute("exampleOutput",output);
        model.addAttribute("testcaseNum",testcases.size());


        return "solve";
    }


}
