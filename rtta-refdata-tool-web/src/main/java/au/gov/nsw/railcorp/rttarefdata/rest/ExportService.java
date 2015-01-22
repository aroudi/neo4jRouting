package au.gov.nsw.railcorp.rttarefdata.rest;

import au.gov.nsw.railcorp.rtta.refint.generated.geography.CgGeography;
import au.gov.nsw.railcorp.rttarefdata.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;

/**
 * Created by arash on 24/12/2014.
 */
@Component
@Path("export")
public class ExportService {
    private final Logger logger = LoggerFactory.getLogger(StationService.class);
    @Context
    private UriInfo uriInfo;
    @Context
    private Request request;

    @Autowired
    private TopologyService topologyService;

    @Autowired
    private StopService stopService;

    @Autowired
    private NodeService nodeService;

    @Autowired
    private NodalGeographyService nodalGeographyService;

    @Autowired
    private LocationService locationService;
    /**
     * export networks into csv format.
     * @return csv file
     */
    @Path("/networkToCsv")
    @GET
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response exportNetworkToCsv() {
        final StreamingOutput streamingOutput = topologyService.exportNetworkToCsv();
        return Response.ok(streamingOutput).header("content-disposition", "attachment; filename = Network.csv").build();
    }

    /**
     * export networkLines into csv format.
     * @return csv file
     */
    @Path("/networkLineToCsv")
    @GET
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response exportNetworkLineToCsv() {
        final StreamingOutput streamingOutput = topologyService.exportNetworkLinesToCsv();
        return Response.ok(streamingOutput).header("content-disposition", "attachment; filename = NetworkLine.csv").build();
    }

    /**
     * export linePaths into csv format.
     * @return csv file
     */
    @Path("/linePathsToCsv")
    @GET
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response exportLinePathsToCsv() {
        final StreamingOutput streamingOutput = topologyService.exportLinePathsToCsv();
        return Response.ok(streamingOutput).header("content-disposition", "attachment; filename = NetworkLinePaths.csv").build();
    }

    /**
     * export stations into csv format.
     * @return csv file
     */
    @Path("/stationsToCsv")
    @GET
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response exportStationsToCsv() {
        final StreamingOutput streamingOutput = stopService.exportStationToCsv();
        return Response.ok(streamingOutput).header("content-disposition", "attachment; filename = STATION_DATA.csv").build();
    }

    /**
     * export platforms into csv format.
     * @return csv file
     */
    @Path("/platformsToCsv")
    @GET
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response exportPlatformsToCsv() {
        final StreamingOutput streamingOutput = stopService.exportStationToCsv();
        return Response.ok(streamingOutput).header("content-disposition", "attachment; filename = PLATFORM_DATA.csv").build();
    }

    /**
     * export nodes into csv format.
     * @return csv file
     */
    @Path("/nodesToCsv")
    @GET
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response exportNodesToCsv() {
        final StreamingOutput streamingOutput = nodeService.exportNodesToCsv();
        return Response.ok(streamingOutput).header("content-disposition", "attachment; filename = NODE_DATA.csv").build();
    }

    /**
     * export nodes into csv format.
     * @return csv file
     */
    @Path("/tripletsToCsv")
    @GET
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response exportTripletsToCsv() {
        final StreamingOutput streamingOutput = stopService.exportStopLinksToCsv();
            return Response.ok(streamingOutput).header("content-disposition", "attachment; filename = StopLinks.csv").build();
    }

    /**
     * export Nodal geography in xml format.
     * @return xml format
     */
    @Path("/nodalGeography")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public CgGeography exportNodalGeography() {
        return nodalGeographyService.exportNodalGeography();
    }

    /**
     * export nodalGeography into xml format and return as file.
     * @return xml file
     */
    @Path("/nodalGeography/xml")
    @GET
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response exportNodalGeographyToXml() {
        final StreamingOutput streamingOutput = nodalGeographyService.exportNodalGeographyToXml();
        return Response.ok(streamingOutput).header("content-disposition", "attachment; filename = NodalGeography.xml").build();
    }

    /**
     * export locations into csv format.
     * @return csv file
     */
    @Path("/locationsToCsv")
    @GET
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response exportLocationsToCsv() {
        final StreamingOutput streamingOutput = locationService.exportLocationsToCsv();
        return Response.ok(streamingOutput).header("content-disposition", "attachment; filename = Location.csv").build();
    }
}
