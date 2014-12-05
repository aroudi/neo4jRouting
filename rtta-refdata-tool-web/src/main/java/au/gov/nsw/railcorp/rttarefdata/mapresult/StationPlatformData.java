package au.gov.nsw.railcorp.rttarefdata.mapresult;

/**
 * Created by arash on 5/12/14.
 */
public class StationPlatformData implements IStationPlatformData {

    private Long stationId;

    private int stationStopId;

    private String stationShortName;

    private String platformName;

    private String platformLongName;

    private int platformNo;

    private int platformStopId;

    private double latitude;

    private double longtitude;

    public Long getStationId() {
        return stationId;
    }

    public void setStationId(Long stationId) {
        this.stationId = stationId;
    }

    public int getStationStopId() {
        return stationStopId;
    }

    public void setStationStopId(int stationStopId) {
        this.stationStopId = stationStopId;
    }

    public String getStationShortName() {
        return stationShortName;
    }

    public void setStationShortName(String stationShortName) {
        this.stationShortName = stationShortName;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public String getPlatformLongName() {
        return platformLongName;
    }

    public void setPlatformLongName(String platformLongName) {
        this.platformLongName = platformLongName;
    }

    public int getPlatformNo() {
        return platformNo;
    }

    public void setPlatformNo(int platformNo) {
        this.platformNo = platformNo;
    }

    public int getPlatformStopId() {
        return platformStopId;
    }

    public void setPlatformStopId(int platformStopId) {
        this.platformStopId = platformStopId;
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
}
