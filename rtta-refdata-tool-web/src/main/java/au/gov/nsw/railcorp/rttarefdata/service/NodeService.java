package au.gov.nsw.railcorp.rttarefdata.service;

import au.gov.nsw.railcorp.rtta.refint.generated.nodes.RefNode;
import au.gov.nsw.railcorp.rtta.refint.generated.nodes.RttaNodes;
import au.gov.nsw.railcorp.rttarefdata.manager.NodeManager;
import au.gov.nsw.railcorp.rttarefdata.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by arash on 10/11/14.
 */
@Component
public class NodeService {
    @Autowired
    private NodeManager nodeManager;

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

}
