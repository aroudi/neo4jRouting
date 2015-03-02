package au.gov.nsw.railcorp.rttarefdata.response;

/**
 * Created by arash on 26/02/2015.
 */
public class DataVersionResponse extends Response {
    private boolean active;
    private String createDate;

    public boolean isActive() {
        return active;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
