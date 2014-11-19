package au.gov.nsw.railcorp.rttarefdata.service;

import au.gov.nsw.railcorp.rtta.refint.generated.gtfs.RefGtfsLinePath;
import au.gov.nsw.railcorp.rtta.refint.generated.gtfs.RefGtfsNetwork;
import au.gov.nsw.railcorp.rtta.refint.generated.gtfs.RefGtfsNetworkLine;
import au.gov.nsw.railcorp.rtta.refint.generated.gtfs.RttaGtfsTopology;
import au.gov.nsw.railcorp.rttarefdata.manager.ITopologyManager;
import au.gov.nsw.railcorp.rttarefdata.util.IConstants;
import au.gov.nsw.railcorp.rttarefdata.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arash on 10/11/14.
 */
@Component
public class TopologyService {
    @Autowired
    private ITopologyManager topologyManager;

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
}
