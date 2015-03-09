package au.gov.nsw.railcorp.rttarefdata.service;

import au.gov.nsw.railcorp.rtta.refint.generated.stops.RefStop;
import au.gov.nsw.railcorp.rtta.refint.generated.stops.RefStopLink;
import au.gov.nsw.railcorp.rtta.refint.generated.stops.RefStopLinks;
import au.gov.nsw.railcorp.rtta.refint.generated.stops.RttaStops;
import au.gov.nsw.railcorp.rttarefdata.domain.DataVersion;
import au.gov.nsw.railcorp.rttarefdata.manager.IStopManager;
import au.gov.nsw.railcorp.rttarefdata.mapresult.StationData;
import au.gov.nsw.railcorp.rttarefdata.mapresult.StationPlatformData;
import au.gov.nsw.railcorp.rttarefdata.session.SessionState;
import au.gov.nsw.railcorp.rttarefdata.util.IConstants;
import au.gov.nsw.railcorp.rttarefdata.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.StreamingOutput;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by arash on 7/11/14.
 */
@Component
public class StopService {
    private final Logger logger = LoggerFactory.getLogger(TopologyService.class);

    @Autowired
    private IStopManager stopManager;
    @Autowired
    private SessionState sessionState;
    /**
     * import rtta stops.
     * @param rttaStops rttaStops object resulted from xml file or from input stream.
     */
    public void importRttaStops(RttaStops rttaStops) {
        importStations(rttaStops.getStopsV01().getStops().getStop());
        importPlatforms(rttaStops.getStopsV01().getStops().getStop());
        importStopLinks(rttaStops.getStopsV01().getStopLinks().getLink());
    }

    /**
     * import platfroms.
     * @param stops list of platforms
     */
    public void importPlatforms(List<RefStop> stops) {
        if (stops == null) {
            return;
        }
        for (RefStop refStop : stops) {
            if (refStop == null || refStop.isStationStop()) {
                continue;
            }
            stopManager.createPlatform(
                    StringUtil.strToInt(refStop.getParentStopId()),
                    StringUtil.strToInt(refStop.getStopId()),
                    refStop.getName(),
                    refStop.getLongName(),
                    StringUtil.extractPlatformNoFromNodeName(refStop.getName()),
                    null,
                    refStop.getLatitude(),
                    refStop.getLongitude());
        }
    }

    /**
     * import Stations.
     * @param stops list of station
     */
    public void importStations(List<RefStop> stops) {
        if (stops == null) {
            return;
        }
        for (RefStop refStop : stops) {
            if (refStop == null || !refStop.isStationStop()) {
                continue;
            }
            stopManager.createStation(refStop.getName(), refStop.getLongName(), StringUtil.strToInt(refStop.getStopId()), refStop.getLatitude(), refStop.getLongitude());
        }
    }

    /**
     * import stop links.
     * @param stopLinks stopLinks
     */
    public void importStopLinks(List<RefStopLink> stopLinks) {
        List<String> linkPowers;
        if (stopLinks == null) {
            return;
        }
        for (RefStopLink stopLink: stopLinks) {
            if (stopLink == null) {
                continue;
            }
            linkPowers = new ArrayList<String>();
            if (stopLink.isDiesel()) {
                linkPowers.add(IConstants.POWER_TYPE_DIESEL);
            }
            if (stopLink.isElectric()) {
                linkPowers.add(IConstants.POWER_TYPE_ELECTRIC);
            }
            stopManager.createTriplet(StringUtil.strToInt(stopLink.getInStopId()),
                    StringUtil.strToInt(stopLink.getStopId()),
                    StringUtil.strToInt(stopLink.getOutStopId()),
                    stopLink.isReversible(),
                    linkPowers);
        }
    }

    /**
     * return all stations.
     * @return list of stations
     */
    public List getAllStations() {
        return stopManager.getAllStations();
    }

    /**
     * get all platforms.
     * @return platform list
     */
    public List getAllPlatforms() {
        return stopManager.getAllPlatforms();
    }

    /**
     * Export Station list into csv format and return as StreamingOutput.
     * @return Streamingoutput
     */
    public StreamingOutput exportStationToCsv() {
        StreamingOutput streamingOutput = null;
        try {
            streamingOutput = new StreamingOutput() {
                @Override
                public void write(OutputStream output) throws IOException, WebApplicationException {
                    final List<StationData> stationList = stopManager.getAllStations();
                    if (stationList == null) {
                        return;
                    }
                    String line = "STATION_ID, STATION_NAME, GTFS_STOP_ID, LATITUDE, LONGITUDE ";
                    output.write(line.getBytes());
                    output.write(System.getProperty("line.separator").getBytes());
                    for (StationData stationData : stationList) {
                        line = stationData.getStationId() + ", " + stationData.getLongName() + ", " + stationData.getGtfsStopId() + ", "
                                + stationData.getLatitude() + ", " + stationData.getLongtitude();
                        output.write(line.getBytes());
                        output.write(System.getProperty("line.separator").getBytes());
                    }
                    output.flush();
                }
            };
            return streamingOutput;
        } catch (Exception e) {
            logger.error("Exception in writing stations into csv: ", e);
            return null;
        }
    }

    /**
     * Export Platform list into csv format and return as StreamingOutput.
     * @return Streamingoutput
     */
    public StreamingOutput exportPlatformsToCsv() {
        StreamingOutput streamingOutput = null;
        try {
            streamingOutput = new StreamingOutput() {
                @Override
                public void write(OutputStream output) throws IOException, WebApplicationException {
                    final List<StationPlatformData> platformList = stopManager.getAllPlatforms();
                    if (platformList == null) {
                        return;
                    }
                    String line = "GTFS_STOP_ID, NODE_NAME, STATION_ID, PLATFORM_ID, PLATFORM_NAME, LATITUDE, LONGITUDE";
                    output.write(line.getBytes());
                    output.write(System.getProperty("line.separator").getBytes());
                    for (StationPlatformData platformData : platformList) {
                        line = platformData.getPlatformStopId() + ", " + platformData.getPlatformName() + ", " + platformData.getStationId() + ", "
                                + platformData.getPlatformNo() + ", " + platformData.getLatitude() + ", " + platformData.getLongtitude();
                        output.write(line.getBytes());
                        output.write(System.getProperty("line.separator").getBytes());
                    }
                    output.flush();
                }
            };
            return streamingOutput;
        } catch (Exception e) {
            logger.error("Exception in writing platforms into csv: ", e);
            return null;
        }
    }

    /**
     * Export Stop Links list into csv format and return as StreamingOutput.
     * @return Streamingoutput
     */
    public StreamingOutput exportStopLinksToCsv() {
        StreamingOutput streamingOutput = null;
        try {
            streamingOutput = new StreamingOutput() {
                @Override
                public void write(OutputStream output) throws IOException, WebApplicationException {
                    String line = "InStopId, StopId, OutStopId, InStopName, StopName, OutStopName, Reversible, Electric, Diesel ";
                    output.write(line.getBytes());
                    output.write(System.getProperty("line.separator").getBytes());
                    final RefStopLinks refStopLinks = stopManager.buildStopLinks();
                    for (RefStopLink refStopLink: refStopLinks.getLink()) {
                        line = refStopLink.getInStopId() + ", " + refStopLink.getStopId() + ", " + refStopLink.getOutStopId() + ", "
                                + refStopLink.getInStopName() + ", " + refStopLink.getStopName() + ", " + refStopLink.getOutStopName() + ", " + refStopLink.isReversible()
                                + refStopLink.isElectric() + refStopLink.isDiesel();
                        output.write(line.getBytes());
                        output.write(System.getProperty("line.separator").getBytes());
                    }
                    output.flush();
                }
            };
            return streamingOutput;
        } catch (Exception e) {
            logger.error("Exception in writing stoplinks into csv: ", e);
            return null;
        }
    }

    /**
     * Export Stop list into xml format and return as StreamingOutput.
     * @return Streamingoutput
     */
    public StreamingOutput exportStopsToXml() {
        StreamingOutput streamingOutput = null;
        try {
            final StringWriter writer = new StringWriter();
            final JAXBContext context = JAXBContext.newInstance(RttaStops.class);
            final Marshaller marshaller = context.createMarshaller();

            final RttaStops rttaStops = stopManager.buildStopList();
            marshaller.marshal(rttaStops, writer);
            final String theXml = writer.toString();
            //logger.info(" exported nodalGegoraphy: ");
            //logger.info(theXml);
            streamingOutput = new StreamingOutput() {
                @Override
                public void write(OutputStream output) throws IOException, WebApplicationException {
                    output.write(theXml.getBytes());
                    output.flush();
                }
            };
            return streamingOutput;
        } catch (Exception e) {
            logger.error("Exception in writing RttaStops into xml: ", e);
            return null;
        }
    }

    /**
     * clone rtta stops.
     * @param fromVersion fromVersion
     * @param toVersion toVersion
     * @return boolean
     */
    @Transactional
    public boolean cloneStops(DataVersion fromVersion, DataVersion toVersion) {
        final DataVersion currentWorkingVersion = sessionState.getWorkingVersion();
        try {
           if (fromVersion == null || toVersion == null) {
                 return false;
           }
            //temporary save the current version
            sessionState.setWorkingVersion(fromVersion);
            //fetch all current stops
            final RttaStops rttaStops = stopManager.buildStopList();
            sessionState.setWorkingVersion(toVersion);
            importRttaStops(rttaStops);
            sessionState.setWorkingVersion(currentWorkingVersion);
        } catch (Exception e) {
            logger.error("Exception in clonning stops : ", e);
            sessionState.setWorkingVersion(toVersion);
            return false;
        }
        return true;
    }

}
