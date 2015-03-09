package au.gov.nsw.railcorp.rttarefdata.service;

import au.gov.nsw.railcorp.rtta.refint.generated.gtfs.*;
import au.gov.nsw.railcorp.rtta.refint.generated.reference.ReferenceHeader;
import au.gov.nsw.railcorp.rttarefdata.domain.DataVersion;
import au.gov.nsw.railcorp.rttarefdata.domain.Network;
import au.gov.nsw.railcorp.rttarefdata.domain.PowerType;
import au.gov.nsw.railcorp.rttarefdata.domain.ServiceType;
import au.gov.nsw.railcorp.rttarefdata.manager.IStopManager;
import au.gov.nsw.railcorp.rttarefdata.manager.ITopologyManager;
import au.gov.nsw.railcorp.rttarefdata.mapresult.LinePathData;
import au.gov.nsw.railcorp.rttarefdata.mapresult.LinePathStationData;
import au.gov.nsw.railcorp.rttarefdata.mapresult.NetworkLineData;
import au.gov.nsw.railcorp.rttarefdata.request.EdgeModel;
import au.gov.nsw.railcorp.rttarefdata.request.LineInfo;
import au.gov.nsw.railcorp.rttarefdata.request.NetworkVisModel;
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
import java.util.Date;
import java.util.List;

/**
 * Created by arash on 10/11/14.
 */
@Component
public class TopologyService {

    private final Logger logger = LoggerFactory.getLogger(TopologyService.class);
    @Autowired
    private ITopologyManager topologyManager;
    @Autowired
    private IStopManager stopManager;
    @Autowired
    private SessionState sessionState;
    /**
     * import Rtta Gtfs Topology object.
     * @param rttaGtfsTopology rttaGtfsTopology
     */
    public void importRttaTopology(RttaGtfsTopology rttaGtfsTopology) {
        if (rttaGtfsTopology == null) {
            return;
        }
        importNetwork(rttaGtfsTopology.getGtfsTopologyV01().getNetworks().getNetwork());
        importNetworkLine(rttaGtfsTopology.getGtfsTopologyV01().getNetworkLines().getNetworkLine());
    }

    /**
     * import Network.
     * @param networks networks
     */
    public void importNetwork(List<RefGtfsNetwork> networks) {
        if (networks == null) {
            return;
        }
        for (RefGtfsNetwork network:networks) {
            if (network == null) {
                continue;
            }
            topologyManager.createNetwork(network.getNetworkName(), network.getLongName());
        }
    }

    /**
     * import Network Line.
     * @param networkLines networkLines
     */
    public void importNetworkLine(List<RefGtfsNetworkLine> networkLines) {
        if (networkLines == null) {
            return;
        }
        for (RefGtfsNetworkLine networkLine:networkLines) {
            if (networkLine == null) {
                continue;
            }
            topologyManager.createNetworkLine(networkLine.getNetworkLineName(), networkLine.getLongName(), networkLine.getLineColourHex(),
                    networkLine.getTextColourHex(), networkLine.getNetworkName(), networkLine.getEServiceType().value());
            importLinePath(networkLine, networkLine.getLinePaths().getLinePath());
        }
    }

    /**
     * import Line Path.
     * @param networkLine networkLine
     * @param linePaths linePaths
     */
    public void importLinePath (RefGtfsNetworkLine networkLine, List<RefGtfsLinePath> linePaths) {
        List<String> powerTypes;
        String[] stationPath;
        String[] pathMatchInclude;
        String[] interchagePoints;
        if (linePaths == null) {
            logger.error("importLinePath: linePath is null");
            return;
        }
        for (RefGtfsLinePath linePath:linePaths) {
            if (linePath == null) {
                continue;
            }
            powerTypes = new ArrayList<String>();
            if (linePath.isDieselPath()) {
                powerTypes.add(IConstants.POWER_TYPE_DIESEL);
            }
            if (linePath.isDieselPath()) {
                powerTypes.add(IConstants.POWER_TYPE_ELECTRIC);
            }

            stationPath = StringUtil.splitString(linePath.getStationPath(), " ");
            pathMatchInclude = StringUtil.splitString(linePath.getPathMatchInclude(), " ");
            interchagePoints = StringUtil.splitString(linePath.getInterchangePoints(), " ");
            topologyManager.createLinePath(networkLine.getNetworkLineName(), linePath.getLinePathName(), linePath.getLongName(), powerTypes, interchagePoints, stationPath, pathMatchInclude);

        }
    }

    /**
     * get All Network Lines.
     * @return List of Network Line
     */
    public List getAllNetworkLines() {
        return topologyManager.getAllNetworkLines();
    }
    /**
     * get All path stations.
     * @return list of path stations.
     */
    public List getAllPathStation() { return topologyManager.getAllPathStation(); }

    /**
     * Export Network list into csv format and return as StreamingOutput.
     * @return Streamingoutput
     */
    public StreamingOutput exportNetworkToCsv() {
        StreamingOutput streamingOutput = null;
        try {
            streamingOutput = new StreamingOutput() {
                @Override
                public void write(OutputStream output) throws IOException, WebApplicationException {
                    final List<Network> networkList = topologyManager.getAllNetworks();
                    if (networkList == null) {
                        return;
                    }
                    String line = "NetworkName, LongName";
                    output.write(line.getBytes());
                    output.write(System.getProperty("line.separator").getBytes());
                    for (Network network : networkList) {
                        line = network.getName() + ", " + network.getDescription();
                        output.write(line.getBytes());
                        output.write(System.getProperty("line.separator").getBytes());
                    }
                    output.flush();
                }
            };
            return streamingOutput;
        } catch (Exception e) {
            logger.error("Exception in writing networks into csv: ", e);
            return null;
        }
    }

    /**
     * Export Network line list into csv format and return as StreamingOutput.
     * @return Streamingoutput
     */
    public StreamingOutput exportNetworkLinesToCsv() {
        StreamingOutput streamingOutput = null;
        try {
            streamingOutput = new StreamingOutput() {
                @Override
                public void write(OutputStream output) throws IOException, WebApplicationException {
                    final List<NetworkLineData> networkLineList = topologyManager.getAllNetworkLines();
                    if (networkLineList == null) {
                        return;
                    }
                    String line = "NetworkName, NetworkLineName, LongName, ServiceType, BackgroundColourHex, TextColourHex ";
                    output.write(line.getBytes());
                    output.write(System.getProperty("line.separator").getBytes());
                    for (NetworkLineData networkLineData : networkLineList) {
                        line = networkLineData.getNetworkName() + ", " + networkLineData.getName() + ", " + networkLineData.getLongName() + ", "
                                + networkLineData.getServiceTypeName() + ", " + networkLineData.getBackgroundColourHex() + ", " + networkLineData.getTextColourHex();
                        output.write(line.getBytes());
                        output.write(System.getProperty("line.separator").getBytes());
                    }
                    output.flush();
                }
            };
            return streamingOutput;
        } catch (Exception e) {
            logger.error("Exception in writing networks into csv: ", e);
            return null;
        }
    }

    /**
     * Export path list into csv format and return as StreamingOutput.
     * @return Streamingoutput
     */
    public StreamingOutput exportLinePathsToCsv() {
        StreamingOutput streamingOutput = null;
        try {
            streamingOutput = new StreamingOutput() {
                @Override
                public void write(OutputStream output) throws IOException, WebApplicationException {
                    final List<LinePathData> linePathDataList = getAllPathStation();
                    ExtractedData extractedData;
                    if (linePathDataList == null) {
                        return;
                    }
                    String line = "NetworkLineName, LinePathName, LongName, InterchangePoints, StationPath, PathMatchInclude";
                    output.write(line.getBytes());
                    output.write(System.getProperty("line.separator").getBytes());
                    for (LinePathData linePathData : linePathDataList) {
                        extractedData = extractData(linePathData.getLinePathStationList());
                        line = linePathData.getLineName() + ", " + linePathData.getName() + ", " + linePathData.getLongName() + ", ";
                        if (extractedData != null) {
                            if (!extractedData.extractedInterchangePoint.toString().isEmpty()) {
                                line = line + extractedData.extractedInterchangePoint.toString() + ", ";
                            } else {
                                line = line + "null" + ", ";
                            }
                            if (!extractedData.extractedStationPath.toString().isEmpty()) {
                                line = line + extractedData.extractedStationPath.toString() + ", ";
                            } else {
                                line = line + "null" + ", ";
                            }
                            if (!extractedData.extractedPathMatchInclude.toString().isEmpty()) {
                                line = line + extractedData.extractedPathMatchInclude.toString();
                            } else {
                                line = line + "null";
                            }
                        } else {
                            line = line + "null" + ", " + "null" + ", " + "null";
                        }
                        output.write(line.getBytes());
                        output.write(System.getProperty("line.separator").getBytes());
                    }
                    output.flush();
                }
            };
            return streamingOutput;
        } catch (Exception e) {
            logger.error("Exception in writing networks into csv: ", e);
            return null;
        }
    }

    /**
     * Extracing required data from station data.
     */
    public class ExtractedData {
        private StringBuffer extractedPathMatchInclude = new StringBuffer("");
        private StringBuffer extractedInterchangePoint = new StringBuffer("");
        private StringBuffer extractedStationPath = new StringBuffer("");

    }

    /**
     * extracting InterchangePoints and PathMatchIncludes.
     * @param linePathStationDatas linePathStationDatas.
     * @return ExtractedData
     */
    public ExtractedData extractData(List<LinePathStationData> linePathStationDatas) {
        if (linePathStationDatas == null) {
            return null;
        }
        final ExtractedData extractedData = new ExtractedData();
        for (LinePathStationData stationData: linePathStationDatas) {
            if (stationData.isInterchangePoint()) {
                extractedData.extractedInterchangePoint.append(stationData.getName()).append(" ");
            }
            if (stationData.isPathMatchInclude()) {
                extractedData.extractedPathMatchInclude.append(stationData.getName()).append(" ");
            }
            extractedData.extractedStationPath.append(stationData.getName()).append(" ");
        }
        return extractedData;
    }

    /**
     * return all networks.
     * @return List of Networks.
     */
    public List getAllNetworks() {
        try {
            return topologyManager.getAllNetworks();
        } catch (Exception e) {
            logger.error("Exception in returning network list ", e);
            return null;
        }
    }

    /**
     * get network in vis.js format for visualizing it on screen.
     * @return NetworkVisModel
     */
    public List<NetworkVisModel> getNetworkVisModel() {
        try {
            final List<NetworkVisModel> networkVisModelList = new ArrayList<NetworkVisModel>();
            NetworkVisModel networkVisModel;
            final List<Network> networkList = topologyManager.getAllNetworks();
            for (Network network : networkList) {
                if (network == null) {
                    continue;
                }
                networkVisModel = new NetworkVisModel();
                networkVisModel.setNetworkName(network.getName());
                final List<LinePathData> linePathDataList = topologyManager.getAllPathStationPerNetwork(network.getName());
                for (LinePathData linePathData: linePathDataList) {
                    extractEdges(networkVisModel, linePathData);
                }
                networkVisModelList.add(networkVisModel);
            }
            return networkVisModelList;
        } catch (Exception e) {
            logger.error("Exception in returning networkVisModel :", e);
            return null;
        }
    }

    /**
     * extract Edges from linePathData.
     * @param networkVisModel networkVisModel
     * @param linePathData linePathData
     */
    public void extractEdges(NetworkVisModel networkVisModel, LinePathData linePathData) {
        try {
            final List<LinePathStationData> linePathStationList = linePathData.getLinePathStationList();
            if (linePathData == null || linePathData.getLinePathStationList() == null) {
                return;
            }
            LinePathStationData fromNode;
            LinePathStationData toNode;
            String mainKey;
            String reverseKey;
            EdgeModel edgeModel;
            LineInfo lineInfo;
            for (int i = 0; i < linePathStationList.size() - 2; i++) {
                fromNode = linePathStationList.get(i);
                toNode = linePathStationList.get(i + 1);
                mainKey = fromNode.getName() + toNode.getName();
                reverseKey = toNode.getName() + fromNode.getName();
                edgeModel = new EdgeModel();
                edgeModel.setFromNode(fromNode.getStationId());
                edgeModel.setToNode(toNode.getStationId());
                lineInfo = new LineInfo();
                lineInfo.setName(linePathData.getLineName());
                lineInfo.setLongName(linePathData.getLineLongName());
                lineInfo.setBackgroundColourHex(linePathData.getBackgroundColourHex());
                lineInfo.setTextColourHex(linePathData.getTextColourHex());
                networkVisModel.addEdge(mainKey, reverseKey, edgeModel, lineInfo);
                networkVisModel.addStation(fromNode);
            }
            final int edgesNo = linePathStationList.size();
            fromNode = linePathStationList.get(edgesNo - 2);
            toNode = linePathStationList.get(edgesNo - 1);
            mainKey = fromNode.getName() + toNode.getName();
            reverseKey = toNode.getName() + fromNode.getName();
            edgeModel = new EdgeModel();
            edgeModel.setFromNode(fromNode.getStationId());
            edgeModel.setToNode(toNode.getStationId());
            lineInfo = new LineInfo();
            lineInfo.setName(linePathData.getLineName());
            lineInfo.setLongName(linePathData.getLineLongName());
            lineInfo.setBackgroundColourHex(linePathData.getBackgroundColourHex());
            lineInfo.setTextColourHex(linePathData.getTextColourHex());
            networkVisModel.addEdge(mainKey, reverseKey, edgeModel, lineInfo);
            networkVisModel.addStation(fromNode);
            networkVisModel.addStation(toNode);
        } catch (Exception e) {
            logger.error("Exception in extracting edges from linePath: ", e);
            return;
        }
    }

    /**
     * build RttaGtfsTopology object.
     * @return RttaGtfsTopology
     */
    public RttaGtfsTopology buildGtfsTopology() {
        final RttaGtfsTopology rttaGtfsTopology = new RttaGtfsTopology();

        final ReferenceHeader referenceHeader = new ReferenceHeader();
        final GtfsTopologyV01 gtfsTopologyV01 = new GtfsTopologyV01();
        RefGtfsNetwork refGtfsNetwork;
        final RefGtfsNetworks refGtfsNetworks = new RefGtfsNetworks();
        final RefGtfsNetworkLines refGtfsNetworkLines = new RefGtfsNetworkLines();
        RefGtfsLinePaths refGtfsLinePaths;
        RefGtfsLinePath refGtfsLinePath;
        RefGtfsPathStation refGtfsPathStation;
        List<PowerType> powerTypes;
        List<LinePathStationData> linePathStationDataList;
        RefGtfsNetworkLine refGtfsNetworkLine;
        LinePathData firstLinePath = null;
        ServiceType serviceType;

        //build reference header.
        referenceHeader.setContent("RttaGtfsTopology");
        referenceHeader.setContentManager("GtfsTopologyMessage");
        referenceHeader.setContentVersion("0.1");
        referenceHeader.setDescription("TfNSW RTTA Gtfs Data Bundle");
        referenceHeader.setComment("Generated by RttaRefDataTool");
        referenceHeader.setSequence("0");
        final Date currentDate = new Date();
        referenceHeader.setDate(currentDate.toString());
        rttaGtfsTopology.setReferenceHeader(referenceHeader);


        final List<Network> networkList = topologyManager.getAllNetworks();
        for (Network network : networkList) {
            if (network == null) {
                continue;
            }
            refGtfsNetwork = new RefGtfsNetwork();
            refGtfsNetwork.setNetworkName(network.getName());
            refGtfsNetwork.setLongName(network.getDescription());
            // setNetwork
            refGtfsNetworks.getNetwork().add(refGtfsNetwork);

            // get network lines
            final List<LinePathData> linePathDataList = topologyManager.getAllPathStationPerNetwork(network.getName());
            if (linePathDataList == null || linePathDataList.size() < 1) {
                continue;
            }
            firstLinePath = linePathDataList.get(0);
            if (firstLinePath == null) {
                continue;
            }
            String lineName = firstLinePath.getLineName();
            refGtfsNetworkLine = new RefGtfsNetworkLine();
            refGtfsNetworkLine.setNetworkName(network.getName());
            refGtfsNetworkLine.setNetworkLineName(firstLinePath.getLineName());
            refGtfsNetworkLine.setLongName(firstLinePath.getLineLongName());
            refGtfsNetworkLine.setLineColourHex(firstLinePath.getBackgroundColourHex());
            refGtfsNetworkLine.setTextColourHex(firstLinePath.getTextColourHex());
            serviceType = topologyManager.getLineServiceType(firstLinePath.getLineName());

            if (serviceType != null) {
                refGtfsNetworkLine.setEServiceType(EGtfsServiceType.fromValue(serviceType.getName()));
            }
            refGtfsLinePaths = new RefGtfsLinePaths();
            for (LinePathData linePathData: linePathDataList) {
                if (linePathData == null) {
                    continue;
                }
                if (!linePathData.getLineName().equals(lineName)) {
                    refGtfsNetworkLine.setLinePaths(refGtfsLinePaths);
                    refGtfsNetworkLines.getNetworkLine().add(refGtfsNetworkLine);

                    refGtfsNetworkLine = new RefGtfsNetworkLine();
                    refGtfsNetworkLine.setNetworkName(network.getName());
                    refGtfsNetworkLine.setNetworkLineName(linePathData.getLineName());
                    refGtfsNetworkLine.setLongName(linePathData.getLineLongName());
                    refGtfsNetworkLine.setLineColourHex(linePathData.getBackgroundColourHex());
                    refGtfsNetworkLine.setTextColourHex(linePathData.getTextColourHex());
                    serviceType = topologyManager.getLineServiceType(linePathData.getLineName());
                    if (serviceType != null) {
                        refGtfsNetworkLine.setEServiceType(EGtfsServiceType.fromValue(serviceType.getName()));
                    }
                    lineName = linePathData.getLineName();
                    refGtfsLinePaths = new RefGtfsLinePaths();
                }
                if (linePathData == null) {
                    continue;
                }
                refGtfsLinePath = new RefGtfsLinePath();
                refGtfsLinePath.setLinePathName(linePathData.getName());
                refGtfsLinePath.setLongName(linePathData.getLongName());
                powerTypes = topologyManager.getLinePathPowerTypes(linePathData.getName());
                for (PowerType powerType : powerTypes) {
                    if (powerType == null) {
                        continue;
                    }
                    if (powerType.getName() == IConstants.POWER_TYPE_DIESEL) {
                        refGtfsLinePath.isDieselPath();
                    }
                    if (powerType.getName() == IConstants.POWER_TYPE_ELECTRIC) {
                        refGtfsLinePath.isElectricPath();
                    }
                }
                linePathStationDataList = linePathData.getLinePathStationList();
                StringBuffer stationPath = new StringBuffer("");
                final StringBuffer pathMatchInclude = new StringBuffer("");
                final StringBuffer interchangPoint =  new StringBuffer("");
                if (linePathStationDataList != null) {
                    for (LinePathStationData linePathStationData : linePathStationDataList) {
                        if (linePathStationData == null) {
                            continue;
                        }
                        stationPath = stationPath.append(linePathStationData.getName());
                        stationPath = stationPath.append(" ");
                        if (linePathStationData.isPathMatchInclude()) {
                            pathMatchInclude.append(linePathStationData.getName());
                            pathMatchInclude.append(" ");
                        }
                        if (linePathStationData.isInterchangePoint()) {
                            interchangPoint.append(linePathStationData.getName());
                            interchangPoint.append(" ");
                        }
                        refGtfsPathStation = new RefGtfsPathStation();
                        refGtfsPathStation.setStationTSNID(StringUtil.longToStr(linePathStationData.getGtfsStopId()));
                        refGtfsPathStation.setShortName(linePathStationData.getName());
                        refGtfsLinePath.getStation().add(refGtfsPathStation);
                    }
                }
                if (stationPath.length() > 1) {
                    stationPath.delete(stationPath.length() - 1, stationPath.length());
                }
                if (pathMatchInclude.length() > 1) {
                    pathMatchInclude.delete(pathMatchInclude.length() - 1, pathMatchInclude.length());
                }
                if (interchangPoint.length() > 1) {
                    interchangPoint.delete(interchangPoint.length() - 1, interchangPoint.length());
                }
                refGtfsLinePath.setStationPath(stationPath.toString());
                refGtfsLinePath.setPathMatchInclude(pathMatchInclude.toString());
                refGtfsLinePath.setInterchangePoints(interchangPoint.toString());
                refGtfsLinePaths.getLinePath().add(refGtfsLinePath);
            }
            refGtfsNetworkLine.setLinePaths(refGtfsLinePaths);
            refGtfsNetworkLines.getNetworkLine().add(refGtfsNetworkLine);
        }
        gtfsTopologyV01.setNetworks(refGtfsNetworks);
        gtfsTopologyV01.setNetworkLines(refGtfsNetworkLines);
        rttaGtfsTopology.setGtfsTopologyV01(gtfsTopologyV01);
        return rttaGtfsTopology;
    }
    /**
     * Export Gtfs Topology Networkinto xml format and return as StreamingOutput.
     * @return Streamingoutput
     */
    public StreamingOutput exportGtfsTopologyToXml() {
        StreamingOutput streamingOutput = null;
        try {
            final StringWriter writer = new StringWriter();
            final JAXBContext context = JAXBContext.newInstance(RttaGtfsTopology.class);
            final Marshaller marshaller = context.createMarshaller();

            final RttaGtfsTopology rttaGtfsTopology = buildGtfsTopology();
            marshaller.marshal(rttaGtfsTopology, writer);
            final String theXml = writer.toString();
            streamingOutput = new StreamingOutput() {
                @Override
                public void write(OutputStream output) throws IOException, WebApplicationException {
                    output.write(theXml.getBytes());
                    output.flush();
                }
            };
            return streamingOutput;
        } catch (Exception e) {
            logger.error("Exception in writing RttaGtfsTopology into xml: ", e);
            return null;
        }
    }
    /**
     * clone Topology.
     * @param fromVersion fromVersion
     * @param toVersion toVersion
     * @return boolean
     */
    @Transactional
    public boolean cloneTopology(DataVersion fromVersion, DataVersion toVersion) {
        //temporary save the current version
        final DataVersion currentWorkingVersion = sessionState.getWorkingVersion();
        try {
            if (fromVersion == null || toVersion == null) {
                return false;
            }
            sessionState.setWorkingVersion(fromVersion);
            //fetch all current stops
            final RttaGtfsTopology gtfsTopology = buildGtfsTopology();
            sessionState.setWorkingVersion(toVersion);
            importRttaTopology(gtfsTopology);
            sessionState.setWorkingVersion(currentWorkingVersion);
        } catch (Exception e) {
            logger.error("Exception in clonning stops : ", e);
            sessionState.setWorkingVersion(toVersion);
            return false;
        }
        return true;
    }

}
