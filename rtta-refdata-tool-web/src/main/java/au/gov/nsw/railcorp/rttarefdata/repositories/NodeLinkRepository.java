// RailCorp 2014
package au.gov.nsw.railcorp.rttarefdata.repositories;

import au.gov.nsw.railcorp.rttarefdata.domain.NodeLink;
import au.gov.nsw.railcorp.rttarefdata.domain.RuningTime;
import au.gov.nsw.railcorp.rttarefdata.mapresult.INodeLinkData;
import au.gov.nsw.railcorp.rttarefdata.mapresult.INodeLinkRunTimeData;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.List;

/**
 * Created by arash on 3/11/14.
 */
public interface NodeLinkRepository extends GraphRepository<NodeLink> {
    /**
     * search Node link based on fromNode and toNode.
     * @param version version
     * @param fromNode fromNode
     * @param toNode toNode
     * @return NodeLink
     */
    @Query("MATCH (version:DataVersion{name:{0}})-[:VERSION_NODE]-(fromNode:Node{name:{1}})-[:NODE_LINK_FROM]->(nodeLink:NodeLink)<-[:NODE_LINK_TO]-(toNode:Node{name:{2}}) "
            + "RETURN nodeLink")
    NodeLink getNodeLink(String version, String fromNode, String toNode);

    /**
     * return all node links.
     * @param version version
     * @return List of node links
     */
    @Query("MATCH (version:DataVersion{name:{0}})-[:VERSION_NODE_LINK]-(n:NodeLink) RETURN id(n) AS nodeLinkId,"
            + "  n.fromNodeName AS fromNodeName, n.toNodeName AS toNodeName, n.length AS  length, n.sliding AS sliding, n.crossOver AS crossOver,"
            + "n.runningLine AS  runningLine, n.busEnergy AS busEnergy, n.acEnergy AS acEnergy, n.dcEnergy AS dcEnergy, n.dieselEnergy AS dieselEnergy, n.busGauge AS busGauge,"
            + "n.narrowGauge AS narrowGauge, n.standardGauge AS standardGauge, n.broadGauge AS broadGauge, n.trackSectionId AS trackSectionId")
    List<INodeLinkData> getAllNodeLinks(String version);

    /**
     * search running time for specific nodelink.
     * @param version version;
     * @param nodeId nodeId;
     * @return List of running time
     */
    @Query("MATCH (version:DataVersion{name:{0}})-[:VERSION_NODE_LINK]-(nodeLink:NodeLink)-[:NODE_LINK_RUN_TIME]->(runTime:RuningTime) WHERE id(nodeLink)={1} RETURN runTime")
    List<RuningTime> getNodeLinkRunningTimes(String version, long nodeId);

    /**
     * return all node links with their tunning time.
     * @param version version
     * @return List of node links with their running times
     */
    @Query("MATCH (version:DataVersion{name:{0}})-[:VERSION_NODE_LINK]-(n:NodeLink)-[:NODE_LINK_RUN_TIME]->(runTime:RuningTime) RETURN  id(n) AS nodeLinkId,  n.fromNodeName AS fromNodeName,"
            + "n.toNodeName AS toNodeName, n.length AS  length, n.sliding AS sliding, n.crossOver AS crossOver,n.runningLine AS  runningLine,"
            + "n.busEnergy AS busEnergy, n.acEnergy AS acEnergy, n.dcEnergy AS dcEnergy, n.dieselEnergy AS dieselEnergy, n.busGauge AS busGauge,"
            + "n.narrowGauge AS narrowGauge, n.standardGauge AS standardGauge, n.broadGauge AS broadGauge, n.trackSectionId AS trackSectionId,"
            + "runTime.stopToStop as stopToStop, runTime.passToPass as passToPass, runTime.sbId as sbId")
    List<INodeLinkRunTimeData> getAllNodeLinksAndRunningTimes(String version);

    /**
     * delete all NodeLinks with specific version.
     * @param version version;
     */
    @Query("MATCH (version:DataVersion)-[:VERSION_NODE_LINK]-(nodeLink:NodeLink) WHERE version.name={0} DELETE nodeLink")
    void deleteNodeLinkPerVersion(String version);
}
