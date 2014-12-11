package au.gov.nsw.railcorp.rttarefdata.response;

/**
 * Created by arash on 11/12/14.
 */
public class NetworkLineResponse extends Response {
    private Long lineId;

    public Long getLineId() {
        return lineId;
    }

    public void setLineId(Long lineId) {
        this.lineId = lineId;
    }
}
