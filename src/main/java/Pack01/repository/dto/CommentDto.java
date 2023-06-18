package Pack01.repository.dto;

public class CommentDto {

    private Long postCommentId;

    private Long postId;

    private Long userId;

    private String comment;

    private String name;

    public CommentDto(Long postCommentId, Long postId, Long userId, String comment, String name) {
        this.postCommentId = postCommentId;
        this.postId = postId;
        this.userId = userId;
        this.comment = comment;
        this.name = name;
    }

    public Long getPostCommentId() {
        return postCommentId;
    }

    public void setPostCommentId(Long postCommentId) {
        this.postCommentId = postCommentId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
