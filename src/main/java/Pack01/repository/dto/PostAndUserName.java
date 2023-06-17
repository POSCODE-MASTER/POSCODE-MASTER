package Pack01.repository.dto;

import java.time.LocalDateTime;

public class PostAndUserName {

    private Long postId;

    private Long problemId;

    private Long userId;

    private String title;

    private String content;

    private LocalDateTime writtenDate;

    private String name;    // 유저 이름

    public PostAndUserName(Long postId, Long problemId, Long userId, String title, String content, LocalDateTime writtenDate, String name) {
        this.postId = postId;
        this.problemId = problemId;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.writtenDate = writtenDate;
        this.name = name;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getWrittenDate() {
        return writtenDate;
    }

    public void setWrittenDate(LocalDateTime writtenDate) {
        this.writtenDate = writtenDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
