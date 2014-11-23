package au.gov.nsw.railcorp.rttarefdata.manager;

import au.gov.nsw.railcorp.rtta.refint.generated.geography.CgGeography;
import au.gov.nsw.railcorp.rttarefdata.domain.*;
import au.gov.nsw.railcorp.rttarefdata.repositories.*;
import au.gov.nsw.railcorp.rttarefdata.util.IConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

/**
 * Created by arash on 11/11/14.
 */
@Component
@Transactional
public class NodalGeographyManager implements INodalGeographyManager {
    @Autowired
    private NodeRepository nodeRepository;

    @Autowired
    private GaugeRepository gaugeRepository;

    @Autowired
    private TrackSectionRepository trackSectionRepository;

    @Autowired
    private NodeLinkRepository nodeLinkRepository;

    @Autowired
    private SpeedBandRepository speedBandRepository;

    @Autowired
    private RuningTimeRepository runingTimeRepository;

    @Autowired
    private TurnPenaltyBanRepository turnPenaltyBanRepository;

    @Autowired
    private IDataTypeManager dataTypeManager;
    @Autowired
    private NodeLinkageRepository nodeLinkageRepository;
    /**
     * Create SpeedBand Record.
     * @param id id
     * @param name name
     * @param description description
     * @return SpeedBand speedBand
     */
    public SpeedBand createSpeedBand(int id, String name, String description) {
        SpeedBand speedBand = speedBandRepository.findBySchemaPropertyValue("name", name);
        if (speedBand == null) {
            speedBand = new SpeedBand();
        }
        speedBand.setId(id);
        speedBand.setName(name);
        speedBand.setDescription(description);
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
    public TrackSection createTrackSection(int id, String name, boolean isUp, boolean isPermissive) {
        TrackSection trackSection = trackSectionRepository.findBySchemaPropertyValue("id", id);
        if (trackSection == null) {
            trackSection = new TrackSection();
        }
        trackSection.setId(id);
        trackSection.setName(name);
        trackSection.setUpDirection(isUp);
        trackSection.setPermissive(isPermissive);
        trackSectionRepository.save(trackSection);
        return trackSection;
    }

    /**
     * Create NodeLink Record: if nodeLink exists then delete it and recreate it.
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
                                   boolean isRuningLine, int trackSectionNumber, List<String> powerTypes, List<String> gauges)
    {

        final Node fromNode = nodeRepository.findBySchemaPropertyValue("name", fromNodeName);
        final Node toNode = nodeRepository.findBySchemaPropertyValue("name", toNodeName);

        if (fromNode == null || toNode == null) {
            return null;
        }

        final TrackSection trackSection = trackSectionRepository.findBySchemaPropertyValue("id", trackSectionNumber);

        NodeLink nodeLink = nodeLinkRepository.getNodeLink(fromNodeName, toNodeName);
        if (nodeLink != null) {
            //running times are part of node link. so we need to delete them with node link
            if (nodeLink.getRunningTimes() != null) {
                runingTimeRepository.delete(nodeLink.getRunningTimes());
            }
            nodeLinkRepository.delete(nodeLink);
        }
        nodeLink = new NodeLink();
        nodeLink.setLength(length);
        nodeLink.setSliding(isSliding);
        nodeLink.setCrossOver(isCrossOver);
        nodeLink.setRunningLine(isRuningLine);

        for (String powertypeName : powerTypes) {
            final PowerType powerType = dataTypeManager.addPowerType(powertypeName, powertypeName);
            if (powerType != null) {
                nodeLink.addPowerType(powerType);
            }
        }
        for (String gaugeName : gauges) {
            final Gauge gauge = dataTypeManager.addGauge(gaugeName, gaugeName);
            if (gauge != null) {
                nodeLink.addGauge(gauge);
            }
        }

        nodeLink.setFromNode(fromNode);
        nodeLink.setToNode(toNode);
        if (trackSection != null) {
            nodeLink.setTrackSection(trackSection);
        }
        nodeLinkRepository.save(nodeLink);
        return nodeLink;
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
        NodeLinkage nodeLinkage;
        if (trackSectionId < 0){
            nodeLinkage = nodeLinkageRepository.getNodeLinkageNonTrackSection(fromNodeName, toNodeName);
            if (nodeLinkage != null) {
                //no need to create a linkage between 2 nodes cause there is already a path
                return nodeLinkage;
            }
        }
        TrackSection trackSection = null;
        trackSection = trackSectionRepository.findBySchemaPropertyValue("id", trackSectionId);
        nodeLinkage = new NodeLinkage();
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
        if (trackSection != null) {
            nodeLinkage.setDirection(trackSection.isUpDirection()? IConstants.TRACK_UP_DIRECTION : IConstants.TRACK_DOWN_DIRECTION);
        } else {
            nodeLinkage.setDirection(IConstants.TRACK_NON_DIRECTION);
        }
        nodeLinkageRepository.save(nodeLinkage);
        return nodeLinkage;
    }
    /**
     * Create a Node. if node exists then modify it otherwise create it.
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
                           boolean isWorkingTimingPoint, boolean isPublicTimingPoint, boolean isEndOfLine, String dWellDuration,
                           String upRecoveryDuration, String downRecoveryDuration, double length)
    {
        Node node;
        node = nodeRepository.findBySchemaPropertyValue("name", name);
        /*
            if node is not null then we must remove all linkages and turning penalty bans
         */
        if (node != null) {
            if (node.getOutgoingTurnPenalties() != null) {
                turnPenaltyBanRepository.delete(node.getOutgoingTurnPenalties());
            }
            if (node.getNodeLinkages() != null){
                nodeLinkageRepository.delete(node.getNodeLinkages());
            }
        }
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
        //reset relations
        node.setMasterTimingPoint(null);
        node.setMasterJunction(null);
        //node.setIncomingTurnPenaltyBans(null);
        //node.setOutGoingNodeLinks(null);
        //
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
     * Create TurnPenaltyBan Record.
     * @param viaNodeName viaNodeName
     * @param fromNodeName fromNodeName
     * @param toNodeName toNodeName
     * @param penaltyBan penaltyBan
     * @return TurnPenaltyBan turnPenaltyBan
     */
    public TurnPenaltyBan createTurnPenaltyBan(String viaNodeName, String fromNodeName, String toNodeName, String penaltyBan) {
        final Node fromNode = nodeRepository.findBySchemaPropertyValue("name", fromNodeName);
        final Node toNode = nodeRepository.findBySchemaPropertyValue("name", toNodeName);
        if (fromNode == null || toNode == null) {
            return null;
        }

        TurnPenaltyBan turnPenaltyBan = turnPenaltyBanRepository.getNodeTurnPenaltyBan(fromNodeName, viaNodeName, toNodeName);
        if (turnPenaltyBan == null) {
            turnPenaltyBan = new TurnPenaltyBan();
        }
        turnPenaltyBan.setViaNodeName(viaNodeName);
        turnPenaltyBan.setFromNode(fromNode);
        turnPenaltyBan.setToNode(toNode);
        turnPenaltyBan.setPenalty(penaltyBan);
        turnPenaltyBanRepository.save(turnPenaltyBan);
        return turnPenaltyBan;
    }

}
