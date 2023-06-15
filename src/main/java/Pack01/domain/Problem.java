package Pack01.domain;

import java.time.LocalDateTime;

public class Problem {

    private Long problemId;

    private Long userId;

    private String title;

    private String description;

    private LocalDateTime writtenDate;

    private LocalDateTime updateDate;

    public Problem() {
    }

    public Problem(Long problemId, Long userId, String title, String description, LocalDateTime writtenDate, LocalDateTime updateDate) {
        this.problemId = problemId;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.writtenDate = writtenDate;
        this.updateDate = updateDate;
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
}
