package document.docu.DeleteDocById;

/**
 * Created by jongzazaal on 21/3/2560.
 */
public class DeleteDocStatus {
    boolean response;
    String message;

    public DeleteDocStatus() {
    }

    public DeleteDocStatus(boolean response, String message) {
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
