package document.docu.PutDocById;

/**
 * Created by jongzazaal on 21/3/2560.
 */
public class PutDocStatus {
    boolean response;
    String message;

    public PutDocStatus(boolean response, String message) {
        this.response = response;
        this.message = message;
    }

    public PutDocStatus() {
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
