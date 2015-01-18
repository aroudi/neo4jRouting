package au.gov.nsw.railcorp.rttarefdata.mapresult;

import org.springframework.data.neo4j.annotation.MapResult;
import org.springframework.data.neo4j.annotation.ResultColumn;

/**
 * Created by arash on 15/01/2015.
 */
@MapResult
public interface INodeLinkRunTimeData {
    /**
     * nodeLinkId.
     * @return nodeLinkId
     */
    @ResultColumn("nodeLinkId")
    Long getNodeLinkId();

    /**
     * fromNodeName.
     * @return fromNodeName
     */
    @ResultColumn("fromNodeName")
    String getFromNodeName();

    /**
     * toNodeName.
     * @return toNodeName
     */
    @ResultColumn("toNodeName")
    String getToNodeName();

    /**
     * length.
     * @return length
     */
    @ResultColumn("length")
    long getLength();

    /**
     * sliding.
     * @return sliding
     */
    @ResultColumn("sliding")
    boolean getSliding();

    /**
     * crossOver.
     * @return crossOver
     */
    @ResultColumn("crossOver")
    boolean getCrossOver();

    /**
     * runningLine.
     * @return runningLine
     */
    @ResultColumn("runningLine")
    boolean getRunningLine();

    /**
     * busEnergy.
     * @return busEnergy
     */
    @ResultColumn("busEnergy")
    boolean getBusEnergy();

    /**
     * acEnergy.
     * @return acEnergy
     */
    @ResultColumn("acEnergy")
    boolean getAcEnergy();

    /**
     * dcEnergy.
     * @return dcEnergy
     */
    @ResultColumn("dcEnergy")
    boolean getDcEnergy();

    /**
     * dieselEnergy.
     * @return dieselEnergy
     */
    @ResultColumn("dieselEnergy")
    boolean getDieselEnergy();

    /**
     * busGauge.
     * @return busGauge
     */
    @ResultColumn("busGauge")
    boolean getBusGauge();

    /**
     * narrowGauge.
     * @return narrowGauge
     */
    @ResultColumn("narrowGauge")
    boolean getNarrowGauge();

    /**
     * standardGauge.
     * @return standardGauge
     */
    @ResultColumn("standardGauge")
    boolean getStandardGauge();

    /**
     * broadGauge.
     * @return broadGauge
     */
    @ResultColumn("broadGauge")
    boolean getBroadGauge();

    /**
     * trackSectionId.
     * @return trackSectionId
     */
    @ResultColumn("trackSectionId")
    String getTrackSectionId();

    /**
     * stopToStop.
     * @return String
     */
    @ResultColumn("stopToStop")
    String getStopToStop();

    /**
     * passToPass.
     * @return String
     */
    @ResultColumn("passToPass")
    String getPassToPass();

    /**
     * sbId.
     * @return String
     */
    @ResultColumn("sbId")
    String getSbId();
}
