package au.gov.nsw.railcorp.rttarefdata.domain;

import org.springframework.data.neo4j.annotation.*;

/**
 * Created by arash on 14/11/14.
 */
@RelationshipEntity(type = Links.NODE_LINKAGE)

public class NodeLinkage {
    @GraphId
    private Long nodeLinkageId;

    @Indexed
    private long length;
    private boolean siding;
    private boolean crossOver;
    private boolean runningLine;
    @Indexed
    private int trackSectionId;
    private boolean busEnergy;
    private boolean acEnergy;
    private boolean dcEnergy;
    private boolean dieselEnergy;
    private boolean busGauge;
    private boolean narrowGauge;
    private boolean standardGauge;
    private boolean broadGauge;
    @Indexed
    private String direction;
    @Indexed
    private String penaltyBan= "PT6S";

    @StartNode
    private Node fromNode;

    @EndNode
    private Node toNode;

    public Long getNodeLinkageId() {
        return nodeLinkageId;
    }

    public void setNodeLinkageId(Long nodeLinkageId) {
        this.nodeLinkageId = nodeLinkageId;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public boolean isSiding() {
        return siding;
    }

    public void setSiding(boolean siding) {
        this.siding = siding;
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

    public int getTrackSectionId() {
        return trackSectionId;
    }

    public void setTrackSectionId(int trackSectionId) {
        this.trackSectionId = trackSectionId;
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

    public boolean isDcEnergy() {
        return dcEnergy;
    }

    public void setDcEnergy(boolean dcEnergy) {
        this.dcEnergy = dcEnergy;
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

    public Node getFromNode() {
        return fromNode;
    }

    public void setFromNode(Node fromNode) {
        this.fromNode = fromNode;
    }

    public Node getToNode() {
        return toNode;
    }

    public void setToNode(Node toNode) {
        this.toNode = toNode;
    }

    public String getPenaltyBan() {
        return penaltyBan;
    }

    public void setPenaltyBan(String penaltyBan) {
        this.penaltyBan = penaltyBan;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
