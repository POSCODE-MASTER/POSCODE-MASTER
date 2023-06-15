package Pack01.controller;

import Pack01.domain.Problem;
import Pack01.repository.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * 메서드 테스트 해보는 공간입니다.
 */
@Controller
public class TestController {

    private final ProblemRepository problemRepository;

    @Autowired
    public TestController(ProblemRepository problemRepository) {
        this.problemRepository = problemRepository;
    }

    @GetMapping("/test")
    public String test(Model model) {
        List<Problem> problems = problemRepository.selectAll(1);

        for (Problem problem : problems) {
            System.out.println(problem.getProblemId());
            System.out.println(problem.getTitle());
            System.out.println(problem.getUserId());
        }
        return "test";
    }
}
