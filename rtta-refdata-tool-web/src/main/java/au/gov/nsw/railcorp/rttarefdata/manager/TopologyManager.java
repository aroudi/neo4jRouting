package au.gov.nsw.railcorp.rttarefdata.manager;

import au.gov.nsw.railcorp.rttarefdata.domain.*;
import au.gov.nsw.railcorp.rttarefdata.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Created by arash on 7/11/14.
 */
@Component
@Transactional
public class TopologyManager implements ITopologyManager {
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

}
