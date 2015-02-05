package au.gov.nsw.railcorp.rttarefdata.manager;

import au.gov.nsw.railcorp.rttarefdata.domain.*;
import au.gov.nsw.railcorp.rttarefdata.mapresult.*;
import au.gov.nsw.railcorp.rttarefdata.repositories.*;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by arash on 7/11/14.
 */
@Component
@Transactional
public class TopologyManager implements ITopologyManager {
    private final Logger logger = LoggerFactory.getLogger(TopologyManager.class);
    @Autowired
    private NetworkRepository networkRepository;
    @Autowired
    private NetworkLineRepository networkLineRepository;
    @Autowired
    private LinePathRepository linePathRepository;
    @Autowired
    private StationRepository stationRepository;
    @Autowired
    private IDataTypeManager dataTypeManager;

    @Autowired
    private PathStationRepository pathStationRepository;

    /**
     * createNetwork.
     * @param name name
     * @param description description
     * @return Network
     */
    public Network createNetwork(String name, String description) {
        Network network = networkRepository.findBySchemaPropertyValue("name", name);
        if (network == null) {
            network = new Network();
        }
        network.setName(name);
        network.setDescription(description);
        return networkRepository.save(new Network(name, description, null, null, null, null, null));
    }

    /**
     * create network line.
     * @param name name
     * @param longName longname
     * @param backGroundColourHex backGroundColourHex
     * @param textColourHex textColourHex
     * @param networkName networkName
     * @param serviceTypeName serviceTypeName
     * @return NetworkLine
     */
    public NetworkLine createNetworkLine(String name, String longName, String backGroundColourHex, String textColourHex, String networkName, String serviceTypeName) {
        NetworkLine networkLine = networkLineRepository.findBySchemaPropertyValue("name", name);
        if (networkLine == null) {
            networkLine = new NetworkLine();
        }
        networkLine.setName(name);
        networkLine.setLongName(longName);
        networkLine.setBackgroundColourHex(backGroundColourHex);
        networkLine.setTextColourHex(textColourHex);
        final ServiceType serviceType = dataTypeManager.addServiceType(serviceTypeName, serviceTypeName);
        if (serviceType != null) {
            networkLine.setServiceType(serviceType);
        }
        final Network myNetwork = networkRepository.findBySchemaPropertyValue("name", networkName);
        if (myNetwork != null) {
            networkLine.setNetwork(myNetwork);
        }
        return networkLineRepository.save(networkLine);
    }
    /**
     * createLinePath.
     * @param lineName lineName
     * @param pathName pathName
     * @param longName longName
     * @param powerTypes powerTypes
     * @param interchagePoint interchagePoint
     * @param stationPath stationPath
     * @param pathMatchInclude pathMatchInclude
     * @return LinePath linePath
     */
    public LinePath createLinePath (String lineName, String pathName, String longName, Collection<String> powerTypes,
                                    String[] interchagePoint, String[] stationPath, String[] pathMatchInclude)
    {

        Station station;
        LinePath linePath = linePathRepository.findBySchemaPropertyValue("name", pathName);
        if (linePath == null) {
            linePath = new LinePath();
        }
        linePath.setName(pathName);
        linePath.setLongName(longName);
        final NetworkLine networkLine = networkLineRepository.findBySchemaPropertyValue("name", lineName);
        if (networkLine != null) {
            linePath.setNetworkLine(networkLine);
        }
        for (String powertypeName : powerTypes) {
            final PowerType powerType = dataTypeManager.addPowerType(powertypeName, powertypeName);
            if (powerType != null) {
                linePath.addPowerType(powerType);
            }
        }
        if (stationPath != null) {
            int sequence = 0;
            for (String stationName : stationPath) {
                sequence++;
                station = stationRepository.findBySchemaPropertyValue("shortName", stationName);
                if (station != null) {
                    boolean pathIsMatch = false;
                    for (String matchInclude : pathMatchInclude) {
                        if (matchInclude == null) {
                            continue;
                        }
                        if (matchInclude.equals(stationName)) {
                            pathIsMatch = true;
                            break;
                        }
                    }
                    linePath.addStationToPath(station, sequence, pathIsMatch);
                }
            }
        }
        linePathRepository.save(linePath);
        return linePath;
    }

    /**
     * Retrieve Network list from db.
     * @return list
     */
    public List<Network> getAllNetworks() {
        try {
            return Lists.newArrayList(networkRepository.findAll().iterator());
        } catch (Exception e) {
            logger.error("Exception in returning network list ", e);
            return null;
        }
    }
    /**
     * get All Network Lines.
     * @return List of Network Line
     */
    public List getAllNetworkLines() {
        final List<NetworkLineData> result = new ArrayList<NetworkLineData>();
        final List<INetworkLineData> lines;
        NetworkLineData networkLineData;
        try {
            lines = networkLineRepository.getAllNetworkLines();
            for (INetworkLineData line: lines) {
                networkLineData = new NetworkLineData();
                networkLineData.setLineId(line.getLineId());
                networkLineData.setNetworkName(line.getNetworkName());
                networkLineData.setName(line.getName());
                networkLineData.setLongName(line.getLongName());
                networkLineData.setBackgroundColourHex(line.getBackgroundColourHex());
                networkLineData.setTextColourHex(line.getTextColourHex());
                networkLineData.setServiceTypeName(line.getServiceTypeName());
                result.add(networkLineData);
            }
            return result;
        } catch (Exception e) {
            logger.error("Exception in returning line list ", e);
            return null;
        }
    }

    /**
     * Returns all path stations.
     * @return List of path station
     */
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
                linePathData.setLineName(linePath.getLineName());
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
                    linePathStationData.setInterchangePoint(pathStation.getInterchangePoint());
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

    /**
     * Returns all path stations per Netwrok.
     * @param networkName networkName
     * @return List of path station
     */
    public List getAllPathStationPerNetwork(String networkName) {
        final List<LinePathData> result = new ArrayList<LinePathData>();
        final List<ILinePath> linePathList;
        List<ILinePathStation> linePathStationList;
        LinePathData linePathData;
        LinePathStationData linePathStationData;
        try {
            linePathList = linePathRepository.getAllLinePathsPerNetwork(networkName);
            for (ILinePath linePath: linePathList) {
                linePathData = new LinePathData();
                linePathData.setPathId(linePath.getPathId());
                linePathData.setName(linePath.getName());
                linePathData.setLongName(linePath.getLongName());
                linePathData.setLineName(linePath.getLineName());
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
                    linePathStationData.setInterchangePoint(pathStation.getInterchangePoint());
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
