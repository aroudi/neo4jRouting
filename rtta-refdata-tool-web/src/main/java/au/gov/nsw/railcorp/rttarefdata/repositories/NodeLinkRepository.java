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

    /**
     * return all node links.
     * @return List of node links
     */
    @Query("MATCH (n:NodeLink) RETURN id(n) AS nodeLinkId,  n.fromNodeName AS fromNodeName, n.toNodeName AS toNodeName, n.length AS  length, n.sliding AS sliding, n.crossOver AS crossOver,"
            + "n.runningLine AS  runningLine, n.busEnergy AS busEnergy, n.acEnergy AS acEnergy, n.dcEnergy AS dcEnergy, n.dieselEnergy AS dieselEnergy, n.busGauge AS busGauge,"
            + "n.narrowGauge AS narrowGauge, n.standardGauge AS standardGauge, n.broadGauge AS broadGauge, n.trackSectionId AS trackSectionId")
    List<INodeLinkData> getAllNodeLinks();

    /**
     * search running time for specific nodelink.
     * @param nodeId nodeId;
     * @return List of running time
     */
    @Query("MATCH (nodeLink:NodeLink)-[:NODE_LINK_RUN_TIME]->(runTime:RuningTime) WHERE id(nodeLink)={0} RETURN runTime")
    List<RuningTime> getNodeLinkRunningTimes(long nodeId);

    /**
     * return all node links with their tunning time.
     * @return List of node links with their running times
     */
    @Query("MATCH (n:NodeLink)-[:NODE_LINK_RUN_TIME]->(runTime:RuningTime) RETURN  id(n) AS nodeLinkId,  n.fromNodeName AS fromNodeName,"
            + "n.toNodeName AS toNodeName, n.length AS  length, n.sliding AS sliding, n.crossOver AS crossOver,n.runningLine AS  runningLine,"
            + "n.busEnergy AS busEnergy, n.acEnergy AS acEnergy, n.dcEnergy AS dcEnergy, n.dieselEnergy AS dieselEnergy, n.busGauge AS busGauge,"
            + "n.narrowGauge AS narrowGauge, n.standardGauge AS standardGauge, n.broadGauge AS broadGauge, n.trackSectionId AS trackSectionId,"
            + "runTime.stopToStop as stopToStop, runTime.passToPass as passToPass, runTime.sbId as sbId")
    List<INodeLinkRunTimeData> getAllNodeLinksAndRunningTimes();

}
