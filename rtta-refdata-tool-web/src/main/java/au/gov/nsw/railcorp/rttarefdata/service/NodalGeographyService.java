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
import au.gov.nsw.railcorp.rttarefdata.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.StreamingOutput;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;


/**
 * Created by arash on 11/11/14.
 */
@Component
public class NodalGeographyService {
    private final Logger logger = LoggerFactory.getLogger(NodalGeographyService.class);
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
        try {
            for (CgGeography.Geov10RC geov10RC : cgGeography.getGeov10RC()) {
                if (geov10RC == null) {
                    continue;
                }
                importNodalHeader(geov10RC);
                importSpeedBands(geov10RC.getSpeedBands());
                logger.info("importRailNetGeography: importSpeedBands finished");
                importTrackSections(geov10RC.getTrackSections());
                logger.info("importRailNetGeography: importTrackSections finished");
                importNodes(geov10RC.getNodes());
                logger.info("importRailNetGeography: importNodes finished");
                importLinks(geov10RC.getLinks());
                logger.info("importRailNetGeography: importLinks finished");
                importNodeLinkages(geov10RC.getLinks());
                logger.info("importRailNetGeography: importNodeLinkages finished");
                importNodeSelfRelations(geov10RC.getNodes());
                logger.info("importRailNetGeography: importNodeSelfRelations finished");

            }
        } catch (Exception e) {
            logger.error("Exception in importing nodal geography: ", e);
        }

    }

    /**
     * import nodes.
     * @param nodes list of nodes
     */
    //@Transactional
    public void importNodes(Nodes nodes) {

        if (nodes == null || nodes.getNode() == null) {
            return;
        }
        //remove All TurnPenaltyBans
        nodalGeographyManager.emptyNodeTurnPenaltyBan();
        nodalGeographyManager.emptyNodeNodeLinkages();
        String masterTimingPoint = "";
        String masterJunction = "";
        for (Node node: nodes.getNode()) {
            if (node == null) {
                continue;
            }
            if (node.getNodeMasterJunction() != null && node.getNodeMasterJunction().size() > 0) {
                masterJunction = node.getNodeMasterJunction().get(0).getNodeName();
            }
            if (node.getNodeMasterTimingPoint() != null && node.getNodeMasterTimingPoint().size() > 0) {
                masterTimingPoint = node.getNodeMasterTimingPoint().get(0).getNodeName();
            }
            nodalGeographyManager.createNode(node.getName(), node.getLongName(), node.getPlatformName(), node.isIsDummy(),
                    node.isIsJunction(), node.isIsWorkingTimingPoint(), node.isIsPublicTimingPoint(), node.isIsEndOfLine(),
                    node.getDwellDuration().toString(), node.getUpRecoveryDuration().toString(), node.getDownRecoveryDuration().toString(), node.getLength(), masterTimingPoint, masterJunction);

        }

    }

    /**
     * create MasterTimingPoint, MaterJunction and TurnPenaltyBan relations between nodes.
     * @param nodes nodes
     */
    //@Transactional
    public void importNodeSelfRelations(Nodes nodes) {
        if (nodes == null || nodes.getNode() == null) {
            return;
        }

        for (Node node: nodes.getNode()) {
            if (node == null) {
                continue;
            }
            /*
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
            */
            if (node.getNodeTurnPenaltyBans() != null) {
                for (NodeTurnPenaltyBan nodeTurnPenaltyBan: node.getNodeTurnPenaltyBans().getNodeTurnPenaltyBan()) {
                    if (nodeTurnPenaltyBan == null) {
                        continue;
                    }
                    nodalGeographyManager.createTurnPenaltyBan(node.getName(), nodeTurnPenaltyBan.getFromNodeName(), nodeTurnPenaltyBan.getToNodeName(), nodeTurnPenaltyBan.getPenalty().toString());
                }
            }
        }
    }

    /**
     * import speed bands.
     * @param speedBands speedBands
     */
    //@Transactional
    public void importSpeedBands(SpeedBands speedBands) {
        nodalGeographyManager.emptySpeedBands();
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
    //@Transactional
    public void  importTrackSections(TrackSections trackSections) {
        nodalGeographyManager.emptyTrackSections();
        if (trackSections == null) {
            return;
        }
        for (TrackSection trackSection: trackSections.getTrackSection()) {
            if (trackSection == null) {
                continue;
            }
            nodalGeographyManager.createTrackSection(trackSection.getId(), trackSection.getName(), trackSection.isIsUp(), trackSection.isIsPermissive());
        }
    }

    /**
     * import links.
     * @param links links
     */
    //@Transactional
    public void importLinks(Links links) {
        NodeLink nodeLink;
        if (links == null || links.getLink() == null) {
            return;
        }

        //empty the current links
        nodalGeographyManager.emptyRunningTimes();
        nodalGeographyManager.emptyNodeLinks();

        for (Link link : links.getLink()) {
            if (link == null) {
                continue;
            }

            nodeLink = nodalGeographyManager.createNodeLink(link.getFromNodeName(), link.getToNodeName(), link.getLength(),
                    link.isIsSiding(), link.isIsCrossOver(), link.isIsRunningLine(), link.getTrackSectionId(), link.isIsBusEnergy(),
                    link.isIsACEnergy(), link.isIsDCEnergy(), link.isIsDieselEnergy(), link.isIsBusGauge(), link.isIsNarrowGauge(), link.isIsStandardGauge(), link.isIsBroadGauge());
            if (link.getRunningTimes() != null && link.getRunningTimes().getRunningTime() != null) {
                for (RunningTime runningTime: link.getRunningTimes().getRunningTime()) {
                    if (runningTime == null) {
                        continue;
                    }
                    nodalGeographyManager.createRuningTime(nodeLink, runningTime.getSBId(), runningTime.getSS().toString(), runningTime.getPP().toString());
                }
            }
        }
    }

    /**
     * for writing algorithms on graph we create a simple linkages from node to node.
     * @param links links
     */
    //@Transactional
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

    /**
     * import NodalHeader.
     * @param geov10RC geov10RC
     */
    //@Transactional
    public void importNodalHeader(CgGeography.Geov10RC geov10RC) {
        if (geov10RC != null) {
            nodalGeographyManager.emptyTrackSections();
            nodalGeographyManager.createNodalHeader(geov10RC.getDescription(), geov10RC.getOwner(), geov10RC.getDate().toString());
        }
    }

    /**
     * extract NodalGeography.
     * @return CgGeography
     */
    public CgGeography exportNodalGeography() {
        return nodalGeographyManager.exportNodalGeography();
    }

    /**
     * Export Platform list into csv format and return as StreamingOutput.
     * @return Streamingoutput
     */
    public StreamingOutput exportNodalGeographyToXml() {
        StreamingOutput streamingOutput = null;
        try {
            final StringWriter writer = new StringWriter();
            final JAXBContext context = JAXBContext.newInstance(CgGeography.class);
            final Marshaller marshaller = context.createMarshaller();

            final CgGeography cgGeography = nodalGeographyManager.exportNodalGeography();
            marshaller.marshal(cgGeography, writer);
            final String theXml = writer.toString();
            logger.info(" exported nodalGegoraphy: ");
            logger.info(theXml);
            streamingOutput = new StreamingOutput() {
                @Override
                public void write(OutputStream output) throws IOException, WebApplicationException {
                    output.write(theXml.getBytes());
                    output.flush();
                }
            };
            return streamingOutput;
        } catch (Exception e) {
            logger.error("Exception in writing nodalGeography into xml: ", e);
            return null;
        }
    }

}
