package au.gov.nsw.railcorp.rttarefdata.rest;

import au.gov.nsw.railcorp.rttarefdata.domain.Node;
import au.gov.nsw.railcorp.rttarefdata.mapresult.INodeData;
import au.gov.nsw.railcorp.rttarefdata.mapresult.NodeData;
import au.gov.nsw.railcorp.rttarefdata.repositories.NodeRepository;
import au.gov.nsw.railcorp.rttarefdata.request.NodeModel;
import au.gov.nsw.railcorp.rttarefdata.response.Response;
import au.gov.nsw.railcorp.rttarefdata.util.IConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
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

    /**
     * Add new Node.
     * @param nodeModel nodeModel.
     * @return Response
     */
    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addNode (NodeModel nodeModel) {
        final Response response = new Response();
        try {
            if (nodeModel == null) {
                response.setStatus(IConstants.RESPONSE_FAILURE);
                response.setMessage("received object is null");
                return response;
            }
            Node node = nodeRepository.findBySchemaPropertyValue("name", nodeModel.getName());
            if (node != null) {
                response.setStatus(IConstants.RESPONSE_FAILURE);
                response.setMessage("node " + nodeModel.getName() + " already exists");
                return response;
            }
            node = new Node();
            node.setName(nodeModel.getName());
            node.setLongName(nodeModel.getLongName());
            node.setPlatfromName(nodeModel.getPlatformName());
            node.setDummy(nodeModel.isDummy());
            node.setJunction(nodeModel.isJunction());
            node.setWorkingTimingPoint(nodeModel.isWorkingTimingPoint());
            node.setPublicTimingPoint(nodeModel.isPublicTimingPoint());
            node.setEndOfLine(nodeModel.isEndOfLine());
            node.setdWellDuration(nodeModel.getWellDuration());
            node.setUpRecoveryDuration(nodeModel.getUpRecoveryDuration());
            node.setDownRecoveryDuration(nodeModel.getDownRecoveryDuration());
            node.setLength(nodeModel.getLength());
            if (nodeModel.getLatitude() == null) {
                node.setLatitude(0.0);
            } else {
                node.setLatitude(nodeModel.getLatitude());
            }
            if (nodeModel.getLongtitude() == null) {
                node.setLongitude(0.0);
            } else {
                node.setLongitude(nodeModel.getLongtitude());
            }
            final Node savedNode = nodeRepository.save(node);
            response.setId(savedNode.getNodeId());
            response.setStatus(IConstants.RESPONSE_SUCCESS);
        } catch (Exception e) {
            response.setStatus(IConstants.RESPONSE_FAILURE);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    /**
     * edit existing node.
     * @param nodeModel nodeModel
     * @return Response
     */
    @Path("/edit")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editNode (NodeModel nodeModel) {
        final Response response = new Response();
        try {
            if (nodeModel == null) {
                response.setStatus(IConstants.RESPONSE_FAILURE);
                response.setMessage("received object is null");
                return response;
            }
            final Node node = nodeRepository.findBySchemaPropertyValue("name", nodeModel.getName());
            if (node != null) {
                node.setName(nodeModel.getName());
                node.setLongName(nodeModel.getLongName());
                node.setPlatfromName(nodeModel.getPlatformName());
                node.setDummy(nodeModel.isDummy());
                node.setJunction(nodeModel.isJunction());
                node.setWorkingTimingPoint(nodeModel.isWorkingTimingPoint());
                node.setPublicTimingPoint(nodeModel.isPublicTimingPoint());
                node.setEndOfLine(nodeModel.isEndOfLine());
                node.setdWellDuration(nodeModel.getWellDuration());
                node.setUpRecoveryDuration(nodeModel.getUpRecoveryDuration());
                node.setDownRecoveryDuration(nodeModel.getDownRecoveryDuration());
                node.setLength(nodeModel.getLength());
                if (nodeModel.getLatitude() == null) {
                    node.setLatitude(0.0);
                } else {
                    node.setLatitude(nodeModel.getLatitude());
                }
                if (nodeModel.getLongtitude() == null) {
                    node.setLongitude(0.0);
                } else {
                    node.setLongitude(nodeModel.getLongtitude());
                }
                nodeRepository.save(node);
                response.setStatus(IConstants.RESPONSE_SUCCESS);
            } else {
                response.setStatus(IConstants.RESPONSE_FAILURE);
                response.setMessage("Node not found");
            }
        } catch (Exception e) {
            response.setStatus(IConstants.RESPONSE_FAILURE);
            response.setMessage(e.getMessage());
        }
        return response;
    }
    /**
     * Delete Node.
     * @param nodeId nodeId
     * @return response
     */
    @GET
    @Path("/delete/{nodeId}")
    public Response deleteNode (@PathParam("nodeId") Long nodeId) {
        final Response response = new Response();
        try {
            final Node node = nodeRepository.findOne(nodeId);
            if (node == null) {
                response.setStatus(IConstants.RESPONSE_FAILURE);
                response.setMessage("node not found");
                return response;
            }
            nodeRepository.delete(node);
            response.setStatus(IConstants.RESPONSE_SUCCESS);
        } catch (Exception e) {
            response.setStatus(IConstants.RESPONSE_FAILURE);
            response.setMessage(e.getMessage());
        }
        return response;
    }
}
