// RailCorp 2014
package au.gov.nsw.railcorp.rttarefdata.repositories;

import au.gov.nsw.railcorp.rttarefdata.domain.NodeLink;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by arash on 3/11/14.
 */
public interface NodeLinkRepository extends GraphRepository<NodeLink> {
    /**
     * search Node link based on fromNode and toNode.
     * @param fromNode fromNode
     * @param toNode toNode
     * @return NodeLink
     */
    @Query("MATCH (fromNode:Node{name:{0}})-[:NODE_LINK_FROM]->(nodeLink:NodeLink)<-[:NODE_LINK_TO]-(toNode:Node{name:{1}}) "
            + "RETURN nodeLink")
    NodeLink getNodeLink(String fromNode, String toNode);

    /**
     * Return node link by FromNodeName and toNodeName.
     * @param fromNodeName fromNodeName
     * @param toNodeName toNodeName
     * @return NodeLink
     */
    NodeLink findByFromNodeNameAndToNodeName(String fromNodeName, String toNodeName);
}
