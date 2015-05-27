// RailCorp 2014
package au.gov.nsw.railcorp.rttarefdata.repositories;

import au.gov.nsw.railcorp.rttarefdata.domain.TurnPenaltyBan;
import au.gov.nsw.railcorp.rttarefdata.mapresult.ITurnPenaltyBanData;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.List;

/**
 * Created by arash on 3/11/14.
 */
public interface TurnPenaltyBanRepository extends GraphRepository<TurnPenaltyBan> {
    /**
     * Return Node Turning penalty ban.
     * @param version version
     * @param fromNode fromNode
     * @param viaNodeName viaNodeName
     * @param toNode toNode
     * @return TurnPenaltyBan
     */
    @Query("MATCH (version:DataVersion{name:{0}})-[:VERSION_NODE]-(fromNode:Node{name:{1}})-[turnPenaltyBan:TURN_PENALTY_BAN{viaNodeName:{2}}]->(toNode:Node{name:{3}}) "
            + "RETURN turnPenaltyBan")
    TurnPenaltyBan getNodeTurnPenaltyBan(String version, String fromNode, String viaNodeName, String toNode);

    /**
     * Return Node Turning penalty ban.
     * @param version version
     * @param viaNodeName viaNodeName
     * @return TurnPenaltyBan
     */
    @Query("MATCH (version:DataVersion{name:{0}})-[:VERSION_NODE]-(fromNode:Node)-[turnPenaltyBan:TURN_PENALTY_BAN{viaNodeName:{0}}]->(toNode:Node) "
            + "RETURN turnPenaltyBan")
    List<TurnPenaltyBan> getAllTurnPenaltyBansPerNode(String version, String viaNodeName);

    /**
     * delete all Turning Penalty Ban with specific version.
     * @param version version;
     */
    @Query("MATCH (tpb:TurnPenaltyBan) WHERE tpb.version={0} DELETE tpb")
    void deleteTurnPenaltyBanPerVersion(String version);

    /**
     * return all Turning Penalty Ban with specific version.
     * @return TurnPenaltyBanList
     */
    @Query("MATCH (fromNode:Node)-[turnPenaltyBan:TURN_PENALTY_BAN]->(toNode:Node) RETURN fromNode.name As fromNode,"
            + "turnPenaltyBan.viaNodeName As viaNode, toNode.name As toNode, turnPenaltyBan.penalty As penalty, turnPenaltyBan.version As version")
    List<ITurnPenaltyBanData> getAllTurnPenaltyBan();

}
