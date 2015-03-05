package au.gov.nsw.railcorp.rttarefdata.repositories;

import au.gov.nsw.railcorp.rttarefdata.domain.NodeLinkage;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by arash on 14/11/14.
 */
public interface NodeLinkageRepository extends GraphRepository<NodeLinkage> {

    /**
     * Return Node Turning penalty ban.
     * @param version version
     * @param fromNode fromNode
     * @param toNode toNode
     * @return NodeLinkage
     */
    @Query("MATCH (version:DataVersion{name:{0}})-[:VERSION_NODE]-(fromNode:Node{name:{1}})-[nodeLinkage:NODE_LINKAGE]->(toNode:Node{name:{2}}) "
            + "RETURN nodeLinkage")
    NodeLinkage getNodeLinkage(String version, String fromNode, String toNode);
    /**
     * Return Node Turning penalty ban.
     * @param version version
     * @param fromNode fromNode
     * @param toNode toNode
     * @return NodeLinkage
     */
    @Query("MATCH (version:DataVersion{name:{0}})-[:VERSION_NODE]-(fromNode:Node{name:{1}})-[nodeLinkage:NODE_LINKAGE]-(toNode:Node{name:{2}}) "
            + "RETURN nodeLinkage")
    NodeLinkage getNodeLinkageNonTrackSection(String version, String fromNode, String toNode);

    /**
     * delete all NodeLinkage with specific version.
     * @param version version;
     */
    @Query("MATCH (nodeLinkage:NodeLinkage) WHERE nodeLinkage.version={0} DELETE nodeLinkage")
    void deleteNodeLinkagePerVersion(String version);

}
