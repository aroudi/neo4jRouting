package au.gov.nsw.railcorp.rttarefdata.mapresult;

import org.springframework.data.neo4j.annotation.MapResult;
import org.springframework.data.neo4j.annotation.ResultColumn;

/**
 * Created by arash on 5/12/14.
 */
@MapResult
public interface IStationData {
    /**
     * StationId.
     * @return stationId
     */
    @ResultColumn("stationId")
    Long getStationId();

    /**
     * shortName.
     * @return shortName
     */
    @ResultColumn("shortName")
    String getShortName();

    /**
     * longName.
     * @return longName
     */
    @ResultColumn("longName")
    String getLongName();

    /**
     * gtfsStopId.
     * @return gtfsStopId
     */
    @ResultColumn("gtfsStopId")
    int getGtfsStopId();

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

    /**
     * interchangePoint.
     * @return interchangePoint
     */
    @ResultColumn("interchangePoint")
    boolean getInterchangePoint();



}
