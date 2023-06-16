package Pack01.controller;

import JDoole.JDoodle;
import com.google.gson.Gson;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class problemController {

    @PostMapping("/solveProblem")
    public ResponseEntity<String> solve(@RequestBody Map<String, String> request) {
        String value = request.get("value");
        System.out.println("SOLVEProblem URLPOST");
        System.out.println(value);
        String result = JDoodle.apiCall("1", value, "java", "3");
        System.out.println("SOLVEProblem URL");
        System.out.println(result);

        // Create a JSON response using Gson
        Gson gson = new Gson();
        String jsonResponse = gson.toJson(result);

        return ResponseEntity.ok(jsonResponse);
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
}
