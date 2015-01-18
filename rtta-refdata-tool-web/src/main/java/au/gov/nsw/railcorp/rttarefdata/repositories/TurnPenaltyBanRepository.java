// RailCorp 2014
package au.gov.nsw.railcorp.rttarefdata.repositories;

import au.gov.nsw.railcorp.rttarefdata.domain.TurnPenaltyBan;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.List;

/**
 * Created by arash on 3/11/14.
 */
public interface TurnPenaltyBanRepository extends GraphRepository<TurnPenaltyBan> {
    /**
     * Return Node Turning penalty ban.
     * @param fromNode fromNode
     * @param viaNodeName viaNodeName
     * @param toNode toNode
     * @return TurnPenaltyBan
     */
    @Query("MATCH (fromNode:Node{name:{0}})-[turnPenaltyBan:TURN_PENALTY_BAN{viaNodeName:{1}}]->(toNode:Node{name:{2}}) "
            + "RETURN turnPenaltyBan")
    TurnPenaltyBan getNodeTurnPenaltyBan(String fromNode, String viaNodeName, String toNode);

    /**
     * Return Node Turning penalty ban.
     * @param viaNodeName viaNodeName
     * @return TurnPenaltyBan
     */
    @Query("MATCH (fromNode:Node)-[turnPenaltyBan:TURN_PENALTY_BAN{viaNodeName:{0}}]->(toNode:Node) "
            + "RETURN turnPenaltyBan")
    List<TurnPenaltyBan> getAllTurnPenaltyBansPerNode(String viaNodeName);
}
