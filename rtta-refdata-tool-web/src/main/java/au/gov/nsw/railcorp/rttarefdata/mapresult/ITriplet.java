package au.gov.nsw.railcorp.rttarefdata.mapresult;

import org.springframework.data.neo4j.annotation.MapResult;
import org.springframework.data.neo4j.annotation.ResultColumn;

/**
 * Created by arash on 5/12/14.
 */
@MapResult
public interface ITriplet {
    /**
     * inStopId.
     * @return inStopId
     */
    @ResultColumn("inStopId")
    int getInStopId();

    /**
     * stopId.
     * @return stopId
     */
    @ResultColumn("stopId")
    int getStopId();

    /**
     * outStopId.
     * @return outStopId
     */
    @ResultColumn("outStopId")
    int getOutStopId();

    /**
     * inStopName.
     * @return inStopName
     */
    @ResultColumn("inStopName")
    String getInStopName();

    /**
     * outStopName.
     * @return outStopName
     */
    @ResultColumn("outStopName")
    String getOutStopName();

    /**
     * stopName.
     * @return stopName
     */
    @ResultColumn("stopName")
    String getStopName();

    /**
     * reversible.
     * @return reversible
     */
    @ResultColumn("reversible")
    boolean getReversible();
}
