package au.gov.nsw.railcorp.rttarefdata.rest;

import au.gov.nsw.railcorp.rttarefdata.request.LocationModel;
import au.gov.nsw.railcorp.rttarefdata.response.Response;
import au.gov.nsw.railcorp.rttarefdata.service.LocationService;
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
 * Created by arash on 21/01/2015.
 */
@Component
@Path("locations")
public class LocationWebService {

    private final Logger logger = LoggerFactory.getLogger(StationService.class);
    @Context
    private UriInfo uriInfo;
    @Context
    private Request request;

    @Autowired
    private LocationService locationService;
    /**
     * Return All locations.
     * @return list of locations
     */
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List getAllLocations() {
        return locationService.getAllLocations();
    }
    /**
     * Edit location.
     * @param locationModel locationModel
     * @return Response
     */
    @Path("/edit")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editLocation (LocationModel locationModel) {
        return locationService.editLocation(locationModel);
    }

    /**
     * add location.
     * @param locationModel locationModel
     * @return Response
     */
    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addLocation (LocationModel locationModel) {
        return locationService.addLocation(locationModel);
    }
    /**
     * delete Location.
     * @param locationId locationId
     * @return Network Response.
     */
    @GET
    @Path("/delete/{locationId}")
    public Response deleteNetwork (@PathParam("locationId") Long locationId) {
        return locationService.deleteLocation(locationId);
    }
}
