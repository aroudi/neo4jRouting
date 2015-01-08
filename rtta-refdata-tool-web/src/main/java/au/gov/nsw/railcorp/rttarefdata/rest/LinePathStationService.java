package au.gov.nsw.railcorp.rttarefdata.rest;

import au.gov.nsw.railcorp.rttarefdata.mapresult.*;
import au.gov.nsw.railcorp.rttarefdata.repositories.LinePathRepository;
import au.gov.nsw.railcorp.rttarefdata.repositories.PathStationRepository;
import au.gov.nsw.railcorp.rttarefdata.service.TopologyService;
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
import java.util.List;

/**
 * Created by arash on 22/12/2014.
 */
@Component
@Path("linePathStations")
public class LinePathStationService {

    private final Logger logger = LoggerFactory.getLogger(StationService.class);
    @Context
    private UriInfo uriInfo;
    @Context
    private Request request;

    @Autowired
    private PathStationRepository pathStationRepository;

    @Autowired
    private LinePathRepository linePathRepository;

    @Autowired
    private TopologyService topologyService;
    /**
     * Return path station list in Json format.
     * @return path Station List
     */
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List getAllPathStation() {
        return topologyService.getAllPathStation();
    }
}

