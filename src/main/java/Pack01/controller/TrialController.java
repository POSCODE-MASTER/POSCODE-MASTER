package Pack01.controller;

import JDoole.JDoodle;
import Pack01.domain.Problem;
import Pack01.domain.Testcase;
import Pack01.domain.Trial;
import Pack01.service.ProblemService;
import Pack01.service.TestCaseService;
import Pack01.service.TrialService;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Controller
public class TrialController {

    private final TestCaseService testCaseService;
    private final ProblemService problemService;

    private final TrialService trialService;


    @Autowired
    public TrialController(TestCaseService testCaseService, ProblemService problemService, TrialService trialService) {
        this.testCaseService = testCaseService;
        this.problemService = problemService;
        this.trialService = trialService;
    }

    @PostMapping("/executeUserCode")
    public ResponseEntity<List<JsonObject>> executeUserCode(@RequestBody Map<String, String> request) {
        String value = request.get("value");
        Long problemId = Long.parseLong(request.get("problemId"));
        Long userId = Long.parseLong(request.get("userId"));
        LocalDateTime currentTime = LocalDateTime.now();


        String processedValue = value.replaceAll("//.*", "")
                .replaceAll("/\\*.*?\\*/", "")
                .replace("\n","")
                .replace("\\","\\\\")
                .replace("\"","\\\"");

        List<Testcase> testcases = testCaseService.findByProblemId(problemId);
        List<JsonObject> resultList = new ArrayList<>();

        for(Testcase testcase : testcases) {
            JsonParser parser = new JsonParser();
            JsonObject input = (JsonObject) parser.parse(testcase.getInput());
            String stdin = input.get("input").toString();
            stdin = stdin
                    .replace("[", "")
                    .replace("]", "")
                    .replace(",", " ")
                    .replace("\"", "");

            String result = JDoodle.apiCall(stdin, processedValue, "java", "3");
            // Create a JSON response using Gson
            Gson gson = new Gson();
            JsonObject jobj = (JsonObject) parser.parse(result);
            JsonObject testOutput = (JsonObject)parser.parse(testcase.getOutput());
            String answer = testOutput.get("output")
                    .toString()
                    .replace("[","")
                    .replace("]","")
                    .replace("\"","")
                    .replace(",", " ");

            String output = jobj.get("output")
                    .toString()
                    .replace("\"","")
                    .replace("\\n","");

            jobj.addProperty("answer", answer);
            jobj.addProperty("output", output);

            String isCorrect = answer.equals(output)? "Solved" : "Not Solved";
            jobj.addProperty("isCorrect",isCorrect);

            resultList.add(jobj);
            System.out.println("Result is.." + jobj);

            Boolean trialSolved = answer.equals(output)? true:false;
            //만약 error가 난다면, memoery와 CPUtime은 null이 된다. 해당 부분을 전처리 하는 코드가 필요하다 .
            System.out.println(jobj.get("memory").toString());
            Long memory = Long.parseLong(jobj.get("memory").toString().replace("\"",""));
            double cpuTime = Double.parseDouble(jobj.get("cpuTime").toString().replace("\"",""));

            System.out.println(testcase.getTestcaseId());
            System.out.println(userId);
            System.out.println(trialSolved);
            System.out.println(memory);
            System.out.println(cpuTime);
            System.out.println(output);
            System.out.println(value);
            System.out.println(currentTime);

            String[]  outputDB = output.split("\\s+");
            JsonArray outputArray = new JsonArray();
            for (String i : outputDB) {
                outputArray.add(i);
            }
            JsonObject outputJsonObject = new JsonObject();
            outputJsonObject.add("output", outputArray);
            String outputJson = new Gson().toJson(outputJsonObject);

            Trial trial = new Trial(testcase.getTestcaseId(),userId, trialSolved,memory, cpuTime,outputJson,value,currentTime);
            System.out.println("OK until here");
            trialService.save(trial);
        }
        return ResponseEntity.ok(resultList);
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
