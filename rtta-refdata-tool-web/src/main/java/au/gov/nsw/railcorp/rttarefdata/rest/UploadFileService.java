package au.gov.nsw.railcorp.rttarefdata.rest;
import au.gov.nsw.railcorp.rtta.refint.boot.GtfsTopologyBootManager;
import au.gov.nsw.railcorp.rtta.refint.boot.NodalGeographyBootManager;
import au.gov.nsw.railcorp.rtta.refint.boot.NodeBootManager;
import au.gov.nsw.railcorp.rtta.refint.boot.StopsBootManager;
import au.gov.nsw.railcorp.rtta.refint.generated.geography.CgGeography;
import au.gov.nsw.railcorp.rtta.refint.generated.gtfs.RttaGtfsTopology;
import au.gov.nsw.railcorp.rtta.refint.generated.nodes.RttaNodes;
import au.gov.nsw.railcorp.rtta.refint.generated.stops.RttaStops;
import au.gov.nsw.railcorp.rttarefdata.response.Response;
import au.gov.nsw.railcorp.rttarefdata.service.NodalGeographyService;
import au.gov.nsw.railcorp.rttarefdata.service.NodeService;
import au.gov.nsw.railcorp.rttarefdata.service.StopService;
import au.gov.nsw.railcorp.rttarefdata.service.TopologyService;
import au.gov.nsw.railcorp.rttarefdata.util.IConstants;
import com.sun.jersey.multipart.FormDataParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import java.io.InputStream;

/**
 * Created by arash on 12/12/2014.
 */
@Component
@Path("/upload")
public class UploadFileService {

    private final Logger logger = LoggerFactory.getLogger(StationService.class);
    @Autowired
    private StopsBootManager stopsBootManager;
    @Autowired
    private NodeBootManager nodeBootManager;

    @Autowired
    private GtfsTopologyBootManager gtfsTopologyBootManager;

    @Autowired
    private NodalGeographyBootManager nodalGeographyBootManager;

    @Autowired
    private StopService stopService;

    @Autowired
    private NodalGeographyService nodalGeographyService;

    @Autowired
    private NodeService nodeService;

    @Autowired
    private TopologyService topologyService;

    /**
     * upload stops.
     * @param uploadedInputStream uploadedInputStream
     * @return Response
     */
    @POST
    @Path("/stops")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadStops(@FormDataParam("file")InputStream uploadedInputStream) {
        final Response response = new Response();
        try {
            final RttaStops rttaStops = stopsBootManager.loadStopsFromInputStream(uploadedInputStream);
            if (rttaStops == null) {
                response.setStatus(IConstants.RESPONSE_FAILURE);
                response.setMessage("Not able to process input stream");
            }
            stopService.importRttaStops(rttaStops);
            response.setStatus(IConstants.RESPONSE_SUCCESS);
        } catch (Exception e) {
            response.setStatus(IConstants.RESPONSE_FAILURE);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    /**
     * upload Nodes.
     * @param uploadedInputStream uploadedInputStream
     * @return Response
     */
    @POST
    @Path("/nodes")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadNodes(@FormDataParam("file")InputStream uploadedInputStream) {
        final Response response = new Response();
        try {
            final RttaNodes rttaNodes = nodeBootManager.loadNodesFromInputStream(uploadedInputStream);
            if (rttaNodes == null) {
                response.setStatus(IConstants.RESPONSE_FAILURE);
                response.setMessage("Not able to process input stream");
            }
            nodeService.importRttaNodes(rttaNodes);
            response.setStatus(IConstants.RESPONSE_SUCCESS);
        } catch (Exception e) {
            response.setStatus(IConstants.RESPONSE_FAILURE);
            response.setMessage(e.getMessage());
        }
        return response;
    }
    /**
     * upload Gtfs Topology.
     * @param uploadedInputStream uploadedInputStream
     * @return Response
     */
    @POST
    @Path("/topology")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadTopology(@FormDataParam("file")InputStream uploadedInputStream) {
        final Response response = new Response();
        try {
            final RttaGtfsTopology rttaGtfsTopology = gtfsTopologyBootManager.loadTopologyFromInputStream(uploadedInputStream);
            if (rttaGtfsTopology == null) {
                response.setStatus(IConstants.RESPONSE_FAILURE);
                response.setMessage("Not able to process input stream");
            }
            topologyService.importRttaTopology(rttaGtfsTopology);
            response.setStatus(IConstants.RESPONSE_SUCCESS);
        } catch (Exception e) {
            response.setStatus(IConstants.RESPONSE_FAILURE);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    /**
     * upload nodal Geography.
     * @param uploadedInputStream uploadedInputStream
     * @return Response
     */
    @POST
    @Path("/nodalGeography")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadNodalGeography(@FormDataParam("file")InputStream uploadedInputStream) {
        final Response response = new Response();
        try {
            final CgGeography cgGeography = nodalGeographyBootManager.loadNodalGeographyFromInputStream(uploadedInputStream);
            if (cgGeography == null) {
                response.setStatus(IConstants.RESPONSE_FAILURE);
                response.setMessage("Not able to process input stream");
            }
            nodalGeographyService.importRailNetGeography(cgGeography);
            response.setStatus(IConstants.RESPONSE_SUCCESS);
        } catch (Exception e) {
            response.setStatus(IConstants.RESPONSE_FAILURE);
            response.setMessage(e.getMessage());
        }
        return response;
    }
}
