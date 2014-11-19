package au.gov.nsw.railcorp.rttarefdata.service;

import au.gov.nsw.railcorp.rtta.refint.generated.geography.CgGeography;
import au.gov.nsw.railcorp.rtta.refint.generated.geography.CgGeography.Geov10RC.Nodes;
import au.gov.nsw.railcorp.rtta.refint.generated.geography.CgGeography.Geov10RC.Links;
import au.gov.nsw.railcorp.rtta.refint.generated.geography.CgGeography.Geov10RC.Links.Link;
import au.gov.nsw.railcorp.rtta.refint.generated.geography.CgGeography.Geov10RC.Links.Link.RunningTimes.RunningTime;
import au.gov.nsw.railcorp.rtta.refint.generated.geography.CgGeography.Geov10RC.SpeedBands;
import au.gov.nsw.railcorp.rtta.refint.generated.geography.CgGeography.Geov10RC.TrackSections;
import au.gov.nsw.railcorp.rtta.refint.generated.geography.CgGeography.Geov10RC.TrackSections.TrackSection;
import au.gov.nsw.railcorp.rtta.refint.generated.geography.CgGeography.Geov10RC.Nodes.Node;
import au.gov.nsw.railcorp.rtta.refint.generated.geography.CgGeography.Geov10RC.Nodes.Node.NodeTurnPenaltyBans.NodeTurnPenaltyBan;
import au.gov.nsw.railcorp.rttarefdata.domain.NodeLink;
import au.gov.nsw.railcorp.rttarefdata.manager.INodalGeographyManager;
import au.gov.nsw.railcorp.rttarefdata.util.IConstants;
import au.gov.nsw.railcorp.rttarefdata.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arash on 11/11/14.
 */
@Component
public class NodalGeographyService {
    @Autowired
    private INodalGeographyManager nodalGeographyManager;

    /**
     * import RailNet Geography.
     * @param cgGeography cgGeography
     */
    public void importRailNetGeography(CgGeography cgGeography) {
        if (cgGeography == null) {
            return;
        }

        for (CgGeography.Geov10RC geov10RC: cgGeography.getGeov10RC()) {
            if (geov10RC == null) {
                continue;
            }
            //importSpeedBands(geov10RC.getSpeedBands());
            importTrackSections(geov10RC.getTrackSections());
            importNodes(geov10RC.getNodes());
            //importLinks(geov10RC.getLinks());
            importNodeLinkages(geov10RC.getLinks());
            importNodeSelfRelations(geov10RC.getNodes());

        }

    }

    /**
     * import nodes.
     * @param nodes list of nodes
     */
    public void importNodes(Nodes nodes) {

        if (nodes == null || nodes.getNode() == null) {
            return;
        }

        for (Node node: nodes.getNode()) {
            if (node == null) {
                continue;
            }
            nodalGeographyManager.createNode(node.getName(), node.getLongName(), node.getPlatformName(), node.isIsDummy(),
                    node.isIsJunction(), node.isIsWorkingTimingPoint(), node.isIsPublicTimingPoint(), node.isIsEndOfLine(),
                    node.getDwellDuration().toString(), node.getUpRecoveryDuration().toString(), node.getDownRecoveryDuration().toString(), node.getLength());

        }

    }

    /**
     * create MasterTimingPoint, MaterJunction and TurnPenaltyBan relations between nodes.
     * @param nodes nodes
     */
    public void importNodeSelfRelations(Nodes nodes) {
        if (nodes == null || nodes.getNode() == null) {
            return;
        }

        for (Node node: nodes.getNode()) {
            if (node == null) {
                continue;
            }
            for (Node.NodeMasterTimingPoint masterTimingPoint: node.getNodeMasterTimingPoint()) {
                if (masterTimingPoint == null) {
                    continue;
                }
                nodalGeographyManager.defineNodeMasterTimingPoint(node.getName(), masterTimingPoint.getNodeName());
            }
            for (Node.NodeMasterJunction masterJunction: node.getNodeMasterJunction()) {
                if (masterJunction == null) {
                    continue;
                }
                nodalGeographyManager.defineNodeMasterJunction(node.getName(), masterJunction.getNodeName());

            }
            if (node.getNodeTurnPenaltyBans() != null) {
                for (NodeTurnPenaltyBan nodeTurnPenaltyBan: node.getNodeTurnPenaltyBans().getNodeTurnPenaltyBan()) {
                    if (nodeTurnPenaltyBan == null) {
                        continue;
                    }
                    nodalGeographyManager.createTurnPenaltyBan(nodeTurnPenaltyBan.getFromNodeName(), nodeTurnPenaltyBan.getToNodeName(), nodeTurnPenaltyBan.getPenalty().toString());
                }
            }
        }
    }

    /**
     * import speed bands.
     * @param speedBands speedBands
     */
    public void importSpeedBands(SpeedBands speedBands) {
        if (speedBands == null) {
            return;
        }
        for (SpeedBands.SpeedBand speedBand: speedBands.getSpeedBand()) {
            if (speedBand == null) {
                continue;
            }
            nodalGeographyManager.createSpeedBand(StringUtil.strToInt(speedBand.getId()), speedBand.getName(), speedBand.getName());
        }
    }

    /**
     * import trackSections.
     * @param trackSections trackSections
     */
    public void  importTrackSections(TrackSections trackSections) {
        if (trackSections == null) {
            return;
        }
        for (TrackSection trackSection: trackSections.getTrackSection()) {
            if (trackSection == null) {
                continue;
            }
            nodalGeographyManager.createTrackSection(StringUtil.strToInt(trackSection.getId()), trackSection.getName(), trackSection.isIsUp(), trackSection.isIsPermissive());
        }
    }

    /**
     * import links.
     * @param links links
     */
    public void importLinks(Links links) {
        List<String> linkPowers;
        List<String> linkGauges;
        NodeLink nodeLink;
        if (links == null || links.getLink() == null) {
            return;
        }

        for (Link link : links.getLink()) {
            if (link == null) {
                continue;
            }

            linkPowers = new ArrayList<String>();
            if (link.isIsACEnergy()) {
                linkPowers.add(IConstants.POWER_TYPE_AC);
            }
            if (link.isIsDCEnergy()) {
                linkPowers.add(IConstants.POWER_TYPE_DC);
            }
            if (link.isIsBusEnergy()) {
                linkPowers.add(IConstants.POWER_TYPE_BUS);
            }
            if (link.isIsDieselEnergy()) {
                linkPowers.add(IConstants.POWER_TYPE_DIESEL);
            }

            linkGauges = new ArrayList<String>();
            if (link.isIsBusGauge()) {
                linkGauges.add(IConstants.GAUGE_BUS);
            }
            if (link.isIsNarrowGauge()) {
                linkGauges.add(IConstants.GAUGE_NARROW);
            }
            if (link.isIsStandardGauge()) {
                linkGauges.add(IConstants.GAUGE_STANDARD);
            }
            if (link.isIsBroadGauge()) {
                linkGauges.add(IConstants.GAUGE_BRAOD);
            }
            nodeLink = nodalGeographyManager.createNodeLink(link.getFromNodeName(), link.getToNodeName(), link.getLength(),
                    link.isIsSiding(), link.isIsCrossOver(), link.isIsRunningLine(),
                    StringUtil.strToInt(link.getTrackSectionId()), linkPowers, linkGauges);
            if (link.getRunningTimes() != null && link.getRunningTimes().getRunningTime() != null) {
                for (RunningTime runningTime: link.getRunningTimes().getRunningTime()) {
                    if (runningTime == null) {
                        continue;
                    }
                    nodalGeographyManager.createRuningTime(nodeLink, StringUtil.strToInt(runningTime.getSBId()), runningTime.getSS().toString(), runningTime.getPP().toString());
                }
            }
        }
    }

    /**
     * for writing algorithms on graph we create a simple linkages from node to node.
     * @param links links
     */
    public void importNodeLinkages (Links links) {
        if (links == null || links.getLink() == null) {
            return;
        }
        for (Link link : links.getLink()) {
            if (link == null) {
                continue;
            }
            nodalGeographyManager.createNodeLinkage(link.getFromNodeName(), link.getToNodeName(), link.getLength(),
                    link.isIsBusEnergy(), link.isIsACEnergy(), link.isIsDCEnergy(), link.isIsDieselEnergy(),
                    link.isIsBusGauge(), link.isIsNarrowGauge(), link.isIsStandardGauge(), link.isIsBroadGauge(),
                    link.isIsSiding(), link.isIsCrossOver(), link.isIsRunningLine(), StringUtil.strToInt(link.getTrackSectionId()));
        }

    }

}
