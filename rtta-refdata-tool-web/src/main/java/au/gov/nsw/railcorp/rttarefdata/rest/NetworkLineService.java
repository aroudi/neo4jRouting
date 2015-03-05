package au.gov.nsw.railcorp.rttarefdata.rest;

import au.gov.nsw.railcorp.rttarefdata.domain.*;
import au.gov.nsw.railcorp.rttarefdata.repositories.NetworkLineRepository;
import au.gov.nsw.railcorp.rttarefdata.repositories.NetworkRepository;
import au.gov.nsw.railcorp.rttarefdata.repositories.ServiceTypeRepository;
import au.gov.nsw.railcorp.rttarefdata.request.NetworkLineModel;
import au.gov.nsw.railcorp.rttarefdata.response.NetworkLineResponse;
import au.gov.nsw.railcorp.rttarefdata.response.Response;
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
 * Created by arash on 11/12/14.
 */
@Component
@Path("networkLines")

public class NetworkLineService {
    private final Logger logger = LoggerFactory.getLogger(StationService.class);
    @Context
    private UriInfo uriInfo;
    @Context
    private Request request;

    @Autowired
    private NetworkLineRepository networklineRepository;

    @Autowired
    private NetworkRepository networkRepository;

    @Autowired
    private ServiceTypeRepository serviceTypeRepository;
    @Autowired
    private TopologyService topologyService;
    @Autowired
    private SessionState sessionState;

    /**
     * get All Network Lines.
     * @return List of Network Line
     */
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List getAllNetworkLines() {
        return topologyService.getAllNetworkLines();
    }

    /**
     * add new line.
     * @param networkLineModel networkLineModel
     * @return response.
     */
    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public NetworkLineResponse addLine (NetworkLineModel networkLineModel) {
        final NetworkLineResponse response = new NetworkLineResponse();
        try {
            if (networkLineModel == null) {
                response.setStatus(IConstants.RESPONSE_FAILURE);
                response.setMessage("received object is null");
                return response;
            }
            if (networkLineModel.getNetwork() == null) {
                response.setStatus(IConstants.RESPONSE_FAILURE);
                response.setMessage("network object is null");
                return response;
            }
            if (networkLineModel.getServiceType() == null) {
                response.setStatus(IConstants.RESPONSE_FAILURE);
                response.setMessage("serviceType object is null");
                return response;
            }
            //check if platform allready exists.
            NetworkLine networkLine = networklineRepository.getLinePerName(sessionState.getWorkingVersion().getName(), networkLineModel.getName());
            if (networkLine != null) {
                response.setStatus(IConstants.RESPONSE_FAILURE);
                response.setMessage("network name " + networkLineModel.getName() + " already exists");
                return response;
            }
            final Network network = networkRepository.getNetworkPerName(sessionState.getWorkingVersion().getName(), networkLineModel.getNetwork().getRefDataCode());
            if (network == null) {
                response.setStatus(IConstants.RESPONSE_FAILURE);
                response.setMessage("not able to find network :" + networkLineModel.getNetwork().getRefDataName());
                return response;
            }
            final ServiceType serviceType = serviceTypeRepository.findBySchemaPropertyValue("name", networkLineModel.getServiceType().getRefDataCode());
            if (serviceType == null) {
                response.setStatus(IConstants.RESPONSE_FAILURE);
                response.setMessage("not able to find service type :" + networkLineModel.getServiceType().getRefDataName());
                return response;
            }
            networkLine = new NetworkLine();
            networkLine.setNetwork(network);
            networkLine.setServiceType(serviceType);
            networkLine.setName(networkLineModel.getName());
            networkLine.setLongName(networkLineModel.getLongName());
            networkLine.setBackgroundColourHex(networkLineModel.getBackgroundColourHex());
            networkLine.setTextColourHex(networkLineModel.getTextColourHex());
            networkLine.setVersion(sessionState.getWorkingVersion());
            networklineRepository.save(networkLine);
            final NetworkLine savedOne = networklineRepository.getLinePerName(sessionState.getWorkingVersion().getName(), networkLineModel.getName());
            response.setLineId(savedOne.getNetworkLineId());
            response.setStatus(IConstants.RESPONSE_SUCCESS);
        } catch (Exception e) {
            response.setStatus(IConstants.RESPONSE_FAILURE);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    /**
     * edit NetworkLine.
     * @param networkLineModel networkLineModel
     * @return Response
     */
    @Path("/edit")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editLine (NetworkLineModel networkLineModel) {
        final Response response = new Response();
        try {
            if (networkLineModel == null) {
                response.setStatus(IConstants.RESPONSE_FAILURE);
                response.setMessage("received object is null");
                return response;
            }
            if (networkLineModel.getNetwork() == null) {
                response.setStatus(IConstants.RESPONSE_FAILURE);
                response.setMessage("network object is null");
                return response;
            }
            if (networkLineModel.getServiceType() == null) {
                response.setStatus(IConstants.RESPONSE_FAILURE);
                response.setMessage("se.rviceType object is null");
                return response;
            }
            final Network network = networkRepository.getNetworkPerName(sessionState.getWorkingVersion().getName(), networkLineModel.getNetwork().getRefDataCode());
            if (network == null) {
                response.setStatus(IConstants.RESPONSE_FAILURE);
                response.setMessage("not able to find network :" + networkLineModel.getNetwork().getRefDataName());
                return response;
            }
            final ServiceType serviceType = serviceTypeRepository.findBySchemaPropertyValue("name", networkLineModel.getServiceType().getRefDataCode());
            if (serviceType == null) {
                response.setStatus(IConstants.RESPONSE_FAILURE);
                response.setMessage("not able to find service type :" + networkLineModel.getServiceType().getRefDataName());
                return response;
            }

            final NetworkLine networkLine = networklineRepository.findOne(networkLineModel.getLineId());
            if (networkLine == null) {
                response.setStatus(IConstants.RESPONSE_FAILURE);
                response.setMessage("line name " + networkLineModel.getName() + " not found");
                return response;
            }
            networkLine.setNetwork(network);
            networkLine.setServiceType(serviceType);
            networkLine.setName(networkLineModel.getName());
            networkLine.setLongName(networkLineModel.getLongName());
            networkLine.setBackgroundColourHex(networkLineModel.getBackgroundColourHex());
            networkLine.setVersion(sessionState.getWorkingVersion());
            networkLine.setTextColourHex(networkLineModel.getTextColourHex());
            networklineRepository.save(networkLine);
            response.setStatus(IConstants.RESPONSE_SUCCESS);
        } catch (Exception e) {
            response.setStatus(IConstants.RESPONSE_FAILURE);
            response.setMessage(e.getMessage());
        }
        return response;
    }
    /**
     * Delete NetworkLine.
     * @param lineId lineId
     * @return response
     */
    @GET
    @Path("/delete/{lineId}")
    public Response deleteNetworkLine (@PathParam("lineId") Long lineId) {
        final Response response = new Response();
        try {
            final NetworkLine networkLine = networklineRepository.findOne(lineId);
            if (networkLine == null) {
                response.setStatus(IConstants.RESPONSE_FAILURE);
                response.setMessage("line not found");
                return response;
            }
            networklineRepository.delete(networkLine);
            response.setStatus(IConstants.RESPONSE_SUCCESS);
        } catch (Exception e) {
            response.setStatus(IConstants.RESPONSE_FAILURE);
            response.setMessage(e.getMessage());
        }
        return response;
    }
}
