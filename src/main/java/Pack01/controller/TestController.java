package Pack01.controller;

import Pack01.domain.Problem;
import Pack01.domain.Testcase;
import Pack01.domain.User;
import Pack01.repository.ProblemRepository;
import Pack01.repository.TestcaseRepository;
import Pack01.service.ProblemService;
import Pack01.service.TestCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 메서드 테스트 해보는 공간입니다.
 */
@Controller
public class TestController {

    private final ProblemService problemService;
    private final TestCaseService testCaseService;
    private final ProblemRepository problemRepository;
    private final TestcaseRepository testcaseRepository;

    @Autowired
    public TestController(ProblemService problemService, TestCaseService testCaseService, ProblemRepository problemRepository, TestcaseRepository testcaseRepository) {
        this.problemService = problemService;
        this.testCaseService = testCaseService;
        this.problemRepository = problemRepository;
        this.testcaseRepository = testcaseRepository;
    }


    // 전체 조회 (페이징 + level 필터)
    @GetMapping("/test1")
    public String test1( @RequestParam(required = false) Integer page, @RequestParam(required = false) String level, Model model) {
        List<Problem> problems = problemRepository.selectAll(page, level);

        for (Problem problem : problems) {
            System.out.println("problemId: " + problem.getProblemId());
            System.out.println("title: " + problem.getTitle());
            System.out.println("userId: " + problem.getUserId());
            System.out.println("level: " + problem.getLevel());
            System.out.println("----------------------------------");
        }

        System.out.println("=====================================");
        return "test";
    }

    //내가 시도한 문제 리스트
    @GetMapping("/test2")
    public String test2(@SessionAttribute(name = "loginUser", required = false) User loginUser) {
        List<Problem> solvedProblems = problemRepository.getSolvedProblems(loginUser.getUserId());

        for (Problem solvedProblem : solvedProblems) {
            System.out.println(solvedProblem.getProblemId());
            System.out.println(solvedProblem.getTitle());
        }

        return "test";
    }



    // problem, testcase 저장
    @GetMapping("/test3")
    public String test3(@SessionAttribute(name = "loginUser", required = false) User loginUser) {

        Problem problem = new Problem(loginUser.getUserId(), loginUser.getUserId(), "title", "description", LocalDateTime.now(), LocalDateTime.now(), 3);
        Problem saveProblem = problemService.save(problem);


        Testcase testcase = new Testcase(saveProblem.getProblemId(), "{\"input\" : \"input\"}", "{\"output\" : \"output\"}");
        Testcase saveTestcase = testCaseService.save(testcase);


        return "test";
    }





    // userId로 problem 리스트 출력 (자신이 출제한 problem들 출력)
    @GetMapping("/test4")
    public String test4(@SessionAttribute(name = "loginUser", required = false) User loginUser) {

        List<Problem> problems = problemService.findByUserId(loginUser.getUserId());

        for (Problem problem : problems) {
            System.out.println(problem.getProblemId());
            System.out.println(problem.getTitle());
            System.out.println(problem.getDescription());
        }

        return "test";
    }


    // problem 수정
    @GetMapping("/test5")
    public String test5() {

        Long updateProblemId = 30L;

        Problem updateProblem = new Problem("updateTitle", "updateDescription", LocalDateTime.now(), 10);

        problemService.updateProblem(updateProblemId, updateProblem);

        return "test";
    }




    // problem_id로 testcase 리스트 조회
    @GetMapping("/test6")
    public String test6() {

        Long problemId = 15L;

        List<Testcase> testcases = testCaseService.findByProblemId(problemId);

        for (Testcase testcase : testcases) {
            System.out.println(testcase.getTestcaseId());
            System.out.println(testcase.getInput());
            System.out.println(testcase.getOutput());
        }

        return "test";
    }


    // testcase 수정
    @GetMapping("/test7")
    public String test7() {

        Long updateTestcaseId = 1L;

        Testcase updateTestcase = new Testcase("{\"updateInput\" : \"updateInput\"}", "{\"updateOutput\" : \"updateOutput\"}");

        testCaseService.update(updateTestcaseId, updateTestcase);

        return "test";
    }

}
