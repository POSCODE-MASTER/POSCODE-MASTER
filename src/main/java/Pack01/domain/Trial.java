package Pack01.domain;

import java.time.LocalDateTime;

public class Trial {

    private Long trialId;

    private Long testcaseId;

    private Long userId;

    private Boolean isSolved;

    private Long memory;

    private Double cpuTime;

    private String output;

    private String code;

    private LocalDateTime solveTime;


    public Trial(Long testcaseId, Long userId, Boolean isSolved, Long memory, Double cpuTime, String output, String code, LocalDateTime solveTime) {
        this.testcaseId = testcaseId;
        this.userId = userId;
        this.isSolved = isSolved;
        this.memory = memory;
        this.cpuTime = cpuTime;
        this.output = output;
        this.code = code;
        this.solveTime = solveTime;
    }

    public Long getTrialId() {
        return trialId;
    }

    public void setTrialId(Long trialId) {
        this.trialId = trialId;
    }

    public Long getTestcaseId() {
        return testcaseId;
    }

    public void setTestcaseId(Long testcaseId) {
        this.testcaseId = testcaseId;
    }

    public Boolean getSolved() {
        return isSolved;
    }

    public void setSolved(Boolean solved) {
        isSolved = solved;
    }

    public Long getMemory() {
        return memory;
    }

    public void setMemory(Long memory) {
        this.memory = memory;
    }

    public Double getCpuTime() {
        return cpuTime;
    }

    public void setCpuTime(Double cpuTime) {
        this.cpuTime = cpuTime;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getSolveTime() {
        return solveTime;
    }

    public void setSolveTime(LocalDateTime solveTime) {
        this.solveTime = solveTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
