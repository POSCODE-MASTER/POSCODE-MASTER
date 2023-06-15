package Pack01.domain;

import java.time.LocalDateTime;

public class Problem {

    private Long problemId;

    private Long userId;

    private String title;

    private String description;

    private LocalDateTime writtenDate;

    private LocalDateTime updateDate;

    private Integer level;

    public Problem() {
    }

    //기본키 제외한 생성자
    public Problem(Long userId, String title, String description, LocalDateTime writtenDate, LocalDateTime updateDate, Integer level) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.writtenDate = writtenDate;
        this.updateDate = updateDate;
        this.level = level;
    }

    //전체 생성자
    public Problem(Long problemId, Long userId, String title, String description, LocalDateTime writtenDate, LocalDateTime updateDate, Integer level) {
        this.problemId = problemId;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.writtenDate = writtenDate;
        this.updateDate = updateDate;
        this.level = level;
    }

    //업데이트용 생성자
    public Problem(String title, String description, LocalDateTime updateDate, Integer level) {
        this.title = title;
        this.description = description;
        this.updateDate = updateDate;
        this.level = level;
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
}
