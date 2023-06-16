package Pack01.controller;

import Pack01.domain.Testcase;
import Pack01.service.TestCaseService;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TestcaseController {

    private final TestCaseService testCaseService;

    @Autowired
    public TestcaseController(TestCaseService testCaseService) {
        this.testCaseService = testCaseService;
    }

    @GetMapping("/testcaseCreate/{problemId}")
    public String testcaseCreate(@PathVariable String problemId){
        return "testcaseCreate";
    }


    @PostMapping("/testcaseCreate/{problemId}")
    public String testcaseCreateOk(@PathVariable Long problemId, @RequestParam("input") String input, @RequestParam("output") String output) {

        // 입력값을 공백을 기준으로 분할하여 배열로 변환
        String[] inputValues = input.split("\\s+");
        String[] outputValues = output.split("\\s+");

        // JSON 배열 생성
        JsonArray inputArray = new JsonArray();
        JsonArray outputArray = new JsonArray();

        // 입력값들을 JSON 배열에 추가
        for (String value : inputValues) {
            inputArray.add(value);
        }

        // 출력값들을 JSON 배열에 추가
        for (String value : outputValues) {
            outputArray.add(value);
        }

        // JSON 객체 생성 및 배열 추가
        JsonObject inputJsonObject = new JsonObject();
        JsonObject outputJsonObject = new JsonObject();

        inputJsonObject.add("input", inputArray);
        outputJsonObject.add("output", outputArray);

        // JSON 문자열로 변환
        String inputJson = new Gson().toJson(inputJsonObject);
        String outputJson = new Gson().toJson(outputJsonObject);

        // JSON 문자열을 필요한 로직으로 처리하거나 저장
        System.out.println(inputJson);
        System.out.println(outputJson);

        Testcase testcase = new Testcase(problemId, inputJson, outputJson);
        testCaseService.save(testcase);

        return "problemList";
    }
}
