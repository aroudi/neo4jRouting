package au.gov.nsw.railcorp.rttarefdata.service;

import au.gov.nsw.railcorp.rtta.refint.boot.GtfsTopologyBootManager;
import au.gov.nsw.railcorp.rtta.refint.boot.NodalGeographyBootManager;
import au.gov.nsw.railcorp.rtta.refint.boot.NodeBootManager;
import au.gov.nsw.railcorp.rtta.refint.boot.StopsBootManager;
import au.gov.nsw.railcorp.rtta.refint.generated.geography.CgGeography;
import au.gov.nsw.railcorp.rtta.refint.generated.gtfs.RttaGtfsTopology;
import au.gov.nsw.railcorp.rtta.refint.generated.nodes.RttaNodes;
import au.gov.nsw.railcorp.rtta.refint.generated.stops.RefStop;
import au.gov.nsw.railcorp.rtta.refint.generated.stops.RttaStops;
import au.gov.nsw.railcorp.rttarefdata.manager.NodalGeographyManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by arash on 5/11/14.
 */
public class ImportTest {
    private static final Logger commonLoger = LoggerFactory.getLogger("commonLoger");
    private static StopsBootManager stopsBootManager;
    private static GtfsTopologyBootManager gtfsTopologyBootManager;
    private static NodeBootManager nodeBootManager;
    private static NodalGeographyBootManager nodalGeographyBootManager;
    private static StopService stopService;
    private static TopologyService topologyService;
    private static NodeService nodeService;
    private static NodalGeographyService nodalGeographyService;

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("rttaDataRefContext.xml");
        stopsBootManager = context.getBean(StopsBootManager.class);
        gtfsTopologyBootManager = context.getBean(GtfsTopologyBootManager.class);
        gtfsTopologyBootManager = context.getBean(GtfsTopologyBootManager.class);
        nodeBootManager = context.getBean(NodeBootManager.class);
        nodalGeographyBootManager = context.getBean(NodalGeographyBootManager.class);
        stopService = context.getBean(StopService.class);
        topologyService = context.getBean(TopologyService.class);
        nodeService = context.getBean(NodeService.class);
        nodalGeographyService = context.getBean(NodalGeographyService.class);

        /*
        RttaStops rttaStops = stopsBootManager.loadStopsFromFile();
        commonLoger.debug("before importing...");
        stopService.importRttaStops(rttaStops);
        */
        importStops();
        System.out.println("stops imported");
        importGtfsTopology();
        System.out.println("topology imported");
        importGtfsNodes();
        System.out.println("nodes imported");
        importNodalGeography();
        System.out.println("nodal geography imported");

    }
    public static void importStops() {
        RttaStops rttaStops = stopsBootManager.loadStopsFromFile();
        commonLoger.debug("before importing...");
        stopService.importRttaStops(rttaStops);
    }
    public static void importGtfsTopology() {
        System.out.println("before importing gtfs topology....");
        RttaGtfsTopology rttaGtfsTopology = gtfsTopologyBootManager.loadTopologyFromFile();
        topologyService.importRttaTopology(rttaGtfsTopology);
        //System.out.println(rttaGtfsTopology);
    }
    public static void importGtfsNodes() {
        System.out.println("before importing Nodes....");
        RttaNodes rttaNodes = nodeBootManager.loadNodesFromFile();
        nodeService.importRttaNodes(rttaNodes);
    }

    public static void importNodalGeography() {
        System.out.println("before importing Nodal Geography....");
        CgGeography cgGeography = nodalGeographyBootManager.loadNodalGeographyFromFile();
        nodalGeographyService.importRailNetGeography(cgGeography);
    }

}
