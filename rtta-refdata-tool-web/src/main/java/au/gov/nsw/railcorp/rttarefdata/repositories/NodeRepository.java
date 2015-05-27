// RailCorp 2014
package au.gov.nsw.railcorp.rttarefdata.repositories;

import au.gov.nsw.railcorp.rttarefdata.domain.Node;
import au.gov.nsw.railcorp.rttarefdata.mapresult.INodeData;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.List;

/**
 * Created by arash on 29/10/14.
 */
public interface NodeRepository extends GraphRepository<Node> {

    /**
     * Return all RailNet Nodes per version.
     * @param version version
     * @return INodeData
     */
    @Query(
            "MATCH (version:DataVersion{name:{0}})-[:VERSION_NODE]-(n:Node{railNetNode:true}) RETURN id(n) AS nodeId,  n.name AS name, n.longName AS longName, n.platfromName AS  platformName"
                    + ", n.isDummy AS dummy, n.isJunction AS junction, n.isWorkingTimingPoint AS  workingTimingPoint, n.isPublicTimingPoint AS publicTimingPoint"
                    + ", n.isEndOfLine AS endOfLine, n.dWellDuration AS wellDuration, n.upRecoveryDuration AS upRecoveryDuration, n.downRecoveryDuration AS downRecoveryDuration"
                    + ", n.length AS length, n.latitude AS latitude, n.longitude AS longtitude, n.masterJunctionName as masterJunctionName, n.masterTimingPointName as masterTimingPointName"
    )
    List<INodeData> getAllRailNetNodes(String version);

    /**
     * Return all Nodes.
     * @param version version
     * @return INodeData
     */
    @Query(
            "MATCH (version:DataVersion{name:{0}})-[:VERSION_NODE]-(n:Node) RETURN id(n) AS nodeId,  n.name AS name, n.longName AS longName, n.platfromName AS  platformName"
                    + ", n.isDummy AS dummy, n.isJunction AS junction, n.isWorkingTimingPoint AS  workingTimingPoint, n.isPublicTimingPoint AS publicTimingPoint"
                    + ", n.isEndOfLine AS endOfLine, n.dWellDuration AS wellDuration, n.upRecoveryDuration AS upRecoveryDuration, n.downRecoveryDuration AS downRecoveryDuration"
                    + ", n.length AS length, n.latitude AS latitude, n.longitude AS longtitude, n.masterJunctionName as masterJunctionName, n.masterTimingPointName as masterTimingPointName "
                    + ", n.gtfsStopId as gtfsStopId"
    )
    List<INodeData> getAllNodes(String version);

    /**
     * Return all Nodes.
     * @param version version
     * @param fromNode fromNode
     * @param toNode toNode
     * @return List of nodes
     */
    //ToDo add version to shortest path
    @Query("MATCH sp = allShortestPaths((fromNode:Node{name:{1}})-[:NODE_LINKAGE*..1000{version:{0}}]->(toNode:Node{name:{2}})) RETURN extract(n IN nodes(sp)| n) AS paths")
    List<List<org.neo4j.graphdb.Node>> findAllShortestPaths(String version, String fromNode, String toNode);
    /**
     * Return Master Timing Point for a node.
     * @param version version
     * @param nodeName nodeName
     * @return Master Timing Point Node
     */
    @Query("MATCH (version:DataVersion{name:{0}})-[:VERSION_NODE]-(node:Node{name:{1}})-[:MASTER_TIMING_POINT]->(mtp:Node) RETURN mtp")
    Node getMasterTimingPointNode(String version, String nodeName);

    /**
     * Return Master Timing Point for a node.
     * @param version version
     * @param nodeName nodeName
     * @return Master Timing Point Node
     */
    @Query("MATCH (version:DataVersion{name:{0}})-[:VERSION_NODE]-(node:Node{name:{1}})-[:MASTER_JUNCTION]->(mj:Node) RETURN mj")
    Node getMasterJunctionNode(String version, String nodeName);

    /**
     * Return node per name.
     * @param version version
     * @param nodeName nodeName
     * @return node
     */
    @Query("MATCH (version:DataVersion{name:{0}})-[:VERSION_NODE]-(node:Node{name:{1}}) RETURN node")
    Node getNodePerName(String version, String nodeName);

    /**
     * delete all Nodes with specific version.
     * @param version version;
     */
    @Query("MATCH (version:DataVersion)-[:VERSION_NODE]-(node:Node) WHERE version.name={0} DELETE node")
    void deleteNodesPerVersion(String version);
    /**
     * delete all Node's master timing point with specific version.
     * @param version version;
     */
    @Query("MATCH (version:DataVersion)-[:VERSION_NODE]-(node:Node)-[mtp:MASTER_TIMING_POINT]->(:Node) WHERE version.name={0} DELETE mtp, node")
    void deleteNodeMasterTimingPoint(String version);

    /**
     * Return all paths between 2 nodes.
     * @param version version
     * @param fromNode fromNode
     * @param toNode toNode
     * @return List of nodes
     */
    //ToDo add version to shortest path
    @Query("MATCH p=(fromNode:Node{name:{1}})-[:NODE_LINKAGE*..15{version:{0}}]->(toNode:Node{name:{2}}) "
            + "WHERE ALL (n in nodes(p) where length(filter (m in nodes(p) where m=n))=1) RETURN distinct extract(n IN nodes(p)| n) AS paths")
    List<List<org.neo4j.graphdb.Node>> findAllPaths(String version, String fromNode, String toNode);

}
