package Pack01.controller;

import java.time.LocalDateTime;

public class PostForm {

    private long problem_id;
    private long user_id;
    private String title;
    private String content;
    private LocalDateTime written_date;

    public PostForm(long problem_id, long user_id, String title, String content) {
        this.problem_id = problem_id;
        this.user_id = user_id;
        this.title = title;
        this.content = content;
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

    public void setWritten_date(LocalDateTime written_date) {
        this.written_date = written_date;
    }


}
