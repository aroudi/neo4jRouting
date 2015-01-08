// RailCorp 2014

package au.gov.nsw.railcorp.rttarefdata.rest;

import au.gov.nsw.railcorp.rttarefdata.domain.Station;
import au.gov.nsw.railcorp.rttarefdata.manager.EntitySequenceManager;
import au.gov.nsw.railcorp.rttarefdata.mapresult.IRefData;
import au.gov.nsw.railcorp.rttarefdata.mapresult.RefData;
import au.gov.nsw.railcorp.rttarefdata.request.StationModel;
import au.gov.nsw.railcorp.rttarefdata.repositories.StationRepository;
import au.gov.nsw.railcorp.rttarefdata.response.StationResponse;
import au.gov.nsw.railcorp.rttarefdata.service.StopService;
import au.gov.nsw.railcorp.rttarefdata.util.IConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Restfull web servic for providing station data.
 * Created by arash on 26/11/14.
 */

@Component
@Path("stations")
public class StationService {
    private final Logger logger = LoggerFactory.getLogger(StationService.class);
    @Context
    private UriInfo uriInfo;
    @Context
    private Request request;

    @Autowired
    private StationRepository stationRepository;
    @Autowired
    private EntitySequenceManager entitySequenceManager;

    @Autowired
    private StopService stopService;
    public StationRepository getStationRepository() {
        return stationRepository;
    }

    public void setStationRepository(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }
    /**
     * Return station list in Json format.
     * @return Station List
     */
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List getAllStations3() {
        return stopService.getAllStations();
    }

    /**
     * adding new station.
     * @param stationModel station
     * @return response
     */

    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public StationResponse addStation (StationModel stationModel) {
        final StationResponse response = new StationResponse();
        try {
            if (stationModel == null) {
                response.setStatus(IConstants.RESPONSE_FAILURE);
                response.setMessage("received object is null");
                return response;
            }
            Station station = stationRepository.findBySchemaPropertyValue("shortName", stationModel.getShortName());
            if (station != null) {
                response.setStatus(IConstants.RESPONSE_FAILURE);
                response.setMessage("station " + stationModel.getShortName() + " already exists");
                return response;
            }
            final long stationId = entitySequenceManager.getNextStationSequence();
            station = new Station();
            station.setStationId(stationId);
            station.setShortName(stationModel.getShortName());
            station.setLongName(stationModel.getLongName());
            station.setGtfsStopId(stationModel.getGtfsStopId());
            if (stationModel.getLatitude() == null) {
                station.setLatitude(0.0);
            } else {
                station.setLatitude(stationModel.getLatitude());
            }
            if (stationModel.getLongtitude() == null) {
                station.setLongtitude(0.0);
            } else {
                station.setLongtitude(stationModel.getLongtitude());
            }
            station.setInterchangePoint(stationModel.isInterchangePoint());
            stationRepository.save(station);
            response.setStationId(stationId);
            response.setStatus(IConstants.RESPONSE_SUCCESS);
        } catch (Exception e) {
            response.setStatus(IConstants.RESPONSE_FAILURE);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    /**
     * adding new station.
     * @param stationModel station
     * @return response
     */
    @Path("/edit")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public StationResponse editStation (StationModel stationModel) {
        final StationResponse response = new StationResponse();
        try {
            if (stationModel == null) {
                response.setStatus(IConstants.RESPONSE_FAILURE);
                response.setMessage("received object is null");
                return response;
            }
            final Station station = stationRepository.findBySchemaPropertyValue("stationId", stationModel.getStationId());
            if (station != null) {
                station.setShortName(stationModel.getShortName());
                station.setLongName(stationModel.getLongName());
                station.setGtfsStopId(stationModel.getGtfsStopId());
                station.setLatitude(stationModel.getLatitude());
                station.setLongtitude(stationModel.getLongtitude());
                station.setInterchangePoint(stationModel.isInterchangePoint());
                stationRepository.save(station);
                response.setStatus(IConstants.RESPONSE_SUCCESS);
            } else {
                response.setStatus(IConstants.RESPONSE_FAILURE);
                response.setMessage("Station not found");
            }
        } catch (Exception e) {
            response.setStatus(IConstants.RESPONSE_FAILURE);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    /**
     * delete station based on stationId.
     * @param stationId stationId
     * @return Response
     */
    @GET
    @Path("/delete/{stationId}")
    public StationResponse deleteStation (@PathParam("stationId") int stationId) {
        final StationResponse response = new StationResponse();
        try {
            final Station station = stationRepository.findBySchemaPropertyValue("stationId", stationId);
            if (station == null) {
                response.setStatus(IConstants.RESPONSE_FAILURE);
                response.setMessage("station not found");
                return response;
            }
            stationRepository.delete(station);
            response.setStatus(IConstants.RESPONSE_SUCCESS);
        } catch (Exception e) {
            response.setStatus(IConstants.RESPONSE_FAILURE);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    /**
     * Return station list in Json format.
     * @return Station List
     */
    @GET
    @Path("/stationsRef")
    @Produces(MediaType.APPLICATION_JSON)
    public List getStationsAsRefData() {
        List<IRefData> refDataList;
        final List<RefData> result = new ArrayList<RefData>();
        RefData refData;
        try {
            refDataList = stationRepository.getStationsAsRefData();
            for (IRefData data: refDataList) {
                refData = new RefData();
                refData.setRefDataId(data.getRefDataId());
                refData.setRefDataCode(data.getRefDataCode());
                refData.setRefDataName(data.getRefDataName());
                result.add(refData);
            }
            return result;
        } catch (Exception e) {
            logger.error("Exception in returning stations as ref data ", e);
            return null;
        }
    }

}
