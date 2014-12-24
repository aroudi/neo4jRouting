package au.gov.nsw.railcorp.rttarefdata.rest;

import au.gov.nsw.railcorp.rttarefdata.mapresult.*;
import au.gov.nsw.railcorp.rttarefdata.repositories.LinePathRepository;
import au.gov.nsw.railcorp.rttarefdata.repositories.PathStationRepository;
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
    /**
     * Return path station list in Json format.
     * @return path Station List
     */
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List getAllPathStation() {
        final List<LinePathData> result = new ArrayList<LinePathData>();
        final List<ILinePath> linePathList;
        List<ILinePathStation> linePathStationList;
        LinePathData linePathData;
        LinePathStationData linePathStationData;
        try {
            linePathList = linePathRepository.getAllLinePaths();
            for (ILinePath linePath: linePathList) {
                linePathData = new LinePathData();
                linePathData.setPathId(linePath.getPathId());
                linePathData.setName(linePath.getName());
                linePathData.setLongName(linePath.getLongName());
                linePathData.setLineName(linePath.getLongName());
                linePathData.setLineLongName(linePath.getLineLongName());
                linePathData.setBackgroundColourHex(linePath.getBackgroundColourHex());
                linePathData.setTextColourHex(linePath.getTextColourHex());
                linePathStationList = pathStationRepository.getAllLinePathStations(linePath.getName());
                for (ILinePathStation pathStation: linePathStationList) {
                    linePathStationData = new LinePathStationData();
                    linePathStationData.setStationId(pathStation.getStationId());
                    linePathStationData.setName(pathStation.getName());
                    linePathStationData.setLongName(pathStation.getLongName());
                    linePathStationData.setLatitude(pathStation.getLatitude());
                    linePathStationData.setLongtitude(pathStation.getLongtitude());
                    linePathStationData.setPathMatchInclude(pathStation.getPathMatchInclude());
                    linePathStationData.setSequence(pathStation.getSequence());
                    linePathData.addStationToPath(linePathStationData);
                }
                result.add(linePathData);
            }
            return result;
        } catch (Exception e) {
            logger.error("Exception in returning station list ", e);
            return null;
        }
    }
}
