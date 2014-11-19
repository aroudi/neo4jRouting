package au.gov.nsw.railcorp.rttarefdata.repositories;

import au.gov.nsw.railcorp.rttarefdata.domain.NodeLinkage;
import au.gov.nsw.railcorp.rttarefdata.domain.TurnPenaltyBan;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by arash on 14/11/14.
 */
public interface NodeLinkageRepository extends GraphRepository<NodeLinkage> {

    /**
     * Return Node Turning penalty ban.
     * @param fromNode fromNode
     * @param toNode toNode
     * @return NodeLinkage
     */
    @Query("MATCH (fromNode:Node{name:{0}})-[nodeLinkage:NODE_LINKAGE]->(toNode:Node{name:{1}}) "
            + "RETURN nodeLinkage")
    NodeLinkage getNodeLinkage(String fromNode, String toNode);
    /**
     * Return Node Turning penalty ban.
     * @param fromNode fromNode
     * @param toNode toNode
     * @return NodeLinkage
     */
    @Query("MATCH (fromNode:Node{name:{0}})-[nodeLinkage:NODE_LINKAGE]-(toNode:Node{name:{1}}) "
            + "RETURN nodeLinkage")
    NodeLinkage getNodeLinkageNonTrackSection(String fromNode, String toNode);

}
