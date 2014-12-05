package au.gov.nsw.railcorp.rttarefdata.mapresult;

/**
 * Created by arash on 5/12/14.
 */
public class RefData implements IRefData {

    private Long refDataId;
    private String refDataCode;
    private String refDataName;

    public Long getRefDataId() {
        return refDataId;
    }

    public void setRefDataId(Long refDataId) {
        this.refDataId = refDataId;
    }

    public String getRefDataCode() {
        return refDataCode;
    }

    public void setRefDataCode(String refDataCode) {
        this.refDataCode = refDataCode;
    }

    public String getRefDataName() {
        return refDataName;
    }

    public void setRefDataName(String refDataName) {
        this.refDataName = refDataName;
    }
}
