package au.gov.nsw.railcorp.rttarefdata.response;

/**
 * Created by arash on 2/12/14.
 */
public class Response {
    /**
     * status code.
     */
    protected int status;
    /**
     * message.
     */
    protected String message;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
