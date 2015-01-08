package au.gov.nsw.railcorp.rttarefdata.manager;

import au.gov.nsw.railcorp.rttarefdata.domain.LinePath;
import au.gov.nsw.railcorp.rttarefdata.domain.Network;
import au.gov.nsw.railcorp.rttarefdata.domain.NetworkLine;

import java.util.Collection;
import java.util.List;

/**
 * Created by arash on 10/11/14.
 */
public interface ITopologyManager {
    /**
     * Create Network.
     * @param name name
     * @param description description
     * @return Network
     */
    Network createNetwork(String name, String description);

    /**
     * Create Network Line.
     * @param name name
     * @param longName longName
     * @param backGroundColourHex backGroundColourHex
     * @param textColourHex textColourHex
     * @param networkName networkName
     * @param serviceTypeName serviceTypeName
     * @return NetworkLine
     */
    NetworkLine createNetworkLine(String name, String longName, String backGroundColourHex, String textColourHex, String networkName, String serviceTypeName);


    /**
     * Create Line Path.
     * @param lineName lineName
     * @param pathName pathName
     * @param longName longName
     * @param powerTypes powerTypes
     * @param interchagePoint interchagePoint
     * @param stationPath stationPath
     * @param pathMatchInclude pathMatchInclude
     * @return LinePath
     */
    LinePath createLinePath (String lineName, String pathName, String longName, Collection<String> powerTypes,
                                    String[] interchagePoint, String[] stationPath, String[] pathMatchInclude);
    /**
     * Retrieve Network list from db.
     * @return list
     */
    List<Network> getAllNetworks();

    /**
     * get All Network Lines.
     * @return List of Network Line
     */
    List getAllNetworkLines();

    /**
     * get all path stations.
     * @return list of path stations
     */
    List getAllPathStation();

    }
