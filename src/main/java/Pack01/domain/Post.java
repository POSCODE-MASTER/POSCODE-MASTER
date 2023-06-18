package Pack01.domain;

import java.time.LocalDateTime;

public class Post {
    private long post_id;
    private long problem_id;
    private long user_id;
    private String title;
    private String content;
    private LocalDateTime written_date;

    public Post(long post_id, long problem_id, long user_id, String title, String content, LocalDateTime written_date) {
        this.post_id = post_id;
        this.problem_id = problem_id;
        this.user_id = user_id;
        this.title = title;
        this.content = content;
        this.written_date = written_date;
    }

    public Post(long problem_id, long user_id, String title, String content, LocalDateTime written_date) {
        this.problem_id = problem_id;
        this.user_id = user_id;
        this.title = title;
        this.content = content;
        this.written_date = written_date;
    }

    //업데이트용 생성자
    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public long getPost_id() {
        return post_id;
    }

    public long getProblem_id() {
        return problem_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getWritten_date() {
        return written_date;
    }


}
