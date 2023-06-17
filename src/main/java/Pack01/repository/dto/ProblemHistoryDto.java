package Pack01.repository.dto;

import java.time.LocalDateTime;

public class ProblemHistoryDto {

    public Long problemId;

    public Long userId;

    public String title;

    public String description;

    public LocalDateTime writtenDate;

    public LocalDateTime updateDate;

    public Integer level;

    public LocalDateTime solveTime;

    public String result;


    public ProblemHistoryDto(Long problemId, Long userId, String title, String description, LocalDateTime writtenDate, LocalDateTime updateDate, Integer level, LocalDateTime solveTime, String result) {
        this.problemId = problemId;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.writtenDate = writtenDate;
        this.updateDate = updateDate;
        this.level = level;
        this.solveTime = solveTime;
        this.result = result;
    }

    public Long getProblemId() {
        return problemId;
    }

    public void setProblemId(Long problemId) {
        this.problemId = problemId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getWrittenDate() {
        return writtenDate;
    }

    public void setWrittenDate(LocalDateTime writtenDate) {
        this.writtenDate = writtenDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public LocalDateTime getSolveTime() {
        return solveTime;
    }

    public void setSolveTime(LocalDateTime solveTime) {
        this.solveTime = solveTime;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
