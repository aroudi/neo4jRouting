// RailCorp 2014
package au.gov.nsw.railcorp.rttarefdata.manager;

import au.gov.nsw.railcorp.rttarefdata.domain.*;
import au.gov.nsw.railcorp.rttarefdata.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



/**
 * Created by arash on 29/10/14.
 */

@Service
@Transactional
public class ServiceTest {

    /**
     * serviceTypeRepository.
     */
    @Autowired
    private ServiceTypeRepository serviceTypeRepository;

    /**
     * powerTypeRepository.
     */
    @Autowired
    private PowerTypeRepository powerTypeRepository;

    /**
     * networkRepository.
     */
    @Autowired
    private NetworkRepository networkRepository;

    /**
     * networkLineRepository.
     */
    @Autowired
    private NetworkLineRepository networkLineRepository;

    /**
     * linePathRepository;.
     */
    @Autowired
    private LinePathRepository linePathRepository;

    /**
     * stationRepository.
     */
    @Autowired
    private StationRepository stationRepository;

    /**
     * platformRepository.
     */
    @Autowired
    private PlatformRepository platformRepository;

    /**
     * stationTripletRepository.
     */
    @Autowired
    private StationTripletRepository stationTripletRepository;

    @Autowired
    private SpeedBandRepository speedBandRepository;
    @Autowired
    private RuningTimeRepository runingTimeRepository;
    @Autowired
    private NodeLinkRepository nodeLinkRepository;
    @Autowired
    private TrackSectionRepository trackSectionRepository;
    @Autowired
    private TurnPenaltyBanRepository turnPenaltyBanRepository;
    @Autowired
    private NodeRepository nodeRepository;
    @Autowired
    private GaugeRepository gaugeRepository;

    @Autowired
    private NodeLinkageRepository nodeLinkageRepository;
    /**
     * createServiceType.
     * @param name name
     * @param description description
     * @return ServiceType
     */
    public ServiceType createServiceType(String name, String description) {
        return serviceTypeRepository.save(new ServiceType(name, description));
    }

    /**
     * createPowerType.
     * @param name name
     * @param description description
     * @return PowerType
     */
    public PowerType createPowerType(String name, String description) {
        return powerTypeRepository.save(new PowerType(name, description));
    }

    /**
     * createNetwork.
     * @param name name
     * @param description description
     * @return Network
     */
    public Network createNetwork(String name, String description) {
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
        final NetworkLine networkLine = new NetworkLine(name, longName, backGroundColourHex, textColourHex);

        final ServiceType serviceType = serviceTypeRepository.findBySchemaPropertyValue("name", serviceTypeName);
        networkLine.setServiceType(serviceType);

        final Network myNetwork = networkRepository.getNetworkPerName("", networkName);
        networkLine.setNetwork(myNetwork);


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
    public LinePath createLinePath (String lineName, String pathName, String longName, String[] powerTypes, String[] interchagePoint, String[] stationPath, String[] pathMatchInclude) {

        Station station;
        final LinePath linePath = new LinePath(pathName, longName);

        final NetworkLine networkLine = networkLineRepository.getLinePerName("", lineName);
        linePath.setNetworkLine(networkLine);

        for (String powertypeName : powerTypes) {
            final PowerType powerType = powerTypeRepository.findBySchemaPropertyValue("name", powertypeName);
            linePath.addPowerType(powerType);
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

    /**
     * create station.
     * @param stationId stationId
     * @param shortName shortName
     * @param longName longName
     * @param gtfsStopId gtfsStopId
     * @param latt latt
     * @param longt longt
     * @return Station station
     */
    public Station createStation(Long stationId, String shortName, String longName, int gtfsStopId, Double latt, Double longt) {
        final Station station = new Station(stationId, shortName, longName, gtfsStopId, latt, longt, false);
        stationRepository.save(station);
        return station;
    }

    /**
     * create platform.
     * @param parentStopId parentStopId
     * @param gtfsStopId gtfsStopId
     * @param name name
     * @param longName longName
     * @param platformNo platformNo
     * @param platformName platformName
     * @return Platform platform
     */
    public Platform createPlatform(int parentStopId, int gtfsStopId, String name, String longName, int platformNo, String platformName) {
        final Station station = stationRepository.findBySchemaPropertyValue("gtfsStopId", parentStopId);

        final Platform platform = new Platform(name, longName, platformName, platformNo, gtfsStopId);

        if (station != null) {
            platform.setStation(station);
        }

        platformRepository.save(platform);
        return platform;
    }

    /**
     * createTriplet.
     * @param inStopId inStopId
     * @param stopId stopId
     * @param outStopId outStopId
     * @param reversible reversible
     * @param powerTypes powerTypes
     * @return StationTriplet station triplet
     */
    public StationTriplet createTriplet(int inStopId, int stopId, int outStopId, boolean reversible, String[] powerTypes) {
        final StationTriplet stationTriplet = new StationTriplet();
        stationTriplet.setReversible(reversible);

        final Station mainStation = stationRepository.findBySchemaPropertyValue("gtfsStopId", stopId);
        stationTriplet.setStation(mainStation);

        final Station fromStation = stationRepository.findBySchemaPropertyValue("gtfsStopId", inStopId);
        stationTriplet.setFromStation(fromStation);

        final Station toStation = stationRepository.findBySchemaPropertyValue("gtfsStopId", outStopId);
        stationTriplet.setToStation(toStation);

        for (String powertypeName : powerTypes) {
            final PowerType powerType = powerTypeRepository.findBySchemaPropertyValue("name", powertypeName);
            stationTriplet.addPowerType(powerType);
        }

        stationTripletRepository.save(stationTriplet);
        return stationTriplet;
    }
    //////////////////////////////////////////////////////   add nodal geograpby data //////////////////////////////

    /**
     * Create SpeedBand Record.
     * @param id id
     * @param name name
     * @param description description
     * @return SpeedBand speedBand
     */
    public SpeedBand createSpeedBand(int id, String name, String description) {
        final SpeedBand speedBand = new SpeedBand(id, name, description);
        speedBandRepository.save(speedBand);
        return speedBand;
    }

    /**
     * Constructor.
     * @param nodeLink nodeLink
     * @param speedBandId speedBandId
     * @param stopToStop stopToStop
     * @param passToPass passToPass
     * @return RuningTime runingTime
     */
    public RuningTime createRuningTime(NodeLink nodeLink, int speedBandId, String stopToStop, String passToPass) {
        final SpeedBand speedBand = speedBandRepository.findBySchemaPropertyValue("id", speedBandId);
        final RuningTime runingTime = new RuningTime(stopToStop, passToPass);
        runingTime.setSpeedBand(speedBand);
        runingTime.setNodeLink(nodeLink);
        runingTimeRepository.save(runingTime);
        return runingTime;
    }

    /**
     * Create Track Section Record.
     * @param id id
     * @param name name
     * @param isUp isUp
     * @param isPermissive isPermissive
     * @return TrackSection trackSection
     */
    public TrackSection createTrackSection(String id, String name, boolean isUp, boolean isPermissive) {
        final TrackSection trackSection = new TrackSection(id, name, isUp, isPermissive);
        trackSectionRepository.save(trackSection);
        return trackSection;
    }

    /**
     * Create NodeLink Record.
     * @param fromNodeName fromNodeName
     * @param toNodeName toNodeName
     * @param length length
     * @param isSliding isSliding
     * @param isCrossOver isCrossOver
     * @param isRuningLine isRuningLine
     * @param trackSectionNumber trackSectionNumber
     * @param powerTypes powerTypes
     * @param gauges gauges
     * @return NodeLink nodeLink
     */
    public NodeLink createNodeLink(String fromNodeName, String toNodeName, long length, boolean isSliding, boolean isCrossOver,
                                   boolean isRuningLine, int trackSectionNumber, String[] powerTypes, String[] gauges)
    {

        final Node fromNode = nodeRepository.findBySchemaPropertyValue("name", fromNodeName);
        final Node toNode = nodeRepository.findBySchemaPropertyValue("name", toNodeName);

        final TrackSection trackSection = trackSectionRepository.findBySchemaPropertyValue("id", trackSectionNumber);
        final NodeLink nodeLink = new NodeLink(length, isSliding, isCrossOver, isRuningLine);

        for (String powertypeName : powerTypes) {
            final PowerType powerType = powerTypeRepository.findBySchemaPropertyValue("name", powertypeName);
        }
        for (String gaugeName : gauges) {
            final Gauge gauge = gaugeRepository.findBySchemaPropertyValue("name", gaugeName);
        }

        nodeLink.setFromNode(fromNode);
        nodeLink.setToNode(toNode);
        //nodeLink.setTrackSection(trackSection);
        nodeLinkRepository.save(nodeLink);
        return nodeLink;
    }

    /**
     * Create TurnPenaltyBan Record.
     * @param fromNodeName fromNodeName
     * @param toNodeName toNodeName
     * @param penaltyBan penaltyBan
     * @return TurnPenaltyBan turnPenaltyBan
     */
    public TurnPenaltyBan createTurnPenaltyBan(String fromNodeName, String toNodeName, String penaltyBan) {
        final Node fromNode = nodeRepository.findBySchemaPropertyValue("name", fromNodeName);
        final Node toNode = nodeRepository.findBySchemaPropertyValue("name", toNodeName);
        if (fromNode == null || toNode == null) {
            return null;
        }
        final TurnPenaltyBan turnPenaltyBan = new TurnPenaltyBan(fromNode, toNode, penaltyBan);
        turnPenaltyBanRepository.save(turnPenaltyBan);
        return turnPenaltyBan;
    }

    /**
     * create Node Linkage.
     * @param fromNodeName fromNodeName
     * @param toNodeName toNodeName
     * @param length length
     * @param isBusEnergy isBusEnergy
     * @param isACEnergy isACEnergy
     * @param isDCEnergy isDCEnergy
     * @param isDieselEnergyk isDieselEnergyk
     * @param isBusGauge isBusGauge
     * @param isNarrowGauge isNarrowGauge
     * @param isStandardGauge isStandardGauge
     * @param isBroadGauge isBroadGauge
     * @param isSiding isSiding
     * @param isCrossOver isCrossOver
     * @param isRunningLine isRunningLine
     * @param trackSectionId trackSectionId
     * @return NodeLinkage
     */
    public NodeLinkage createNodeLinkage(String fromNodeName, String toNodeName, long length, boolean isBusEnergy,
                                         boolean isACEnergy, boolean isDCEnergy, boolean isDieselEnergyk, boolean isBusGauge,
                                         boolean isNarrowGauge, boolean isStandardGauge,
                                         boolean isBroadGauge, boolean isSiding, boolean isCrossOver, boolean isRunningLine, int trackSectionId)
    {
        final Node fromNode = nodeRepository.findBySchemaPropertyValue("name", fromNodeName);
        final Node toNode = nodeRepository.findBySchemaPropertyValue("name", toNodeName);
        if (fromNode == null || toNode == null) {
            return null;
        }
        final NodeLinkage nodeLinkage = new NodeLinkage();
        nodeLinkage.setFromNode(fromNode);
        nodeLinkage.setToNode(toNode);
        nodeLinkage.setLength(length);
        nodeLinkage.setBusEnergy(isBusEnergy);
        nodeLinkage.setAcEnergy(isACEnergy);
        nodeLinkage.setDcEnergy(isDCEnergy);
        nodeLinkage.setDieselEnergy(isDieselEnergyk);
        nodeLinkage.setBusGauge(isBusGauge);
        nodeLinkage.setNarrowGauge(isNarrowGauge);
        nodeLinkage.setStandardGauge(isStandardGauge);
        nodeLinkage.setBroadGauge(isBroadGauge);
        nodeLinkage.setSiding(isSiding);
        nodeLinkage.setCrossOver(isCrossOver);
        nodeLinkage.setRunningLine(isRunningLine);
        nodeLinkage.setTrackSectionId(trackSectionId);
        nodeLinkageRepository.save(nodeLinkage);
        return nodeLinkage;
    }

    /**
     * Create a Node.
     * @param name name
     * @param longName longName
     * @param platformName platformName
     * @param isDummy isDummy
     * @param isJunction isJunction
     * @param isWorkingTimingPoint isWorkingTimingPoint
     * @param isPublicTimingPoint isPublicTimingPoint
     * @param isEndOfLine isEndOfLine
     * @param dWellDuration dWellDuration
     * @param upRecoveryDuration upRecoveryDuration
     * @param downRecoveryDuration downRecoveryDuration
     * @param length length
     * @return Node node
     */
    public Node createNode(String name, String longName, String platformName, boolean isDummy, boolean isJunction,
                           boolean isWorkingTimingPoint, boolean isPublicTimingPoint, boolean isEndOfLine, String dWellDuration, String upRecoveryDuration, String downRecoveryDuration, double length)
    {
        Node node;
        node = nodeRepository.getNodePerName("", name);
        if (node == null) {
            node = new Node();
        }
        node.setName(name);
        node.setLongName(longName);
        node.setDummy(isDummy);
        node.setJunction(isJunction);
        node.setWorkingTimingPoint(isWorkingTimingPoint);
        node.setPublicTimingPoint(isPublicTimingPoint);
        node.setEndOfLine(isEndOfLine);
        node.setdWellDuration(dWellDuration);
        node.setUpRecoveryDuration(upRecoveryDuration);
        node.setDownRecoveryDuration(downRecoveryDuration);
        node.setLength(length);
        nodeRepository.save(node);
        return node;
    }

    /**
     * define master timing point for specific node.
     * @param nodeName nodeName
     * @param masterTimingPointNodeName masterTimingPointNodeName
     */
    public void defineNodeMasterTimingPoint(String nodeName, String masterTimingPointNodeName) {
        final Node fromNode = nodeRepository.findBySchemaPropertyValue("name", nodeName);
        final Node toNode = nodeRepository.findBySchemaPropertyValue("name", masterTimingPointNodeName);
        if (fromNode != null && toNode != null) {
            fromNode.setMasterTimingPoint(toNode);
            nodeRepository.save(fromNode);
        }
    }

    /**
     * define master junction for specific node.
     * @param nodeName nodeName
     * @param masterJunctionNodeName masterJunctionNodeName
     */
    public void defineNodeMasterJunction(String nodeName, String masterJunctionNodeName) {
        final Node fromNode = nodeRepository.findBySchemaPropertyValue("name", nodeName);
        final Node toNode = nodeRepository.findBySchemaPropertyValue("name", masterJunctionNodeName);
        if (fromNode != null && toNode != null) {
            fromNode.setMasterJunction(toNode);
            nodeRepository.save(fromNode);
        }
    }

    /**
     * create new gauge.
     * @param name name
     * @param desc desc
     * @return Gauge gauge
     */
    public Gauge createGauge(String name, String desc) {
        final Gauge gauge = new Gauge(name, desc);
        gaugeRepository.save(gauge);
        return gauge;
    }
}
