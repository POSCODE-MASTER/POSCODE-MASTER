package Pack01.controller;

import Pack01.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    private final ProblemService problemService;
    @Autowired
    public MainController(ProblemService problemService){
        this.problemService = problemService;
    }
    @RequestMapping("/moveMain")
    public String problemList(Model model){

        return "problemList";
    }

}
