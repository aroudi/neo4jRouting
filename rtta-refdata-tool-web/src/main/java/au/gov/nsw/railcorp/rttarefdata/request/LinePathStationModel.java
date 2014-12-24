package au.gov.nsw.railcorp.rttarefdata.request;

/**
 * Created by arash on 22/12/2014.
 */
public class LinePathStationModel {
    private Long stationId;
    private String name;
    private String longName;
    private Double latitude;
    private Double longtitude;
    private boolean pathMatchInclude;
    private int sequence;

    public Long getStationId() {
        return stationId;
    }

    public void setStationId(Long stationId) {
        this.stationId = stationId;
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

    public boolean isPathMatchInclude() {
        return pathMatchInclude;
    }

    public void setPathMatchInclude(boolean pathMatchInclude) {
        this.pathMatchInclude = pathMatchInclude;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }
}
