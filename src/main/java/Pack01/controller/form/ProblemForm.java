package Pack01.controller.form;

public class ProblemForm {

    private String title;
    private String description;
    private Integer level;

    public ProblemForm(String title, String description, Integer level) {
        this.title = title;
        this.description = description;
        this.level = level;
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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
