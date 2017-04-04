package authentication.token;

/**
 * Created by jongzazaal on 3/4/2560.
 */
public class Token {
    private String username, password;
    private Integer user_id, dep_id;

    public Token(String username, String password, Integer user_id, Integer dep_id) {
        this.username = username;
        this.password = password;
        this.user_id = user_id;
        this.dep_id = dep_id;
    }

    public Token() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getDep_id() {
        return dep_id;
    }

    public void setDep_id(Integer dep_id) {
        this.dep_id = dep_id;
    }
}
