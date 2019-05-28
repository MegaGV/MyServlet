package practice3.entity;

public class Administrator {
    private String username;
    private String password;

    public Administrator(){
        this.username = "admin";
        this.password = "123456";
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
