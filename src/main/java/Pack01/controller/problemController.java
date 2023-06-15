package Pack01.controller;

import JDoole.JDoodle;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class problemController {

    @RequestMapping("/solveProblem")
    @ResponseBody
    public String solve(@RequestBody Map<String, String> request, Model model){
        String value = request.get("value");
        String result = JDoodle.apiCall("1", value, "java", "3");
        model.addAttribute("result", result);

        return "solve";
    }
}
