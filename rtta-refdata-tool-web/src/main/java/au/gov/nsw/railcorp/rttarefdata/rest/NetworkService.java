package au.gov.nsw.railcorp.rttarefdata.rest;

import au.gov.nsw.railcorp.rttarefdata.domain.Network;
import au.gov.nsw.railcorp.rttarefdata.repositories.NetworkRepository;
import au.gov.nsw.railcorp.rttarefdata.request.NetworkModel;
import au.gov.nsw.railcorp.rttarefdata.request.NetworkVisModel;
import au.gov.nsw.railcorp.rttarefdata.response.NetworkResponse;
import au.gov.nsw.railcorp.rttarefdata.service.TopologyService;
import au.gov.nsw.railcorp.rttarefdata.session.SessionState;
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
import java.util.List;

/**
 * Created by arash on 9/12/14.
 * A Rest service for Network.
 */
@Component
@Path("networks")
public class NetworkService {
    private final Logger logger = LoggerFactory.getLogger(StationService.class);
    @Context
    private UriInfo uriInfo;
    @Context
    private Request request;

    @Autowired
    private NetworkRepository networkRepository;
    @Autowired
    private TopologyService topologyService;

    @Autowired
    private SessionState sessionState;
    /**
     * Return All Networks.
     * @return List of Network
     */
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List getAllNetworks() {
        try {
                return topologyService.getAllNetworks();
        } catch (Exception e) {
            logger.error("Exception in returning network list ", e);
            return null;
        }
    }

    /**
     * Edit network.
     * @param networkModel networkModel
     * @return NetworkResponse
     */
    @Path("/edit")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public NetworkResponse editNetwork (NetworkModel networkModel) {
        final NetworkResponse response = new NetworkResponse();
        try {
            if (networkModel == null) {
                response.setStatus(IConstants.RESPONSE_FAILURE);
                response.setMessage("received object is null");
                return response;
            }
            final Network network = networkRepository.findOne(networkModel.getNetworkId());
            if (network != null) {
                network.setName(networkModel.getName());
                network.setDescription(networkModel.getDescription());
                network.setUrl(networkModel.getUrl());
                network.setLang(networkModel.getLang());
                network.setFareUrl(networkModel.getFareUrl());
                network.setTimeZone(networkModel.getTimeZone());
                network.setVersion(sessionState.getWorkingVersion());
                networkRepository.save(network);
                response.setStatus(IConstants.RESPONSE_SUCCESS);
            } else {
                response.setStatus(IConstants.RESPONSE_FAILURE);
                response.setMessage("network " + networkModel.getNetworkId() + " not found");
            }
        } catch (Exception e) {
            response.setStatus(IConstants.RESPONSE_FAILURE);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    /**
     * Crate new network.
     * @param networkModel networkModel
     * @return Network Response.
     */
    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public NetworkResponse addNetwork (NetworkModel networkModel) {
        final NetworkResponse response = new NetworkResponse();
        try {
            if (networkModel == null) {
                response.setStatus(IConstants.RESPONSE_FAILURE);
                response.setMessage("received object is null");
                return response;
            }
            final Network network = new Network();
            network.setName(networkModel.getName());
            network.setDescription(networkModel.getDescription());
            network.setUrl(networkModel.getUrl());
            network.setLang(networkModel.getLang());
            network.setFareUrl(networkModel.getFareUrl());
            network.setTimeZone(networkModel.getTimeZone());
            network.setVersion(sessionState.getWorkingVersion());
            networkRepository.save(network);
            final Network savedNetwork = networkRepository.getNetworkPerName(sessionState.getWorkingVersion().getName(), network.getName());
            response.setStatus(IConstants.RESPONSE_SUCCESS);
            response.setNetworkId(savedNetwork.getNetworkId());
        } catch (Exception e) {
            response.setStatus(IConstants.RESPONSE_FAILURE);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    /**
     * delete Network.
     * @param networkId networkId
     * @return Network Response.
     */
    @GET
    @Path("/delete/{networkId}")
    public NetworkResponse deleteNetwork (@PathParam("networkId") Long networkId) {
        final NetworkResponse response = new NetworkResponse();
        try {
            final Network network = networkRepository.findOne(networkId);
            if (network == null) {
                response.setStatus(IConstants.RESPONSE_FAILURE);
                response.setMessage("network not found");
                return response;
            }
            networkRepository.delete(network);
            response.setStatus(IConstants.RESPONSE_SUCCESS);
        } catch (Exception e) {
            response.setStatus(IConstants.RESPONSE_FAILURE);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    /**
     * return network in visjs format.
     * @return NetworkVisModel
     */
    @GET
    @Path("/visualize")
    @Produces(MediaType.APPLICATION_JSON)
    public List<NetworkVisModel> visualizeNetwork () {
        return topologyService.getNetworkVisModel();
    }

}
