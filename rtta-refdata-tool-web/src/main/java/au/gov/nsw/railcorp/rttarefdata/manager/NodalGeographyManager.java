package au.gov.nsw.railcorp.rttarefdata.manager;

import au.gov.nsw.railcorp.rtta.refint.generated.geography.CgGeography;
import au.gov.nsw.railcorp.rtta.refint.generated.geography.CgGeography.Geov10RC;
import au.gov.nsw.railcorp.rtta.refint.generated.geography.CgGeography.Geov10RC.SpeedBands;
import au.gov.nsw.railcorp.rtta.refint.generated.geography.CgGeography.Geov10RC.Nodes;
import au.gov.nsw.railcorp.rtta.refint.generated.geography.CgGeography.Geov10RC.Links;
import au.gov.nsw.railcorp.rtta.refint.generated.geography.CgGeography.Geov10RC.Links.Link.RunningTimes;
import au.gov.nsw.railcorp.rtta.refint.generated.geography.CgGeography.Geov10RC.Links.Link.RunningTimes.RunningTime;
import au.gov.nsw.railcorp.rtta.refint.generated.geography.CgGeography.Geov10RC.Links.Link;
import au.gov.nsw.railcorp.rtta.refint.generated.geography.CgGeography.Geov10RC.TrackSections;
import au.gov.nsw.railcorp.rtta.refint.generated.geography.CgGeography.Geov10RC.Nodes.Node.NodeTurnPenaltyBans.NodeTurnPenaltyBan;
import au.gov.nsw.railcorp.rtta.refint.generated.geography.CgGeography.Geov10RC.Nodes.Node.NodeTurnPenaltyBans;
import au.gov.nsw.railcorp.rtta.refint.generated.geography.CgGeography.Geov10RC.Nodes.Node.NodeMasterJunction;
import au.gov.nsw.railcorp.rtta.refint.generated.geography.CgGeography.Geov10RC.Nodes.Node.NodeMasterTimingPoint;

import au.gov.nsw.railcorp.rttarefdata.domain.*;
import au.gov.nsw.railcorp.rttarefdata.domain.Node;
import au.gov.nsw.railcorp.rttarefdata.expander.TurnPenaltyBanExpander;
import au.gov.nsw.railcorp.rttarefdata.mapresult.INodeData;
import au.gov.nsw.railcorp.rttarefdata.mapresult.INodeLinkRunTimeData;
import au.gov.nsw.railcorp.rttarefdata.repositories.*;
import au.gov.nsw.railcorp.rttarefdata.request.NodeModel;
import au.gov.nsw.railcorp.rttarefdata.request.TraverseModel;
import au.gov.nsw.railcorp.rttarefdata.util.IConstants;
import au.gov.nsw.railcorp.rttarefdata.util.StringUtil;
import org.neo4j.graphalgo.GraphAlgoFactory;
import org.neo4j.graphalgo.PathFinder;
import org.neo4j.graphdb.*;
import org.neo4j.graphdb.traversal.*;
import org.neo4j.graphdb.traversal.Traverser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
//import org.springframework.transaction.annotation.Transactional;

import javax.xml.datatype.DatatypeFactory;
import java.util.*;
import javax.xml.datatype.Duration;

/**
 * Created by arash on 11/11/14.
 */
@Component
//@Transactional
public class NodalGeographyManager implements INodalGeographyManager {

    private final Logger logger = LoggerFactory.getLogger(NodalGeographyManager.class);

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
    @Autowired
    private NodalHeaderRepository nodalHeaderRepository;
    @Autowired
    private GraphDatabaseService graphDatabaseService;
    private boolean metEndNode;
    private boolean timedOut;

    /**
     * Create SpeedBand Record.
     * @param id id
     * @param name name
     * @param description description
     * @return SpeedBand speedBand
     */
    public SpeedBand createSpeedBand(int id, String name, String description) {
        final SpeedBand speedBand = new SpeedBand();
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
    public RuningTime createRuningTime(NodeLink nodeLink, String speedBandId, String stopToStop, String passToPass) {
        final RuningTime runingTime = new RuningTime(stopToStop, passToPass);
        runingTime.setSbId(speedBandId);
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
        final TrackSection trackSection = new TrackSection();
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
     * @param isBusEnergy isBusEnergy
     * @param isAcEnergy isAcEnergy
     * @param isDcEnergy isDcEnergy
     * @param isDieselEnergy isDieselEnergy
     * @param isBusGauge isBusGauge
     * @param isNarrowGauge isNarrowGauge
     * @param isStandardGauge isStandardGauge
     * @param isBroadGauge isBroadGauge
     * @return NodeLink nodeLink
     */
    public NodeLink createNodeLink(String fromNodeName, String toNodeName, long length, boolean isSliding, boolean isCrossOver,
                                   boolean isRuningLine, String trackSectionNumber, boolean isBusEnergy, boolean isAcEnergy,
                                   boolean isDcEnergy, boolean isDieselEnergy, boolean isBusGauge, boolean isNarrowGauge, boolean  isStandardGauge, boolean isBroadGauge)
    {


        final NodeLink nodeLink = new NodeLink();
        nodeLink.setFromNodeName(fromNodeName);
        nodeLink.setToNodeName(toNodeName);
        nodeLink.setLength(length);
        nodeLink.setSliding(isSliding);
        nodeLink.setCrossOver(isCrossOver);
        nodeLink.setRunningLine(isRuningLine);
        nodeLink.setTrackSectionId(trackSectionNumber);
        nodeLink.setBusEnergy(isBusEnergy);
        nodeLink.setAcEnergy(isAcEnergy);
        nodeLink.setDcEnergy(isDcEnergy);
        nodeLink.setDieselEnergy(isDieselEnergy);
        nodeLink.setBusGauge(isBusGauge);
        nodeLink.setNarrowGauge(isNarrowGauge);
        nodeLink.setStandardGauge(isStandardGauge);
        nodeLink.setBroadGauge(isBroadGauge);
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
        if (trackSectionId < 0) {
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
            nodeLinkage.setDirection(trackSection.isUpDirection() ? IConstants.TRACK_UP_DIRECTION : IConstants.TRACK_DOWN_DIRECTION);
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
     * @param masterTimingPoint masterTimingPoint
     * @param masterJunction masterJunction
     * @return Node node
     */
    public Node createNode(String name, String longName, String platformName, boolean isDummy, boolean isJunction,
                           boolean isWorkingTimingPoint, boolean isPublicTimingPoint, boolean isEndOfLine, String dWellDuration,
                           String upRecoveryDuration, String downRecoveryDuration, double length, String masterTimingPoint, String masterJunction)
    {
        Node node;
        node = nodeRepository.findBySchemaPropertyValue("name", name);
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
        node.setMasterTimingPointName(masterTimingPoint);
        node.setMasterJunctionName(masterJunction);
        node.setRailNetNode(true);
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
        turnPenaltyBan.setFromNodeName(fromNodeName);
        turnPenaltyBan.setToNodeName(toNodeName);
        turnPenaltyBanRepository.save(turnPenaltyBan);
        return turnPenaltyBan;
    }

    /**
     * export NodalGeography.
     * @return CgGeography
     */
    public CgGeography exportNodalGeography() {
        logger.info("NodalGeographyManager: export begin.");
        final CgGeography cgGeography = new CgGeography();
        final Geov10RC geov10RC = exportNodalHeader();
        geov10RC.setNodes(exportNodes());
        logger.info("NodalGeographyManager: nodes exported");
        geov10RC.setSpeedBands(exportSpeedBands());
        logger.info("NodalGeographyManager: speed bands exported");
        geov10RC.setTrackSections(exportTrackSections());
        logger.info("NodalGeographyManager: track section exported");
        geov10RC.setLinks(exportLinks());
        logger.info("NodalGeographyManager: links exported");
        cgGeography.getGeov10RC().add(geov10RC);
        logger.info("NodalGeographyManager return");
        return cgGeography;
    }
    /**
     * Build Node list for export.
     * @return Nodes
     */
    @Transactional
    public Nodes exportNodes() {
        final Nodes exportNodeList = new CgGeography.Geov10RC.Nodes();
        try {
            final List<INodeData> nodeDataList = nodeRepository.getAllRailNetNodes();
            List<TurnPenaltyBan> turnPenaltyBanList = null;
            Nodes.Node node;
            NodeTurnPenaltyBan nodeTurnPenaltyBan = null;
            NodeMasterJunction nodeMasterJunction = null;
            NodeMasterTimingPoint nodeMasterTimingPoint = null;
            Duration duration;
            NodeTurnPenaltyBans nodeTurnPenaltyBans = null;
            for (INodeData nodeData : nodeDataList) {
                node = new Nodes.Node();
                node.setName(nodeData.getName());
                node.setLongName(nodeData.getLongName());
                node.setPlatformName(nodeData.getPlatformName());
                node.setIsDummy(nodeData.isDummy());
                node.setIsJunction(nodeData.isJunction());
                node.setIsWorkingTimingPoint(nodeData.isWorkingTimingPoint());
                node.setIsPublicTimingPoint(nodeData.isPublicTimingPoint());
                node.setIsEndOfLine(nodeData.isEndOfLine());
                try {
                    duration = DatatypeFactory.newInstance().newDuration(nodeData.getWellDuration());
                } catch (Exception e) {
                    duration = null;
                }
                node.setDwellDuration(duration);

                try {
                    duration = DatatypeFactory.newInstance().newDuration(nodeData.getUpRecoveryDuration());
                } catch (Exception e) {
                    duration = null;
                }
                node.setUpRecoveryDuration(duration);

                try {
                    duration = DatatypeFactory.newInstance().newDuration(nodeData.getDownRecoveryDuration());
                } catch (Exception e) {
                    duration = null;
                }
                node.setDownRecoveryDuration(duration);
                node.setLength(nodeData.getLength());

                if (!nodeData.getMasterJunctionName().isEmpty()) {
                    nodeMasterJunction = new NodeMasterJunction();
                    nodeMasterJunction.setNodeName(nodeData.getMasterJunctionName());
                }

                if (!nodeData.getMasterTimingPointName().isEmpty()) {
                    nodeMasterTimingPoint = new NodeMasterTimingPoint();
                    nodeMasterTimingPoint.setNodeName(nodeData.getMasterTimingPointName());
                }
                node.getNodeMasterTimingPoint().add(nodeMasterTimingPoint);
                node.getNodeMasterJunction().add(nodeMasterJunction);
                //fetch NodeTurnPenaltyBan
                turnPenaltyBanList = turnPenaltyBanRepository.getAllTurnPenaltyBansPerNode(node.getName());
                nodeTurnPenaltyBans = new NodeTurnPenaltyBans();
                for (TurnPenaltyBan turnPenaltyBan : turnPenaltyBanList) {
                    nodeTurnPenaltyBan = new NodeTurnPenaltyBan();
                    nodeTurnPenaltyBan.setFromNodeName(turnPenaltyBan.getFromNodeName());
                    nodeTurnPenaltyBan.setToNodeName(turnPenaltyBan.getToNodeName());
                    try {
                        duration = DatatypeFactory.newInstance().newDuration(turnPenaltyBan.getPenalty());
                    } catch (Exception e) {
                        duration = null;
                    }
                    nodeTurnPenaltyBan.setPenalty(duration);
                    nodeTurnPenaltyBans.getNodeTurnPenaltyBan().add(nodeTurnPenaltyBan);
                }
                node.setNodeTurnPenaltyBans(nodeTurnPenaltyBans);
                exportNodeList.getNode().add(node);
            }
            return exportNodeList;
        } catch (Exception e) {
            logger.error("Error in building NodalGegraphy's nodes section :", e);
            return exportNodeList;
        }
    }

    /**
     * build speedBands for export.
     * @return SpeedBands
     */
    @Transactional
    public SpeedBands exportSpeedBands() {
        final SpeedBands speedBands = new SpeedBands();
        try {
            SpeedBands.SpeedBand speedBand = new SpeedBands.SpeedBand();
            final List<SpeedBand> speedBandList = speedBandRepository.getAllSpeedBands();
            for (SpeedBand speedBand1 : speedBandList) {
                speedBand = new SpeedBands.SpeedBand();
                speedBand.setId(StringUtil.intToStr(speedBand1.getId()));
                speedBand.setName(speedBand1.getName());
                speedBands.getSpeedBand().add(speedBand);
            }
            return speedBands;
        } catch (Exception e) {
            logger.error("Exception in exporting NodalGeography speed bands: ", e);
            return speedBands;
        }
    }

    /**
     * return all TrackSections.
     * @return TrackSections
     */
    @Transactional
    public TrackSections exportTrackSections() {
        final TrackSections trackSections = new TrackSections();
        try {
            TrackSections.TrackSection trackSection = null;
            final List<TrackSection> trackSectionList = trackSectionRepository.getAllTrackSections();
            for (TrackSection trackSection1 : trackSectionList) {
                trackSection = new TrackSections.TrackSection();
                trackSection.setName(trackSection1.getName());
                trackSection.setId(trackSection1.getId());
                trackSection.setIsPermissive(trackSection1.isPermissive());
                trackSection.setIsUp(trackSection1.isUpDirection());
                trackSections.getTrackSection().add(trackSection);
            }
            return trackSections;
        } catch (Exception e) {
            logger.error("Exception in exporting NodalGegoraphy TrackSections: ", e);
            return trackSections;
        }
    }


    /**
     * export Links.
     * @return Links
     */
    @Transactional
    public Links exportLinks() {
        Duration duration;
        final Links links = new Links();
        Link link = null;
        RunningTimes runningTimes = null;
        RunningTime runningTime = null;
        try {
            final List<INodeLinkRunTimeData> nodeLinkRunTimeDataList = nodeLinkRepository.getAllNodeLinksAndRunningTimes();
            logger.info("NodalGeographyManager: after returning NodeLinkRunTimeData");
            if (nodeLinkRunTimeDataList == null) {
                return links;
            }

            long headNodeLinkId;
            INodeLinkRunTimeData nodeLinkRunTimeData = null;
            final ListIterator iterator = nodeLinkRunTimeDataList.listIterator();
            boolean goForward = false;
            while (iterator.hasNext()) {
                goForward = false;
                nodeLinkRunTimeData = (INodeLinkRunTimeData) iterator.next();
                logger.info("NodalGeographyManager: processing nodelink: " + nodeLinkRunTimeData.getFromNodeName() + " --> " + nodeLinkRunTimeData.getToNodeName());
                headNodeLinkId = nodeLinkRunTimeData.getNodeLinkId();

                link = new Link();
                link.setFromNodeName(nodeLinkRunTimeData.getFromNodeName());
                link.setToNodeName(nodeLinkRunTimeData.getToNodeName());
                link.setLength(nodeLinkRunTimeData.getLength());
                link.setIsBusEnergy(nodeLinkRunTimeData.getBusEnergy());
                link.setIsACEnergy(nodeLinkRunTimeData.getAcEnergy());
                link.setIsDCEnergy(nodeLinkRunTimeData.getDcEnergy());
                link.setIsDieselEnergy(nodeLinkRunTimeData.getDieselEnergy());
                link.setIsBusGauge(nodeLinkRunTimeData.getBusGauge());
                link.setIsNarrowGauge(nodeLinkRunTimeData.getNarrowGauge());
                link.setIsStandardGauge(nodeLinkRunTimeData.getStandardGauge());
                link.setIsBroadGauge(nodeLinkRunTimeData.getBroadGauge());
                link.setIsSiding(nodeLinkRunTimeData.getSliding());
                link.setIsCrossOver(nodeLinkRunTimeData.getCrossOver());
                link.setIsRunningLine(nodeLinkRunTimeData.getRunningLine());
                link.setTrackSectionId(nodeLinkRunTimeData.getTrackSectionId());

                //runingTimeList = nodeLinkRepository.getNodeLinkRunningTimes(nodeLinkData.getNodeLinkId());
                runningTimes = new RunningTimes();
                while (nodeLinkRunTimeData.getNodeLinkId().longValue() == headNodeLinkId && iterator.hasNext()) {
                    runningTime = new RunningTime();
                    runningTime.setSBId(nodeLinkRunTimeData.getSbId());
                    try {
                        duration = DatatypeFactory.newInstance().newDuration(nodeLinkRunTimeData.getPassToPass());
                    } catch (Exception e) {
                        duration = null;
                    }
                    runningTime.setPP(duration);
                    try {
                        duration = DatatypeFactory.newInstance().newDuration(nodeLinkRunTimeData.getStopToStop());
                    } catch (Exception e) {
                        duration = null;
                    }
                    runningTime.setSS(duration);
                    runningTimes.getRunningTime().add(runningTime);
                    nodeLinkRunTimeData = (INodeLinkRunTimeData) iterator.next();
                    goForward = true;
                }
                link.setRunningTimes(runningTimes);
                links.getLink().add(link);
                if (goForward) {
                    iterator.previous();
                }
            }
            return links;
        } catch (Exception e) {
            logger.error("Exception in exporting NodalGeography nodeLinks : ", e);
            return links;
        }
    }

    /**
     * build Geov10RC for export.
      * @return Geov10RC
     */
    @Transactional
    public Geov10RC exportNodalHeader() {
        final Geov10RC geov10RC = new Geov10RC();
        try {
            final Iterator iterator = nodalHeaderRepository.findAll().iterator();
            NodalHeader nodalHeader;
            if (iterator != null && iterator.hasNext()) {
                nodalHeader = (NodalHeader) iterator.next();
                geov10RC.setDescription(nodalHeader.getDescription());
                geov10RC.setOwner(nodalHeader.getOwner());
                geov10RC.setDate(StringUtil.stringToXmlGregorianCalendar(nodalHeader.getDate()));
            }
            return geov10RC;
        } catch (Exception e) {
            logger.error("Exception in exporting NodalHeader :", e);
            return geov10RC;
        }
    }
    /**
     * Remove all runningtime records.
     */
    public void emptyRunningTimes () {
        runingTimeRepository.deleteAll();
    }

    /**
     * Remove All NodeLinks.
     */
    public void emptyNodeLinks () {
        nodeLinkRepository.deleteAll();
    }

    /**
     * Remove All TurnPenaltyBan.
     */
    public void emptyNodeTurnPenaltyBan () {
        turnPenaltyBanRepository.deleteAll();
    }
    /**
     * Remove All NodeLinkages.
     */
    public void emptyNodeNodeLinkages () {
        nodeLinkageRepository.deleteAll();
    }
    /**
     * Remove All SpeedBands.
     */
    public void emptySpeedBands () {
        speedBandRepository.deleteAll();
    }
    /**
     * Remove All TrackSection.
     */
    public void emptyTrackSections () {
        trackSectionRepository.deleteAll();
    }

    /**
     * create NodalHeader.
     * @param description description
     * @param owner owner
     * @param date date
     * @return NodalHeader
     */
    public NodalHeader createNodalHeader(String description, String owner, String date) {
        final NodalHeader nodalHeader = new NodalHeader();
        nodalHeader.setDescription(description);
        nodalHeader.setOwner(owner);
        nodalHeader.setDate(date);
        return nodalHeaderRepository.save(nodalHeader);

    }

    /**
     * remove NodalHeader.
     */
    public void emptyNodalHeader () {
        nodalHeaderRepository.deleteAll();
    }

    /**
     * find All paths between 2 nodes.
     * @param fromNodeName fromNodeName
     * @param toNodeName toNodeName
     * @return List of paths.
     */

    @Transactional
    public List findAllPaths(String fromNodeName, final String toNodeName) {
        //boolean endNodeMet = false;
        setMetEndNode(false);
        setTimedOut(false);
        List result = null;
        org.neo4j.graphdb.Node fromNode;
        final org.neo4j.graphdb.Node toNode;
        final long startTime = System.currentTimeMillis();
        fromNode = graphDatabaseService.getNodeById(nodeRepository.findBySchemaPropertyValue("name", fromNodeName).getNodeId());
        toNode = graphDatabaseService.getNodeById(
                    nodeRepository.findBySchemaPropertyValue("name", toNodeName).getNodeId());
        Evaluator penaltyBanEvaluator = new Evaluator() {
            @Override
            public Evaluation evaluate(Path path) {
                logPath(path);
                if (path.length() < 2) {
                    return Evaluation.INCLUDE_AND_CONTINUE;
                }
                org.neo4j.graphdb.Node node;
                final Iterator iterator = path.reverseNodes().iterator();
                node = (org.neo4j.graphdb.Node) iterator.next();
                final String toNodeName = (String) node.getProperty("name");
                node = (org.neo4j.graphdb.Node) iterator.next();
                final String viaNodeName = (String) node.getProperty("name");
                node = (org.neo4j.graphdb.Node) iterator.next();
                final String fromNodeName = (String) node.getProperty("name");
                logger.info("checking :" + fromNodeName + "-->" + viaNodeName + "-->" + toNodeName);

                final TurnPenaltyBan turnPenaltyBan = turnPenaltyBanRepository.getNodeTurnPenaltyBan(fromNodeName, viaNodeName, toNodeName);

                if (turnPenaltyBan != null && "PT99999S".equals(turnPenaltyBan.getPenalty())) {
                    logger.info("       PATH excluded");
                    return Evaluation.EXCLUDE_AND_PRUNE;
                }
                return Evaluation.INCLUDE_AND_CONTINUE;
            }
        };
        final Evaluator linkDirectionUpEvaluator = new Evaluator() {
            @Override
            public Evaluation evaluate(Path path) {
                try {
                    if (path.length() < 1) {
                        return Evaluation.INCLUDE_AND_CONTINUE;
                    }
                    final String direction = (String) path.lastRelationship().getProperty("direction");
                    if (direction.equals(IConstants.TRACK_DOWN_DIRECTION)) {
                        return Evaluation.EXCLUDE_AND_PRUNE;
                    }
                    return Evaluation.INCLUDE_AND_CONTINUE;
                } catch (Exception e) {
                    logger.error("Exception in evaluator ", e);
                    return Evaluation.EXCLUDE_AND_PRUNE;
                }
            }
        };
        final Evaluator linkDirectionDownEvaluator = new Evaluator() {
            @Override
            public Evaluation evaluate(Path path) {
                try {
                    if (path.length() < 1) {
                        return Evaluation.INCLUDE_AND_CONTINUE;
                    }
                    final String direction = (String) path.lastRelationship().getProperty("direction");
                    if (direction.equals(IConstants.TRACK_UP_DIRECTION)) {
                        return Evaluation.EXCLUDE_AND_PRUNE;
                    }
                    return Evaluation.INCLUDE_AND_CONTINUE;
                } catch (Exception e) {
                    logger.error("Exception in evaluator ", e);
                    return Evaluation.EXCLUDE_AND_PRUNE;
                }
            }
        };
        final Evaluator isReachEndNode = new Evaluator() {
            @Override
            public Evaluation evaluate(Path path) {
                if (isMetEndNode()) {
                    return Evaluation.EXCLUDE_AND_PRUNE;
                }
                final boolean isEndNode = path.endNode().getProperty("name").equals(toNodeName);
                if (isEndNode) {
                    setMetEndNode(true);
                    return Evaluation.INCLUDE_AND_PRUNE;
                }

                return Evaluation.INCLUDE_AND_CONTINUE;
            }
        };
        final Evaluator isTimedOut = new Evaluator() {
            @Override
            public Evaluation evaluate(Path path) {
                if (isTimedOut()) {
                    return Evaluation.EXCLUDE_AND_PRUNE;
                }
                final long stopTime = System.currentTimeMillis();
                final long elapsedTime = stopTime - startTime;
                if (elapsedTime > 300000) {
                    setTimedOut(true);
                    return Evaluation.EXCLUDE_AND_PRUNE;
                }
                return Evaluation.INCLUDE_AND_CONTINUE;
            }
        };
        final RelationshipType relationshipType = DynamicRelationshipType.withName("NODE_LINKAGE");
        /*
        final TraversalDescription traversalDescription = graphDatabaseService.traversalDescription().relationships(relationshipType, Direction.OUTGOING).evaluator(linkDirectionDownEvaluator).
                evaluator(penaltyBanEvaluator).evaluator(Evaluators.returnWhereEndNodeIs(toNode));
        */
        final TraversalDescription traversalDescription = graphDatabaseService.traversalDescription().uniqueness(Uniqueness.NODE_PATH).breadthFirst()
                .relationships(relationshipType, Direction.OUTGOING).evaluator(isReachEndNode).evaluator(isTimedOut)
                .evaluator(penaltyBanEvaluator).evaluator(Evaluators.returnWhereEndNodeIs(toNode));
        //.evaluator(linkDirectionUpEvaluator).
        final Traverser traverser = traversalDescription.traverse(fromNode);

        final Iterator pathIterator = traverser.iterator();
        if (pathIterator == null) {
            return null;
        }
        Path path;
        org.neo4j.graphdb.Node foundNode;
        Iterator nodeIterator;
        result = new ArrayList();
        TraverseModel traverseModel;
        NodeModel nodeModel;
        int i = 1;
        while (pathIterator.hasNext()) {
            path = (Path) pathIterator.next();
            nodeIterator = path.nodes().iterator();
            traverseModel = new TraverseModel();
            traverseModel.setPathName("PATH" + String.valueOf(i));
            i++;
            while (nodeIterator.hasNext()) {
                nodeModel = new NodeModel();
                foundNode = (org.neo4j.graphdb.Node) nodeIterator.next();
                nodeModel.setName((String) foundNode.getProperty("name"));
                nodeModel.setNodeId(foundNode.getId());
                try {
                    nodeModel.setLatitude((Double) foundNode.getProperty("latitude"));
                    nodeModel.setLongtitude((Double) foundNode.getProperty("longtitude"));
                } catch (NotFoundException nfe) {
                    nodeModel.setLatitude(0.00);
                    nodeModel.setLongtitude(0.00);
                }
                nodeModel.setLongName((String) foundNode.getProperty("longName"));
                traverseModel.addNode(nodeModel);
            }
            result.add(traverseModel);
        }
        return result;
    }

    /**
     * log path.
     * @param path path
     */
    public void logPath (Path path) {

        if (path == null) {
            return;
        }
        final Iterator nodeIterator = path.nodes().iterator();
        final StringBuffer pathStr = new StringBuffer("");

        while (nodeIterator.hasNext()) {
            final org.neo4j.graphdb.Node foundNode = (org.neo4j.graphdb.Node) nodeIterator.next();
            pathStr.append((String) foundNode.getProperty("name"));
            pathStr.append(" ");
        }
        logger.info("-------------------------------------------------------------------------------------------------------------");
        logger.info("Path :" + pathStr.toString());
    }

    /**
     * find all path between 2 nodes by algorithemns.
     * @param startNodeName startNodeName
     * @param endNodeName endNodeName
     * @return List
     */
    public List findAllPath2(String startNodeName, String endNodeName) {

        List result = null;
        org.neo4j.graphdb.Node fromNode;
        final org.neo4j.graphdb.Node toNode;

        fromNode = graphDatabaseService.getNodeById(nodeRepository.findBySchemaPropertyValue("name", startNodeName).getNodeId());
        toNode = graphDatabaseService.getNodeById(
                nodeRepository.findBySchemaPropertyValue("name", endNodeName).getNodeId());

        final RelationshipType relationshipType = DynamicRelationshipType.withName("NODE_LINKAGE");

        /*
        final TraversalDescription traversalDescription = graphDatabaseService.traversalDescription().uniqueness(Uniqueness.NODE_PATH).relationships(relationshipType, Direction.OUTGOING)
                .evaluator(Evaluators.returnWhereEndNodeIs(toNode));
        */

        final TurnPenaltyBanExpander expander = new TurnPenaltyBanExpander(relationshipType, Direction.OUTGOING, turnPenaltyBanRepository);
        final PathFinder<Path> finder = GraphAlgoFactory.shortestPath(expander, 100);
        final Iterable<Path> pathIterator = finder.findAllPaths(fromNode, toNode);
        Path path;
        org.neo4j.graphdb.Node foundNode;
        Iterator nodeIterator;
        result = new ArrayList();
        TraverseModel traverseModel;
        NodeModel nodeModel;
        int i = 1;
        if (pathIterator == null) {
            logger.info("PathIterator is null");
            return  null;
        }
        while (pathIterator.iterator().hasNext()) {
            path = (Path) pathIterator.iterator().next();
            nodeIterator = path.nodes().iterator();
            traverseModel = new TraverseModel();
            traverseModel.setPathName("PATH" + String.valueOf(i));
            i++;
            while (nodeIterator.hasNext()) {
                nodeModel = new NodeModel();
                foundNode = (org.neo4j.graphdb.Node) nodeIterator.next();
                nodeModel.setName((String) foundNode.getProperty("name"));
                nodeModel.setNodeId(foundNode.getId());
                try {
                    nodeModel.setLatitude((Double) foundNode.getProperty("latitude"));
                    nodeModel.setLongtitude((Double) foundNode.getProperty("longtitude"));
                } catch (NotFoundException nfe) {
                    nodeModel.setLatitude(0.00);
                    nodeModel.setLongtitude(0.00);
                }
                nodeModel.setLongName((String) foundNode.getProperty("longName"));
                traverseModel.addNode(nodeModel);
            }
            result.add(traverseModel);
        }
        return result;
    }

    public boolean isMetEndNode() {
        return metEndNode;
    }

    public void setMetEndNode(boolean metEndNode) {
        this.metEndNode = metEndNode;
    }

    public boolean isTimedOut() {
        return timedOut;
    }

    public void setTimedOut(boolean timedOut) {
        this.timedOut = timedOut;
    }
}
