package Pack01.controller;

import JDoole.JDoodle;
import Pack01.domain.Problem;
import Pack01.repository.ProblemRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class problemController {
    @Autowired
    private ProblemRepository problemRepository;

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
        List<Problem> problemList = problemRepository.selectAll(page, level);
        model.addAttribute("problemList", problemList);
        model.addAttribute("page", page);
        model.addAttribute("level", level);
        return "problemList";
    }
}
