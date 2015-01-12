// RailCorp 2014
package au.gov.nsw.railcorp.rttarefdata.domain;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

import java.util.Collection;

/**
 * Created by arash on 3/11/14.
 */
@NodeEntity
public class NodeLink {
    @GraphId
    private Long nodeLinkId;

    private long length;
    private boolean sliding;
    private boolean crossOver;
    private boolean runningLine;
    private String fromNodeName;
    private String toNodeName;
    private boolean busEnergy;
    private boolean acEnergy;
    private boolean dcEnergy;
    private boolean dieselEnergy;
    private boolean busGauge;
    private boolean narrowGauge;
    private boolean standardGauge;
    private boolean broadGauge;
    private String trackSectionId;

    //@Fetch
    @RelatedTo(type = Links.NODE_LINK_FROM, direction = Direction.INCOMING)
    private Node fromNode;

    //@Fetch
    @RelatedTo(type = Links.NODE_LINK_TO, direction = Direction.INCOMING)
    private Node toNode;

    @RelatedTo(type = Links.NODE_LINK_RUN_TIME, direction = Direction.OUTGOING)
    private Collection<RuningTime> runingTimes;

    /**
     * Constructor.
     * @param length length
     * @param sliding sliding
     * @param crossOver crossOver
     * @param runningLine runningLine
     */
    public NodeLink(long length, boolean sliding, boolean crossOver, boolean runningLine) {
        this.length = length;
        this.sliding = sliding;
        this.crossOver = crossOver;
        this.runningLine = runningLine;
    }


    /**
     * Default Constructor.
     */
    public NodeLink() {
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public boolean isSliding() {
        return sliding;
    }

    public void setSliding(boolean sliding) {
        this.sliding = sliding;
    }

    public boolean isCrossOver() {
        return crossOver;
    }

    public void setCrossOver(boolean crossOver) {
        this.crossOver = crossOver;
    }

    public boolean isRunningLine() {
        return runningLine;
    }

    public void setRunningLine(boolean runningLine) {
        this.runningLine = runningLine;
    }

    public Node getFromNode() {
        return fromNode;
    }

    public void setFromNode(Node fromNode) {
        this.fromNode = fromNode;
    }

    public Node getToNode() {
        return toNode;
    }

    public void setToNode(Node nodeLinkTo) {
        this.toNode = nodeLinkTo;
    }


    public Collection<RuningTime> getRunningTimes() {
        return runingTimes;
    }

    public void setRunningTimes(Collection<RuningTime> runningTimes) {
        this.runingTimes = runningTimes;
    }

    public String getFromNodeName() {
        return fromNodeName;
    }

    public void setFromNodeName(String fromNodeName) {
        this.fromNodeName = fromNodeName;
    }

    public String getToNodeName() {
        return toNodeName;
    }

    public void setToNodeName(String toNodeName) {
        this.toNodeName = toNodeName;
    }

    public boolean isBusEnergy() {
        return busEnergy;
    }

    public void setBusEnergy(boolean busEnergy) {
        this.busEnergy = busEnergy;
    }

    public boolean isAcEnergy() {
        return acEnergy;
    }

    public void setAcEnergy(boolean acEnergy) {
        this.acEnergy = acEnergy;
    }

    public boolean isDieselEnergy() {
        return dieselEnergy;
    }

    public void setDieselEnergy(boolean dieselEnergy) {
        this.dieselEnergy = dieselEnergy;
    }

    public boolean isBusGauge() {
        return busGauge;
    }

    public void setBusGauge(boolean busGauge) {
        this.busGauge = busGauge;
    }

    public boolean isNarrowGauge() {
        return narrowGauge;
    }

    public void setNarrowGauge(boolean narrowGauge) {
        this.narrowGauge = narrowGauge;
    }

    public boolean isStandardGauge() {
        return standardGauge;
    }

    public void setStandardGauge(boolean standardGauge) {
        this.standardGauge = standardGauge;
    }

    public boolean isBroadGauge() {
        return broadGauge;
    }

    public void setBroadGauge(boolean broadGauge) {
        this.broadGauge = broadGauge;
    }

    public String getTrackSectionId() {
        return trackSectionId;
    }

    public void setTrackSectionId(String trackSectionId) {
        this.trackSectionId = trackSectionId;
    }

    public boolean isDcEnergy() {
        return dcEnergy;
    }

    public void setDcEnergy(boolean dcEnergy) {
        this.dcEnergy = dcEnergy;
    }
}
