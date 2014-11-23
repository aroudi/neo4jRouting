// RailCorp 2014
package au.gov.nsw.railcorp.rttarefdata.domain;

import org.springframework.data.neo4j.annotation.*;

import javax.xml.datatype.Duration;

/**
 * Created by arash on 3/11/14.
 */
@RelationshipEntity(type = Links.TURN_PENALTY_BAN)
public class TurnPenaltyBan {
    @GraphId
    private Long turnPenaltyBanId;

    @StartNode
    private Node fromNode;

    @EndNode
    private Node toNode;

    private String penalty;
    @Indexed
    private String viaNodeName;

    /**
     * Constructor.
     * @param fromNode fromNode
     * @param toNode toNode
     * @param penalty penalty
     */
    public TurnPenaltyBan(Node fromNode, Node toNode, String penalty) {
        this.fromNode = fromNode;
        this.toNode = toNode;
        this.penalty = penalty;
    }

    /**
     * Default constructor.
     */
    public TurnPenaltyBan() {
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

    public String getPenalty() {
        return penalty;
    }

    public void setPenalty(String penalty) {
        this.penalty = penalty;
    }

    public Long getTurnPenaltyBanId() {
        return turnPenaltyBanId;
    }

    public void setTurnPenaltyBanId(Long turnPenaltyBanId) {
        this.turnPenaltyBanId = turnPenaltyBanId;
    }

    public String getViaNodeName() {
        return viaNodeName;
    }

    public void setViaNodeName(String viaNodeName) {
        this.viaNodeName = viaNodeName;
    }
}
