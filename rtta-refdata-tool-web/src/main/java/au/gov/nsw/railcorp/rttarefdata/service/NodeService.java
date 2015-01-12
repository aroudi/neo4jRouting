package au.gov.nsw.railcorp.rttarefdata.service;

import au.gov.nsw.railcorp.rtta.refint.generated.nodes.RefNode;
import au.gov.nsw.railcorp.rtta.refint.generated.nodes.RttaNodes;
import au.gov.nsw.railcorp.rttarefdata.manager.INodeManager;
import au.gov.nsw.railcorp.rttarefdata.mapresult.NodeData;
import au.gov.nsw.railcorp.rttarefdata.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.StreamingOutput;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by arash on 10/11/14.
 */
@Component
public class NodeService {
    private final Logger logger = LoggerFactory.getLogger(NodeService.class);
    @Autowired
    private INodeManager nodeManager;

    /**
     * Import Rtta Nodes.
     * @param rttaNodes rttaNodes
     */
    public void importRttaNodes(RttaNodes rttaNodes) {
        if (rttaNodes == null) {
            return;
        }
        for (RefNode refNode: rttaNodes.getNodesV01().getNodes().getNode()) {
            if (refNode == null) {
                continue;
            }
            if (refNode.isPlatformStop()) {
                nodeManager.createPlatform(refNode.getNodeName(), refNode.getLongName(), StringUtil.extractPlatformNoFromNodeName(refNode.getNodeName()),
                        refNode.getPlatformName(), refNode.getLatitude(), refNode.getLongitude());
            } else {
                nodeManager.createNode(refNode.getNodeName(), refNode.getLongName(), refNode.getPlatformName(), refNode.getLatitude(), refNode.getLongitude());
            }
        }
    }
    /**
     * return all nodes.
     * @return list of nodes
     */
    public List getAllNodes() {
        return nodeManager.getAllNodes();
    }

    /**
     * Export Node list into csv format and return as StreamingOutput.
     * @return Streamingoutput
     */
    public StreamingOutput exportNodesToCsv() {
        StreamingOutput streamingOutput = null;
        try {
            streamingOutput = new StreamingOutput() {
                @Override
                public void write(OutputStream output) throws IOException, WebApplicationException {
                    final List<NodeData> nodeList = nodeManager.getAllNodes();
                    if (nodeList == null) {
                        return;
                    }
                    String line = "NAME, LOCATION_NAME, PLATFORM_BRAND_CODE";
                    output.write(line.getBytes());
                    output.write(System.getProperty("line.separator").getBytes());
                    for (NodeData nodeData : nodeList) {
                        line = nodeData.getName() + ", " + nodeData.getLongName() + ", " + nodeData.getPlatformName();
                        output.write(line.getBytes());
                        output.write(System.getProperty("line.separator").getBytes());
                    }
                    output.flush();
                }
            };
            return streamingOutput;
        } catch (Exception e) {
            logger.error("Exception in writing platforms into csv: ", e);
            return null;
        }
    }
}
