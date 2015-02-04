package au.gov.nsw.railcorp.rttarefdata.service;

import au.gov.nsw.railcorp.rtta.refint.generated.gtfs.RefGtfsLinePath;
import au.gov.nsw.railcorp.rtta.refint.generated.gtfs.RefGtfsNetwork;
import au.gov.nsw.railcorp.rtta.refint.generated.gtfs.RefGtfsNetworkLine;
import au.gov.nsw.railcorp.rtta.refint.generated.gtfs.RttaGtfsTopology;
import au.gov.nsw.railcorp.rttarefdata.domain.Network;
import au.gov.nsw.railcorp.rttarefdata.manager.IStopManager;
import au.gov.nsw.railcorp.rttarefdata.manager.ITopologyManager;
import au.gov.nsw.railcorp.rttarefdata.mapresult.LinePathData;
import au.gov.nsw.railcorp.rttarefdata.mapresult.LinePathStationData;
import au.gov.nsw.railcorp.rttarefdata.mapresult.NetworkLineData;
import au.gov.nsw.railcorp.rttarefdata.request.EdgeModel;
import au.gov.nsw.railcorp.rttarefdata.request.LineInfo;
import au.gov.nsw.railcorp.rttarefdata.request.NetworkVisModel;
import au.gov.nsw.railcorp.rttarefdata.util.IConstants;
import au.gov.nsw.railcorp.rttarefdata.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.StreamingOutput;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
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
    public NetworkVisModel getNetworkVisModel() {
        try {
            final NetworkVisModel networkVisModel = new NetworkVisModel();
            networkVisModel.setNodes(stopManager.getAllStations());
            final List<LinePathData> linePathDataList = topologyManager.getAllPathStation();
            for (LinePathData linePathData: linePathDataList) {
                extractEdges(networkVisModel, linePathData);
            }
            return networkVisModel;
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
        } catch (Exception e) {
            logger.error("Exception in extracting edges from linePath: ", e);
            return;
        }
    }

}


