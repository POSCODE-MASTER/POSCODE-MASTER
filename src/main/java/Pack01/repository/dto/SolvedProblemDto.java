package Pack01.repository.dto;

import java.time.LocalDateTime;

public class SolvedProblemDto {

    private Long problemId;
    private Long userId;
    private String title;
    private String description;
    private LocalDateTime writtenDate;
    private LocalDateTime updateDate;
    private Integer level;
    private Long solver;
    private LocalDateTime solveTime;

    public SolvedProblemDto() {
    }

    public SolvedProblemDto(Long problemId, Long userId, String title, String description, LocalDateTime writtenDate, LocalDateTime updateDate, Integer level, Long solver, LocalDateTime solveTime) {
        this.problemId = problemId;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.writtenDate = writtenDate;
        this.updateDate = updateDate;
        this.level = level;
        this.solver = solver;
        this.solveTime = solveTime;
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

    public Long getSolver() {
        return solver;
    }

    public void setSolver(Long solver) {
        this.solver = solver;
    }

    public LocalDateTime getSolveTime() {
        return solveTime;
    }

    public void setSolveTime(LocalDateTime solveTime) {
        this.solveTime = solveTime;
    }
}
