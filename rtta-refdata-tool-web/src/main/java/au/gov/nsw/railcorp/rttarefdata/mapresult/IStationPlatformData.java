package au.gov.nsw.railcorp.rttarefdata.mapresult;

import org.springframework.data.neo4j.annotation.MapResult;
import org.springframework.data.neo4j.annotation.ResultColumn;

/**
 * Created by arash on 4/12/14.
 */
@MapResult
public interface IStationPlatformData {
    /**
     * SttionId.
     * @return stationId
     */
    @ResultColumn("stationId")
    Long getStationId();

    /**
     * StationStopId.
     * @return StationStopId.
     */
    @ResultColumn("stationStopId")
    int getStationStopId();

    /**
     * StationShortName.
     * @return stationShortName
     */
    @ResultColumn("stationShortName")
    String getStationShortName();

    /**
     * PlatformName.
     * @return platformName
     */
    @ResultColumn("platformName")
    String getPlatformName();

    /**
     * PlatformLongName.
     * @return platfromLongName
     */
    @ResultColumn("platformLongName")
    String getPlatformLongName();

    /**
     * PlatformNo.
     * @return platformNo
     */
    @ResultColumn("platformNo")
    int getPlatformNo();

    /**
     * PlatformStopId.
     * @return platformStopId
     */
    @ResultColumn("platformStopId")
    int getPlatformStopId();

    /**
     * latitude.
     * @return latitude
     */
    @ResultColumn("latitude")
    double getLatitude();

    /**
     * longtitude.
     * @return longtitude
     */
    @ResultColumn("longtitude")
    double getLongtitude();
}
