package au.gov.nsw.railcorp.rttarefdata.mapresult;

import org.springframework.data.neo4j.annotation.MapResult;
import org.springframework.data.neo4j.annotation.ResultColumn;

/**
 * Created by arash on 22/12/2014.
 */
@MapResult
public interface ILinePathStation {

    /**
     * stationId.
     * @return stationId
     */

    @ResultColumn("stationId")
    Long getStationId();

    /**
     * stationName.
     * @return stationName
     */

    @ResultColumn("name")
    String getName();

    /**
     * longName.
     * @return longName
     */

    @ResultColumn("longName")
    String getLongName();

    /**
     * stationLongName.
     * @return stationLongName
     */

    @ResultColumn("latitude")
    Double getLatitude();
    /**
     * longtitude.
     * @return longtitude
     */

    @ResultColumn("longtitude")
    Double getLongtitude();

    /**
     * interchagePoint.
     * @return interchangePoint
     */

    @ResultColumn("interchangePoint")
    boolean getInterchangePoint();

    /**
     * interchange point.
     * @return interchangePoint
     */

    @ResultColumn("pathMatchInclude")
    boolean getPathMatchInclude();

    /**
     * sequence.
     * @return sequence
     */

    @ResultColumn("sequence")
    int getSequence();
}
