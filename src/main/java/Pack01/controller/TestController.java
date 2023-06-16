package Pack01.controller;

import Pack01.domain.Problem;
import Pack01.domain.Testcase;
import Pack01.domain.Trial;
import Pack01.domain.User;
import Pack01.repository.ProblemRepository;
import Pack01.repository.TestcaseRepository;
import Pack01.repository.TrialRepository;
import Pack01.repository.UserRepository;
import Pack01.repository.dto.SolvedProblemDto;
import Pack01.service.ProblemService;
import Pack01.service.TestCaseService;
import Pack01.service.TrialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 메서드 테스트 해보는 공간입니다.
 */
@Controller
public class TestController {

    @Autowired
    private ProblemService problemService;

    @Autowired
    private TestCaseService testCaseService;
    @Autowired
    private ProblemRepository problemRepository;

    @Autowired
    private TestcaseRepository testcaseRepository;

    @Autowired
    private TrialService trialService;

    @Autowired
    private TrialRepository trialRepository;

    @Autowired
    private UserRepository userRepository;



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
//    @GetMapping("/test3")
//    public String test3(@SessionAttribute(name = "loginUser", required = false) User loginUser) {
//
//        Problem problem = new Problem(loginUser.getUserId(), loginUser.getUserId(), "title", "description", LocalDateTime.now(), LocalDateTime.now(), 3);
//        Problem saveProblem = problemService.save(problem);
//
//
//        Testcase testcase = new Testcase(saveProblem.getProblemId(), "{\"input\" : \"input\"}", "{\"output\" : \"output\"}");
//        Testcase saveTestcase = testCaseService.save(testcase);
//
//
//        return "test";
//    }





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



    // trial 저장
    @GetMapping("/test8")
    public String test8() {

        Long testcaseId = 1L;


        LocalDateTime now = LocalDateTime.now();
        Trial trial = new Trial(testcaseId, 2L, true, 1L, 3.1, "{\"updateInput\" : \"updateInput\"}", "1", now);

        trialService.save(trial);

        System.out.println(trial.getTrialId());
        System.out.println(trial.getOutput());

        return "test";
    }



    // API 테스트
    @GetMapping("/test9")
    public String test9(){
        String clientId = "106ecb6fd86bae734a18f6ae09c7e27b"; //Replace with your client ID
        String clientSecret = "6dcd21de8f8b43d82a4119a10e96b8462032b0aa77fb6254bbcd20a887e9d029"; //Replace with your client Secret

        String script = "public class Main{\n" +
                "\tpublic static void main(String[] args) {\n" +
                "\t\tSystem.out.println(\"안녕\");\n" +
                "\t}\n" +
                "}";

        String language = "java";
        String versionIndex = "3";

        try {
            URL url = new URL("https://api.jdoodle.com/v1/execute");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");

            String input = "{\"clientId\": \"" + clientId + "\",\"clientSecret\":\"" + clientSecret + "\",\"script\":\"" + script +
                    "\",\"language\":\"" + language + "\",\"versionIndex\":\"" + versionIndex + "\"} ";

            System.out.println(input);

            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(input.getBytes());
            outputStream.flush();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Please check your inputs : HTTP error code : "+ connection.getResponseCode());
            }

            BufferedReader bufferedReader;
            bufferedReader = new BufferedReader(new InputStreamReader(
                    (connection.getInputStream())));

            String output;
            System.out.println("Output from JDoodle .... \n");
            while ((output = bufferedReader.readLine()) != null) {
                System.out.println(output);
            }

            connection.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "test";
    }



    // 내가 맞은 문제 리스트 조회
    @GetMapping("/test10")
    public String test10() {

        Long userId = 2L;

        List<SolvedProblemDto> solvedProblemDtos = userRepository.selectSolvedProblem(userId);

        for (SolvedProblemDto solvedProblemDto : solvedProblemDtos) {
            System.out.println(solvedProblemDto.getProblemId());
            System.out.println(solvedProblemDto.getUserId());
            System.out.println(solvedProblemDto.getTitle());
            System.out.println(solvedProblemDto.getDescription());
            System.out.println(solvedProblemDto.getWrittenDate().toString());
            System.out.println(solvedProblemDto.getUpdateDate().toString());
            System.out.println(solvedProblemDto.getLevel());
            System.out.println(solvedProblemDto.getSolver());
            System.out.println(solvedProblemDto.getSolveTime());
        }

        return "test";
    }







}
