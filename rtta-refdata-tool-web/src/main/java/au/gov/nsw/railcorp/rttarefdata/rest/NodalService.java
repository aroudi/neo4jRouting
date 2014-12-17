package au.gov.nsw.railcorp.rttarefdata.rest;

import au.gov.nsw.railcorp.rttarefdata.mapresult.INodeData;
import au.gov.nsw.railcorp.rttarefdata.mapresult.NodeData;
import au.gov.nsw.railcorp.rttarefdata.repositories.NodeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by arash on 17/12/2014.
 */

@Component
@Path("nodes")
public class NodalService {
    private final Logger logger = LoggerFactory.getLogger(StationService.class);
    @Context
    private UriInfo uriInfo;
    @Context
    private Request request;

    @Autowired
    private NodeRepository nodeRepository;
    /**
     * Return node list in Json format.
     * @return Node List
     */
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
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
