package document.docu.PostDocument;

/**
 * Created by jongzazaal on 19/3/2560.
 */
public class PostDocStatus {
    boolean response;
    String message;

    public PostDocStatus() {
    }

    public PostDocStatus(boolean response, String message) {
        this.response = response;
        this.message = message;
    }

    public boolean isResponse() {
        return response;
    }

    public void setResponse(boolean response) {
        this.response = response;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
