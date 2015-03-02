package au.gov.nsw.railcorp.rttarefdata.session;

import au.gov.nsw.railcorp.rttarefdata.domain.DataVersion;

/**
 * Created by arash on 2/03/2015.
 */
public class SessionState {
    private DataVersion workingVersion;
    private DataVersion activeVersion;

    /**
     * Default Constructor.
     */
    public SessionState() {
    }

    public DataVersion getWorkingVersion() {
        return workingVersion;
    }

    public void setWorkingVersion(DataVersion workingVersion) {
        this.workingVersion = workingVersion;
    }

    public DataVersion getActiveVersion() {
        return activeVersion;
    }

    public void setActiveVersion(DataVersion activeVersion) {
        this.activeVersion = activeVersion;
    }

}
