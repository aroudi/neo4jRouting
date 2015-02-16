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
     * Return all RailNet Nodes.
     * @return INodeData
     */
    @Query(
            "MATCH (n:Node{railNetNode:true}) RETURN id(n) AS nodeId,  n.name AS name, n.longName AS longName, n.platfromName AS  platformName"
                    + ", n.isDummy AS dummy, n.isJunction AS junction, n.isWorkingTimingPoint AS  workingTimingPoint, n.isPublicTimingPoint AS publicTimingPoint"
                    + ", n.isEndOfLine AS endOfLine, n.dWellDuration AS wellDuration, n.upRecoveryDuration AS upRecoveryDuration, n.downRecoveryDuration AS downRecoveryDuration"
                    + ", n.length AS length, n.latitude AS latitude, n.longitude AS longtitude, n.masterJunctionName as masterJunctionName, n.masterTimingPointName as masterTimingPointName"
    )
    List<INodeData> getAllRailNetNodes();

    /**
     * Return all Nodes.
     * @return INodeData
     */
    @Query(
            "MATCH (n:Node) RETURN id(n) AS nodeId,  n.name AS name, n.longName AS longName, n.platfromName AS  platformName"
                    + ", n.isDummy AS dummy, n.isJunction AS junction, n.isWorkingTimingPoint AS  workingTimingPoint, n.isPublicTimingPoint AS publicTimingPoint"
                    + ", n.isEndOfLine AS endOfLine, n.dWellDuration AS wellDuration, n.upRecoveryDuration AS upRecoveryDuration, n.downRecoveryDuration AS downRecoveryDuration"
                    + ", n.length AS length, n.latitude AS latitude, n.longitude AS longtitude, n.masterJunctionName as masterJunctionName, n.masterTimingPointName as masterTimingPointName"
    )
    List<INodeData> getAllNodes();

    /**
     * Return all Nodes.
     * @param fromNode fromNode
     * @param toNode toNode
     * @return List of nodes
     */
    @Query(
            "MATCH p=allShortestPaths((fromNode:Node{name:{0}})-[r:NODE_LINKAGE*..500]->(toNode:Node{name:{1}})) RETURN extract(n IN nodes(p)| n) AS paths"
    )
    List<List<org.neo4j.graphdb.Node>> findAllShortestPaths(String fromNode, String toNode);
    /**
     * Return Master Timing Point for a node.
     * @param nodeName nodeName
     * @return Master Timing Point Node
     */
    @Query("MATCH (node:Node{name:{0}})-[:MASTER_TIMING_POINT]->(mtp:Node) RETURN mtp")
    Node getMasterTimingPointNode(String nodeName);

    /**
     * Return Master Timing Point for a node.
     * @param nodeName nodeName
     * @return Master Timing Point Node
     */
    @Query("MATCH (node:Node{name:{0}})-[:MASTER_JUNCTION]->(mj:Node) RETURN mj")
    Node getMasterJunctionNode(String nodeName);
}
