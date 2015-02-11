package au.gov.nsw.railcorp.rttarefdata.service;

import au.gov.nsw.railcorp.rtta.refint.generated.geography.CgGeography;
import au.gov.nsw.railcorp.rttarefdata.domain.*;
import au.gov.nsw.railcorp.rttarefdata.manager.INodalGeographyManager;
import au.gov.nsw.railcorp.rttarefdata.manager.NodalGeographyManager;
import au.gov.nsw.railcorp.rttarefdata.manager.ServiceTest;
import au.gov.nsw.railcorp.rttarefdata.mapresult.INodeLinkData;
import au.gov.nsw.railcorp.rttarefdata.mapresult.IStationPlatformData;
import au.gov.nsw.railcorp.rttarefdata.mapresult.StationPlatformData;
import au.gov.nsw.railcorp.rttarefdata.repositories.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Iterator;
import java.util.List;

/**
 * Created by arash on 7/11/14.
 */
public class TestQuery {
    static StationTripletRepository tripletRepository;
    static NodeLinkRepository nodelinkRepository;
    static ServiceTest serviceTest;
    static RuningTimeRepository runingTimeRepository;
    static NodeLinkageRepository nodeLinkageRepository;
    static NodeRepository nodeRepository;
    static PlatformRepository platformRepository;
    static NodeLinkRepository nodeLinkRepository;
    static INodalGeographyManager nodalGeographyManager;
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("rttaDataRefContext.xml");
        tripletRepository = context.getBean(StationTripletRepository.class);
        nodelinkRepository = context.getBean(NodeLinkRepository.class);
        runingTimeRepository = context.getBean(RuningTimeRepository.class);
        nodeLinkageRepository = context.getBean(NodeLinkageRepository.class);
        nodeRepository = context.getBean(NodeRepository.class);
        platformRepository = context.getBean(PlatformRepository.class);
        serviceTest = context.getBean(ServiceTest.class);
        nodeLinkRepository = context.getBean(NodeLinkRepository.class);
        nodalGeographyManager = context.getBean(INodalGeographyManager.class);
        //createStationTriplet();
        //queryStationTriplet();

        //createNodeLink();
        //removeNodeLink();
        //removeNodeLinkage();
        //queryNodeLinkage();
        //queryNodeLinkReposiory();
        traverse();




    }
    public static void queryStationTriplet() {



        StationTriplet stationTriplet = tripletRepository.getTriplet(234012,23521,23541);
        stationTriplet = tripletRepository.findByFromStationGtfsStopIdAndStationGtfsStopIdAndToStationGtfsStopId(234012, 23521, 23541);
        
        System.out.println("isReversible: " + stationTriplet.isReversible());
        System.out.println("fromStation : " + stationTriplet.getFromStation().getGtfsStopId());
        System.out.println("toStation : " + stationTriplet.getToStation().getGtfsStopId());

        stationTriplet = tripletRepository.findByFromStationGtfsStopIdAndStationGtfsStopIdAndToStationGtfsStopId(233610,233710,23381);
        System.out.println("isReversible: " + stationTriplet.isReversible());
        System.out.println("fromStation : " + stationTriplet.getFromStation().getGtfsStopId());
        System.out.println("toStation : " + stationTriplet.getToStation().getGtfsStopId());

        //tripletRepository.delete(stationTriplet);


    }
    public static void createStationTriplet() {
        // Triplets
        String[] powerTypes = {"AC","Diesel"};
        serviceTest.createTriplet(233610, 233710, 23381, true, powerTypes);
        System.out.println("station triplets inserted.");

    }

    public static void removeNodeLink() {


        NodeLink nodeLink = nodelinkRepository.getNodeLink("BTN1", "BTJB");
        //NodeLink nodeLink = nodelinkRepository.findByFromNodeNameAndToNodeName("BTN1", "BTJB");
        //System.out.println(nodeLink.getFromNode().getName());
        if (nodeLink !=null ){
            System.out.println("nodeLink.getRunningTime :" +nodeLink.getRunningTimes());
            if (nodeLink.getRunningTimes() !=null ) {
                for (RuningTime runingTime:nodeLink.getRunningTimes()) {
                    System.out.println("running time :"+ runingTime);
                    //runingTimeRepository.delete(runingTime);
                }
            }
            runingTimeRepository.delete(nodeLink.getRunningTimes());
        }
        nodelinkRepository.delete(nodeLink);
    }

    public static void createNodeLink() {
        String[] powerTypes3 = {"AC","Diesel"};
        String[] gauges = {"StandardGauge"};
        final NodeLink nodeLink = serviceTest.createNodeLink("BTN1", "BTJB", 200, false, false, true, 1553, powerTypes3, gauges);
        serviceTest.createRuningTime(nodeLink, 18, null, null);
        serviceTest.createRuningTime(nodeLink, 1, null, null);

    }
    public static void removeNodeLinkage() {
        Node node = nodeRepository.findBySchemaPropertyValue("name", "BTN1");
        if (node.getNodeLinkages() != null) {
            for (NodeLinkage nodeLinkage: node.getNodeLinkages()) {
                System.out.println("node linkage object = " + nodeLinkage );
            }

        }
        if (node != null) {
            nodeLinkageRepository.delete(node.getNodeLinkages());
        }
    }
    public static void queryNodeLinkage(){
        NodeLinkage nodeLinkage= nodeLinkageRepository.getNodeLinkage("GRDA", "GRDC");
        if (nodeLinkage != null) {
            System.out.println("nodeLinkage =" + nodeLinkage.getLength());
        }
    }
    public static void queryPlatforms(){
        List<IStationPlatformData> platformDataList= platformRepository.getAllPlatforms();
        if (platformDataList == null) {
            System.out.println("list is null");
        }
        for (IStationPlatformData platformData:platformDataList) {
            System.out.println("platform ="+ platformData.getPlatformName());
        }
    }
    public static void queryNodeLinkReposiory(){
        System.out.print("befor returning nodeList ");
        List<INodeLinkData> nodeLinkDatas = nodeLinkRepository.getAllNodeLinks();
        int i=0;
        for (INodeLinkData nodeLinkData:nodeLinkDatas) {
            i = i+1;
            System.out.println("for nodeLink: " + i);
            List<RuningTime> runingTimes = nodeLinkRepository.getNodeLinkRunningTimes(nodeLinkData.getNodeLinkId());
        }
        System.out.print("after returning nodeList ");
    }

    public void test () {
        CgGeography cgGeography = new CgGeography();
        //cgGeography.Geov10RC.Nodes ;
    }

    public static void traverse() {
        nodalGeographyManager.findAllPaths("WAI1","CE18");
        //nodalGeographyManager.findAllPaths("BNX1","ARM1");
    }
}
