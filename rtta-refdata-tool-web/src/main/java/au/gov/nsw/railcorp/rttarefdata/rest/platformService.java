package au.gov.nsw.railcorp.rttarefdata.rest;

import au.gov.nsw.railcorp.rttarefdata.domain.Platform;
import au.gov.nsw.railcorp.rttarefdata.domain.Station;
import au.gov.nsw.railcorp.rttarefdata.repositories.PlatformRepository;
import au.gov.nsw.railcorp.rttarefdata.repositories.StationRepository;
import au.gov.nsw.railcorp.rttarefdata.request.PlatformModel;
import au.gov.nsw.railcorp.rttarefdata.response.PlatformResponse;
import au.gov.nsw.railcorp.rttarefdata.service.StopService;
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
 * Created by arash on 4/12/14.
 * Restful webservice for managing CRUD on platform
 */
@Component
@Path("platforms")

public class platformService {
    private final Logger logger = LoggerFactory.getLogger(StationService.class);
    @Context
    private UriInfo uriInfo;
    @Context
    private Request request;

    @Autowired
    private PlatformRepository platformRepository;
    @Autowired
    private StationRepository stationRepository;
    @Autowired
    private StopService stopService;

    @Autowired
    private SessionState sessionState;
    /**
     * Return station list in Json format.
     * @return Station List
     */
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List getAllPlatforms() {
        return stopService.getAllPlatforms();
    }

    /**
     * Create new platform.
     * @param platformModel platformModle
     * @return general response
     */
    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public PlatformResponse addPlatform (PlatformModel platformModel) {
        final PlatformResponse response = new PlatformResponse();
        try {
            if (platformModel == null) {
                response.setStatus(IConstants.RESPONSE_FAILURE);
                response.setMessage("received object is null");
                return response;
            }
            if (platformModel.getStation() == null) {
                response.setStatus(IConstants.RESPONSE_FAILURE);
                response.setMessage("station object is null");
                return response;
            }
            //check if platform allready exists.
            Platform platform = platformRepository.getPlatformPerName(sessionState.getWorkingVersion().getName(), platformModel.getPlatformName());
            if (platform != null) {
                response.setStatus(IConstants.RESPONSE_FAILURE);
                response.setMessage("platform code " + platformModel.getPlatformName() + " already exists");
                return response;
            }
            platform = new Platform();
            final Station station = stationRepository.getStationPerStationId(sessionState.getWorkingVersion().getName(), platformModel.getStation().getRefDataId());
            if (station == null) {
                response.setStatus(IConstants.RESPONSE_FAILURE);
                response.setMessage("not able to find station :" + platformModel.getStation().getRefDataName());
                return response;
            }
            platform.setStation(station);
            platform.setName(platformModel.getPlatformName());
            platform.setLongName(platformModel.getPlatformLongName());
            platform.setPlatformNo(platformModel.getPlatformNo());
            platform.setGtfsStopId(platformModel.getPlatformStopId());
            platform.setVersion(sessionState.getWorkingVersion());
            if (platformModel.getLatitude() == null) {
                platform.setLatitude(0.0);
            } else {
                platform.setLatitude(platformModel.getLatitude());
            }
            if (platformModel.getLongtitude() == null) {
                platform.setLongitude(0.0);
            } else {
                platform.setLongitude(platformModel.getLongtitude());
            }
            platformRepository.save(platform);
            response.setStatus(IConstants.RESPONSE_SUCCESS);
            response.setStationGtfsStopId(station.getGtfsStopId());
        } catch (Exception e) {
            response.setStatus(IConstants.RESPONSE_FAILURE);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    /**
     * edit platform.
     * @param platformModel platformModel
     * @return Response
     */
    @Path("/edit")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public PlatformResponse editPlatform (PlatformModel platformModel) {
        final PlatformResponse response = new PlatformResponse();
        try {
            if (platformModel == null) {
                response.setStatus(IConstants.RESPONSE_FAILURE);
                response.setMessage("received object is null");
                return response;
            }
            if (platformModel.getStation() == null) {
                response.setStatus(IConstants.RESPONSE_FAILURE);
                response.setMessage("station object is null");
                return response;
            }
            logger.info("edit platform --> version =" + sessionState.getWorkingVersion().getName());
            final Platform platform = platformRepository.getPlatformPerName(sessionState.getWorkingVersion().getName(), platformModel.getPlatformName());
            if (platform == null) {
                response.setStatus(IConstants.RESPONSE_FAILURE);
                response.setMessage("not able to find platform :" + platformModel.getPlatformName());
                return response;
            }
            final Station station = stationRepository.getStationPerStationId(sessionState.getWorkingVersion().getName(), platformModel.getStation().getRefDataId());
            if (station == null) {
                response.setStatus(IConstants.RESPONSE_FAILURE);
                response.setMessage("not able to find station :" + platformModel.getStation().getRefDataName());
                return response;
            }
            platform.setStation(station);
            //platform.setName(platformModel.getPlatformName());
            platform.setLongName(platformModel.getPlatformLongName());
            platform.setPlatformNo(platformModel.getPlatformNo());
            platform.setGtfsStopId(platformModel.getPlatformStopId());
            platform.setVersion(sessionState.getWorkingVersion());
            if (platformModel.getLatitude() == null) {
                platform.setLatitude(0.0);
            } else {
                platform.setLatitude(platformModel.getLatitude());
            }
            if (platformModel.getLongtitude() == null) {
                platform.setLongitude(0.0);
            } else {
                platform.setLongitude(platformModel.getLongtitude());
            }
            platformRepository.save(platform);
            response.setStatus(IConstants.RESPONSE_SUCCESS);
            response.setStationGtfsStopId(station.getGtfsStopId());
        } catch (Exception e) {
            response.setStatus(IConstants.RESPONSE_FAILURE);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    /**
     * Delete platform.
     * @param platformName platformName
     * @return response
     */
    @GET
    @Path("/delete/{platformName}")
    public PlatformResponse deletePlatform (@PathParam("platformName") String platformName) {
        final PlatformResponse response = new PlatformResponse();
        try {
            final Platform platform = platformRepository.getPlatformPerName(sessionState.getWorkingVersion().getName(), platformName);
            if (platform == null) {
                response.setStatus(IConstants.RESPONSE_FAILURE);
                response.setMessage("platform " + platformName + " not found");
                return response;
            }
            platformRepository.delete(platform);
            response.setStatus(IConstants.RESPONSE_SUCCESS);
        } catch (Exception e) {
            response.setStatus(IConstants.RESPONSE_FAILURE);
            response.setMessage(e.getMessage());
        }
        return response;
    }

}
