package Pack01.repository.dto;

import java.time.LocalDateTime;

public class UserTrialDto {
    private LocalDateTime solveTime;
    private String result;

    private Long trialId;

    public UserTrialDto(LocalDateTime solveTime, String result, Long trialId) {
        this.solveTime = solveTime;
        this.result = result;
        this.trialId = trialId;
    }

    public Long getTrialId() {
        return trialId;
    }

    public LocalDateTime getSolveTime() {
        return solveTime;
    }

    public String getResult() {
        return result;
    }


}
