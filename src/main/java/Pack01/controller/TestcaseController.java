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

import java.util.List;

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
    public String testcaseCreateOk(@PathVariable Long problemId, @RequestParam("input") List<String> inputs, @RequestParam("output") List<String> outputs) {

        // 입력과 출력 리스트의 크기가 같은지 확인
        if (inputs.size() != outputs.size()) {
            throw new IllegalArgumentException("입력과 출력의 개수가 일치하지 않습니다.");
        }

        // 입력과 출력 리스트의 크기를 반복문을 돌며 순회하며 테스트케이스 생성 및 저장
        for (int i = 0; i < inputs.size(); i++) {
            String input = inputs.get(i);
            String output = outputs.get(i);

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

            // JSON 객체 생성
            JsonObject inputJsonObject = new JsonObject();
            JsonObject outputJsonObject = new JsonObject();

            // 입력 배열과 출력 배열을 JSON 객체에 추가
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
        }

        return "problemList";
    }
}
