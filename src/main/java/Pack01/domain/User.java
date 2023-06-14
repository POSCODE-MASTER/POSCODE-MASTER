package Pack01.domain;


public class User {

    private Long userId;

    private String id;

    private String password;

    private String level;

    private String name;

    private String role;

    public User() {
    }

    public User(Long userId, String id, String password, String level, String name, String role) {
        this.userId = userId;
        this.id = id;
        this.password = password;
        this.level = level;
        this.name = name;
        this.role = role;
    }

    public Long getUserId() {
        return userId;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
