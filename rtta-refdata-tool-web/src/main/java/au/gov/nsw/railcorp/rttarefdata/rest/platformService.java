package au.gov.nsw.railcorp.rttarefdata.rest;

import au.gov.nsw.railcorp.rttarefdata.mapresult.IStationPlatformData;
import au.gov.nsw.railcorp.rttarefdata.mapresult.StationPlatformData;
import au.gov.nsw.railcorp.rttarefdata.repositories.PlatformRepository;
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
    /**
     * Return station list in Json format.
     * @return Station List
     */
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List getAllPlatforms() {
        List<IStationPlatformData> platformList;
        final List<StationPlatformData> result = new ArrayList<StationPlatformData>();
        StationPlatformData stationPlatformData;
        try {
            platformList = platformRepository.getAllPlatforms();
            for (IStationPlatformData platformData: platformList) {
                stationPlatformData = new StationPlatformData();
                stationPlatformData.setStationId(platformData.getStationId());
                stationPlatformData.setPlatformLongName(platformData.getPlatformLongName());
                stationPlatformData.setPlatformName(platformData.getPlatformName());
                stationPlatformData.setPlatformNo(platformData.getPlatformNo());
                stationPlatformData.setPlatformStopId(platformData.getPlatformStopId());
                stationPlatformData.setStationShortName(platformData.getStationShortName());
                stationPlatformData.setStationStopId(platformData.getStationStopId());
                stationPlatformData.setLatitude(platformData.getLatitude());
                stationPlatformData.setLongtitude(platformData.getLongtitude());
                result.add(stationPlatformData);
            }
            return result;
        } catch (Exception e) {
            logger.error("Exception in returning platform list ", e);
            return null;
        }
    }

}
