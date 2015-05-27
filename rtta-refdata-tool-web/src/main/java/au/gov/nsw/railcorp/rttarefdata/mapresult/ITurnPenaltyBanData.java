package au.gov.nsw.railcorp.rttarefdata.mapresult;

import org.springframework.data.neo4j.annotation.MapResult;
import org.springframework.data.neo4j.annotation.ResultColumn;

/**
 * Created by arash on 11/12/14.
 */
@MapResult
public interface ITurnPenaltyBanData {

    /**
     * fromNode.
     * @return fromNode
     */
    @ResultColumn("fromNode")
    String getFromNode();

    /**
     * viaNode.
     * @return viaNode
     */
    @ResultColumn("viaNode")
    String getViaNode();

    /**
     * toNode.
     * @return toNode
     */
    @ResultColumn("toNode")
    String getToNode();

    /**
     * penalty.
     * @return penalty
     */
    @ResultColumn("penalty")
    String getPenalty();
    /**
     * version.
     * @return version
     */
    @ResultColumn("version")
    String getVersion();
}
