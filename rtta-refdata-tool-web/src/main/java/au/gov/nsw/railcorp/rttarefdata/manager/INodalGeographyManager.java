package au.gov.nsw.railcorp.rttarefdata.manager;

import au.gov.nsw.railcorp.rtta.refint.generated.geography.CgGeography;
import au.gov.nsw.railcorp.rttarefdata.domain.*;

import java.util.List;


/**
 * Created by arash on 11/11/14.
 */
public interface INodalGeographyManager {
    /**
     * Create SpeedBand Record.
     * @param id id
     * @param name name
     * @param description description
     * @return SpeedBand speedBand
     */
    SpeedBand createSpeedBand(int id, String name, String description);

    /**
     * Constructor.
     * @param nodeLink nodeLink
     * @param speedBandId speedBandId
     * @param stopToStop stopToStop
     * @param passToPass passToPass
     * @return RuningTime runingTime
     */
    RuningTime createRuningTime(NodeLink nodeLink, String speedBandId, String stopToStop, String passToPass);

    /**
     * Create Track Section Record.
     * @param id id
     * @param name name
     * @param isUp isUp
     * @param isPermissive isPermissive
     * @return TrackSection trackSection
     */
    TrackSection createTrackSection(String id, String name, boolean isUp, boolean isPermissive);

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
    NodeLink createNodeLink(String fromNodeName, String toNodeName, long length, boolean isSliding, boolean isCrossOver,
                                   boolean isRuningLine, String trackSectionNumber, boolean isBusEnergy, boolean isAcEnergy,
                                   boolean isDcEnergy, boolean isDieselEnergy, boolean isBusGauge, boolean isNarrowGauge, boolean  isStandardGauge, boolean isBroadGauge);
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
    Node createNode(String name, String longName, String platformName, boolean isDummy, boolean isJunction,
                           boolean isWorkingTimingPoint, boolean isPublicTimingPoint, boolean isEndOfLine, String dWellDuration,
                           String upRecoveryDuration, String downRecoveryDuration, double length, String masterTimingPoint, String masterJunction);
    /**
     * define master timing point for specific node.
     * @param nodeName nodeName
     * @param masterTimingPointNodeName masterTimingPointNodeName
     */
    void defineNodeMasterTimingPoint(String nodeName, String masterTimingPointNodeName);

    /**
     * define master junction for specific node.
     * @param nodeName nodeName
     * @param masterJunctionNodeName masterJunctionNodeName
     */
    void defineNodeMasterJunction(String nodeName, String masterJunctionNodeName);

    /**
     * Create TurnPenaltyBan Record.
     * @param viaNodeName viaNodeName
     * @param fromNodeName fromNodeName
     * @param toNodeName toNodeName
     * @param penaltyBan penaltyBan
     * @return TurnPenaltyBan turnPenaltyBan
     */
    TurnPenaltyBan createTurnPenaltyBan(String viaNodeName, String fromNodeName, String toNodeName, String penaltyBan);

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
    NodeLinkage createNodeLinkage(String fromNodeName, String toNodeName, long length, boolean isBusEnergy,
                                         boolean isACEnergy, boolean isDCEnergy, boolean isDieselEnergyk, boolean isBusGauge,
                                         boolean isNarrowGauge, boolean isStandardGauge,
                                         boolean isBroadGauge, boolean isSiding, boolean isCrossOver, boolean isRunningLine, int trackSectionId);


    /**
     * Remove all runningtime records.
     * @param version version
     */
    void emptyRunningTimes (String version);

    /**
     * Remove All NodeLinks.
     * @param version version
     */
    void emptyNodeLinks (String version);
    /**
     * Remove All TurnPenaltyBan.
     * @param version version
     */
    void emptyNodeTurnPenaltyBan (String version);
    /**
     * Remove All NodeLinkages.
     * @param version version
     */
    void emptyNodeNodeLinkages (String version);

    /**
     * Remove All SpeedBands.
     * @param version version
     */
    void emptySpeedBands (String version);
    /**
     * Remove All TrackSection.
     * @param version version
     */
    void emptyTrackSections (String version);

    /**
     * create NodalHeader.
     * @param description description
     * @param owner owner
     * @param date date
     * @return NodalHeader
     */
    NodalHeader createNodalHeader(String description, String owner, String date);

    /**
     * remove NodalHeader.
     * @param version version
     */
    void emptyNodalHeader (String version);

    /**
     * export NodalGeography.
     * @return CgGeography
     */
    CgGeography exportNodalGeography();

    /**
     * find All paths between 2 nodes.
     * @param fromNodeName fromNodeName
     * @param toNodeName toNodeName
     * @param toDepth toDepth
     * @return List of paths
     */
    List findAllPaths(String fromNodeName, String toNodeName, int toDepth);

    /**
     * find all path between 2 nodes by algorithemns.
     * @param startNodeName startNodeName
     * @param endNodeName endNodeName
     * @return List
     */
    List findAllPath2(String startNodeName, String endNodeName);
    /**
     * find all shortest path between 2 nodes.
     * @param fromNodeName fromNodeName
     * @param toNodeName toNodeName
     * @return List of valid path
     */
    List findAllShortestPaths(String fromNodeName, final String toNodeName);

    /**
     * Remove All NodeLinks.
     * @param version version
     */
    void emptyNodeLinkages (String version);

}
