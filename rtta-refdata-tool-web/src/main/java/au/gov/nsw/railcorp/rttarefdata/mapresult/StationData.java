package au.gov.nsw.railcorp.rttarefdata.mapresult;

/**
 * Created by arash on 5/12/14.
 */
public class StationData {
    private Long stationId;
    private String shortName;
    private String longName;
    private int gtfsStopId;
    private double latitude;
    private double longtitude;
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

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }

    public boolean isInterchangePoint() {
        return interchangePoint;
    }

    public void setInterchangePoint(boolean interchangePoint) {
        this.interchangePoint = interchangePoint;
    }
}
