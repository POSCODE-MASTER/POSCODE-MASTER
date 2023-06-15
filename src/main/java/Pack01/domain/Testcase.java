package Pack01.domain;

public class Testcase {

    private Long testcaseId;
    private Long problemId;
    private String input;
    private String output;


    public Testcase() {
    }

    public Testcase(Long problemId, String input, String output) {
        this.problemId = problemId;
        this.input = input;
        this.output = output;
    }


    //업데이트용 생성자
    public Testcase(String input, String output) {
        this.input = input;
        this.output = output;
    }

    public Long getTestcaseId() {
        return testcaseId;
    }

    public void setTestcaseId(Long testcaseId) {
        this.testcaseId = testcaseId;
    }

    public Long getProblemId() {
        return problemId;
    }

    public void setProblemId(Long problemId) {
        this.problemId = problemId;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }
}
