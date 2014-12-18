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
    /**
     * id of new added record.
     */
    protected Long id;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
