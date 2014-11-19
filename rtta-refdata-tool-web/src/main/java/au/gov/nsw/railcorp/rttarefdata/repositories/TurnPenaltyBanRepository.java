// RailCorp 2014
package au.gov.nsw.railcorp.rttarefdata.repositories;

import au.gov.nsw.railcorp.rttarefdata.domain.TurnPenaltyBan;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by arash on 3/11/14.
 */
public interface TurnPenaltyBanRepository extends GraphRepository<TurnPenaltyBan> {
    /**
     * Return Node Turning penalty ban.
     * @param fromNode fromNode
     * @param toNode toNode
     * @return TurnPenaltyBan
     */
    @Query("MATCH (fromNode:Node{name:{0}})-[turnPenaltyBan:TURN_PENALTY_BAN]->(toNode:Node{name:{1}}) "
            + "RETURN turnPenaltyBan")
    TurnPenaltyBan getNodeTurnPenaltyBan(String fromNode, String toNode);
}
