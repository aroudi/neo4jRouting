package au.gov.nsw.railcorp.rttarefdata.manager;

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
                result.add(nodeData);
            }
            return result;
        } catch (Exception e) {
            logger.error("Exception in returning node list ", e);
            return null;
        }
    }

}
