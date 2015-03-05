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

}
