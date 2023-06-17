package Pack01.domain;

public class Comment {
    private Long postCommentId;
    private Long postId;
    private Long userId;
    private String comment;

    public Comment(){}

    public Comment(Long postCommentId, Long postId, Long userId, String comment){
        this.postCommentId = postCommentId;
        this.postId = postId;
        this.userId = userId;
        this.comment = comment;
    }

    public Long getPostCommentId(){return postCommentId;}
    public void setPostCommentId(Long commentId){this.postCommentId = postCommentId;}

    public Long getPostId(){return postId;}
    public void setPostId(Long postId){this.postId = postId;}

    public Long getUserId(){return userId;}
    public void setUserId(Long userId){this.userId = userId;}

    public String getComment(){return comment;}
    public void setComment(String comment){this.comment = comment;}

}
