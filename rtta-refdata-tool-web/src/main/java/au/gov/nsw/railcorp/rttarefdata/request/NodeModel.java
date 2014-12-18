package au.gov.nsw.railcorp.rttarefdata.request;

/**
 * Created by arash on 17/12/2014.
 */
public class NodeModel {
    private Long nodeId;
    private String name;
    private String longName;
    private String platformName;
    private boolean dummy;
    private boolean junction;
    private boolean workingTimingPoint;
    private boolean publicTimingPoint;
    private boolean endOfLine;
    private String wellDuration;
    private String upRecoveryDuration;
    private String downRecoveryDuration;
    private double length;
    private Double latitude;
    private Double longtitude;

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public boolean isDummy() {
        return dummy;
    }

    public void setDummy(boolean dummy) {
        this.dummy = dummy;
    }

    public boolean isJunction() {
        return junction;
    }

    public void setJunction(boolean junction) {
        this.junction = junction;
    }

    public boolean isWorkingTimingPoint() {
        return workingTimingPoint;
    }

    public void setWorkingTimingPoint(boolean workingTimingPoint) {
        this.workingTimingPoint = workingTimingPoint;
    }

    public boolean isPublicTimingPoint() {
        return publicTimingPoint;
    }

    public void setPublicTimingPoint(boolean publicTimingPoint) {
        this.publicTimingPoint = publicTimingPoint;
    }

    public boolean isEndOfLine() {
        return endOfLine;
    }

    public void setEndOfLine(boolean endOfLine) {
        this.endOfLine = endOfLine;
    }

    public String getWellDuration() {
        return wellDuration;
    }

    public void setWellDuration(String wellDuration) {
        this.wellDuration = wellDuration;
    }

    public String getUpRecoveryDuration() {
        return upRecoveryDuration;
    }

    public void setUpRecoveryDuration(String upRecoveryDuration) {
        this.upRecoveryDuration = upRecoveryDuration;
    }

    public String getDownRecoveryDuration() {
        return downRecoveryDuration;
    }

    public void setDownRecoveryDuration(String downRecoveryDuration) {
        this.downRecoveryDuration = downRecoveryDuration;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(Double longtitude) {
        this.longtitude = longtitude;
    }
}
