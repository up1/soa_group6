package authentication.token;

/**
 * Created by jongzazaal on 3/4/2560.
 */
public class Token {
    private String username, password;

    public Token() {
    }

    public Token(String username, String password) {
        this.username = username;
        this.password = password;
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
}
