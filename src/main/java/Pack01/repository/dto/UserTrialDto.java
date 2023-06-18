package Pack01.repository.dto;

import java.time.LocalDateTime;

public class UserTrialDto {
    private String code;
    private LocalDateTime solveTime;
    private String result;

    public UserTrialDto(String code, LocalDateTime solveTime, String result) {
        this.code = code;
        this.solveTime = solveTime;
        this.result = result;
    }

    public String getCode() {
        return code;
    }

    public LocalDateTime getSolveTime() {
        return solveTime;
    }

    public String getResult() {
        return result;
    }


}
