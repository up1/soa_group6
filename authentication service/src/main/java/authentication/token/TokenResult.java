package authentication.token;

/**
 * Created by jongzazaal on 3/4/2560.
 */
public class TokenResult {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public TokenResult(String token) {
        this.token = token;
    }

    public TokenResult() {
    }
}
