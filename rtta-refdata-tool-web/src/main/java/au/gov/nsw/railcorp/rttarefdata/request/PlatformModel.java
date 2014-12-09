package au.gov.nsw.railcorp.rttarefdata.request;

import au.gov.nsw.railcorp.rttarefdata.mapresult.RefData;

/**
 * Created by arash on 8/12/14.
 */
public class PlatformModel {
    private RefData station;
    private String platformName;
    private String platformLongName;
    private int platformNo;
    private Double latitude;
    private Double longtitude;
    private int platformStopId;

    public RefData getStation() {
        return station;
    }

    public void setStation(RefData station) {
        this.station = station;
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

    public int getPlatformStopId() {
        return platformStopId;
    }

    public void setPlatformStopId(int platformStopId) {
        this.platformStopId = platformStopId;
    }
}
