package au.gov.nsw.railcorp.rttarefdata.manager;

import au.gov.nsw.railcorp.rtta.refint.generated.nodes.NodesV01;
import au.gov.nsw.railcorp.rtta.refint.generated.nodes.RefNode;
import au.gov.nsw.railcorp.rtta.refint.generated.nodes.RefNodes;
import au.gov.nsw.railcorp.rtta.refint.generated.nodes.RttaNodes;
import au.gov.nsw.railcorp.rtta.refint.generated.reference.ReferenceHeader;
import au.gov.nsw.railcorp.rttarefdata.domain.Node;
import au.gov.nsw.railcorp.rttarefdata.domain.Platform;
import au.gov.nsw.railcorp.rttarefdata.mapresult.INodeData;
import au.gov.nsw.railcorp.rttarefdata.mapresult.NodeData;
import au.gov.nsw.railcorp.rttarefdata.repositories.NodeRepository;
import au.gov.nsw.railcorp.rttarefdata.repositories.PlatformRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by arash on 10/11/14.
 */
@Component
@Transactional
public class NodeManager implements INodeManager {
    private final Logger logger = LoggerFactory.getLogger(TopologyManager.class);

    @Autowired
    private PlatformRepository platformRepository;

    @Autowired
    private NodeRepository nodeRepository;

    /**
     * create platform.
     * @param nodeName nodeName
     * @param longName longName
     * @param platformNo platformNo
     * @param platformName platformName
     * @param latt latt
     * @param longt longt
     * @return Platform platform
     */
    public Platform createPlatform(String nodeName, String longName, int platformNo, String platformName, double latt, double longt) {

        Platform platform = platformRepository.findBySchemaPropertyValue("name", nodeName);
        if (platform == null) {
            platform = new Platform();
        }
        platform.setPlatformNo(platformNo);
        platform.setName(nodeName);
        platform.setPlatfromName(platformName);
        platform.setLongName(longName);
        platform.setLatitude(latt);
        platform.setLongitude(longt);
        platformRepository.save(platform);
        return platform;
    }

    /**
     * create Node.
     * @param nodeName nodeName
     * @param longName longName
     * @param platformName platformName
     * @param latt latt
     * @param longt longt
     * @return Node platform
     */
    public Node createNode(String nodeName, String longName, String platformName, double latt, double longt) {

        Node node = nodeRepository.findBySchemaPropertyValue("name", nodeName);
        if (node == null) {
            node = new Platform();
        }
        node.setName(nodeName);
        node.setPlatfromName(platformName);
        node.setLongName(longName);
        node.setLatitude(latt);
        node.setLongitude(longt);
        node.setRailNetNode(false);
        nodeRepository.save(node);
        return node;
    }

    /**
     * return all nodes.
     * @return list of nodes
     */
    public List getAllNodes() {
        final List<NodeData> result = new ArrayList<NodeData>();
        final List<INodeData> nodes;
        NodeData nodeData;
        try {
            nodes = nodeRepository.getAllNodes();
            for (INodeData node: nodes) {
                nodeData = new NodeData();
                nodeData.setNodeId(node.getNodeId());
                nodeData.setName(node.getName());
                nodeData.setLongName(node.getLongName());
                nodeData.setPlatformName(node.getPlatformName());
                nodeData.setDummy(node.isDummy());
                nodeData.setJunction(node.isJunction());
                nodeData.setWorkingTimingPoint(node.isWorkingTimingPoint());
                nodeData.setPublicTimingPoint(node.isPublicTimingPoint());
                nodeData.setEndOfLine(node.isEndOfLine());
                nodeData.setWellDuration(node.getWellDuration());
                nodeData.setUpRecoveryDuration(node.getUpRecoveryDuration());
                nodeData.setDownRecoveryDuration(node.getDownRecoveryDuration());
                nodeData.setLength(node.getLength());
                try {
                    nodeData.setLongtitude(node.getLongtitude());
                } catch (NullPointerException e1) {
                    nodeData.setLongtitude(0);
                }
                try {
                    nodeData.setLatitude(node.getLatitude());
                } catch (NullPointerException e2) {
                    nodeData.setLatitude(0);
                }
                try {
                    nodeData.setGtfsStopId(node.getGtfsStopId());
                } catch (NullPointerException e3) {
                    nodeData.setGtfsStopId(0);
                }
                result.add(nodeData);
            }
            return result;
        } catch (Exception e) {
            logger.error("Exception in returning node list ", e);
            return null;
        }
    }

    /**
     * find node by name.
     * @param name name
     * @return Node
     */
    public Node getNodeByName (String name) {
        try {
            return nodeRepository.findBySchemaPropertyValue("name", name);
        } catch (Exception e) {
            logger.error ("Exception in getting node by name:", e);
            return null;
        }
    }

    /**
     * Build RttaNode List.
     * @return RttaNodes
     */
    public RttaNodes buildRttaNodes() {
        try {
            final ReferenceHeader referenceHeader = new ReferenceHeader();
            referenceHeader.setContent("RttaNodes");
            referenceHeader.setContentVersion("0.1");
            referenceHeader.setContentManager("NodeMessage");
            referenceHeader.setDescription("TfNSW RTTA Nodes Data Bundle");
            referenceHeader.setComment("Generated by Rtta RefDataTool");
            referenceHeader.setSequence("0");
            final Date currentDate = new Date();
            referenceHeader.setDate(currentDate.toString());
            final RttaNodes rttaNodes = new RttaNodes();
            rttaNodes.setReferenceHeader(referenceHeader);
            final NodesV01 nodesV01 = new NodesV01();
            RefNode refNode;
            final RefNodes refNodes = new RefNodes();
            //RefNodes refNodes
            final List<NodeData> nodeDataList = getAllNodes();
            for (NodeData nodeData : nodeDataList) {
                refNode = new RefNode();
                refNode.setNodeName(nodeData.getName());
                refNode.setLongName(nodeData.getLongName());
                refNode.setPlatformName(nodeData.getPlatformName());
                refNode.setSource("RefDatTool");
                refNode.setPlatformStop(nodeData.getGtfsStopId() > 0 ? true : false);
                refNode.setLatitude(nodeData.getLatitude());
                refNode.setLongitude(nodeData.getLongtitude());
                refNodes.getNode().add(refNode);
            }
            nodesV01.setNodes(refNodes);
            rttaNodes.setNodesV01(nodesV01);
            return rttaNodes;
        } catch (Exception e) {
            logger.error("Exeption in building Nodes List:", e);
            return null;
        }
    }
}
