package au.gov.nsw.railcorp.rttarefdata.request;

/**
 * Created by arash on 2/12/14.
 */
public class StationModel {

    private Long stationId;
    private String shortName;
    private String longName;
    private int gtfsStopId;
    private Double latitude;
    private Double longtitude;
    private boolean interchangePoint;

    public Long getStationId() {
        return stationId;
    }

    public void setStationId(Long stationId) {
        this.stationId = stationId;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public int getGtfsStopId() {
        return gtfsStopId;
    }

    public void setGtfsStopId(int gtfsStopId) {
        this.gtfsStopId = gtfsStopId;
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

    public boolean isInterchangePoint() {
        return interchangePoint;
    }

    public void setInterchangePoint(boolean interchangePoint) {
        this.interchangePoint = interchangePoint;
    }
}
