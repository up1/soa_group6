package authentication.auth;

/**
 * Created by jongzazaal on 6/3/2560.
 */
public class UserPass {
    private String user_username, user_password;

    private int user_id;

    public UserPass(String user_username, String user_password, int user_id) {
        this.user_username = user_username;
        this.user_password = user_password;
        this.user_id = user_id;
    }

    public UserPass() {
    }

    public String getUser_username() {
        return user_username;
    }

    public void setUser_username(String user_username) {
        this.user_username = user_username;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
