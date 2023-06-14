package Pack01.domain;


public class User {

    private Long user_id;

    private String id;

    private String password;

    private String level;

    private String name;

    private String role;

    public User(Long user_id, String id, String password, String level, String name, String role) {
        this.user_id = user_id;
        this.id = id;
        this.password = password;
        this.level = level;
        this.name = name;
        this.role = role;
    }

    public Long getUser_id() {
        return user_id;
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
}
