package au.gov.nsw.railcorp.rttarefdata.service;

import au.gov.nsw.railcorp.rttarefdata.domain.NodeLink;
import au.gov.nsw.railcorp.rttarefdata.domain.NodeLinkage;
import au.gov.nsw.railcorp.rttarefdata.manager.ServiceTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;

/**
 * Created by arash on 30/10/14.
 */
public class InsertTestData {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("rttaDataRefContext.xml");
        ServiceTest serviceTest = context.getBean(ServiceTest.class);

        // service type
        serviceTest.createServiceType("InterCity", "InterCity");
        serviceTest.createServiceType("Suburban", "Suburban");
        serviceTest.createServiceType("None", "None");
        System.out.println("service type inserted.");

        // power type
        serviceTest.createPowerType("AC", "Electric");
        serviceTest.createPowerType("DC", "Electric");
        serviceTest.createPowerType("Diesel", "Diesel");
        System.out.println("power type inserted.");

        // stations
        serviceTest.createStation(new Long(43), "BTN", "Blacktown Station", 214810, -33.76822903400, 150.90751054500);
        serviceTest.createStation(new Long(147), "SVH", "Seven Hills Station", 214710, -33.77443043400, 150.93651335900);
        serviceTest.createStation(new Long(43), "TGB", "Toongabbie Station", 214610, -33.78726299620, 150.95143732300);
        serviceTest.createStation(new Long(43), "PDH", "Pendle Hill Station", 214530, -33.80134789910, 150.95634268400);

        serviceTest.createStation(new Long(341), "ADN", "Aberdeen", 233610, -32.1671035802, 150.892052189);
        serviceTest.createStation(new Long(342), "SCN", "Scone", 233710, -32.0469408939, 150.866902291);
        serviceTest.createStation(new Long(343), "Murrurundi", "Murrurundi", 23381, -31.76842900360, 150.83945100200);
        System.out.println("stations inserted.");

        // Triplets
        String[] powerTypes = {"AC","Diesel"};
        serviceTest.createTriplet(233610, 233710, 23381, true, powerTypes);
        System.out.println("station triplets inserted.");

        //platform
        serviceTest.createPlatform(214810, 2148531, "BTN1", "Blacktown Station Platform 1", 1, "#1");
        serviceTest.createPlatform(214810, 2148532, "BTN2", "Blacktown Station Platform 2", 2, "#2");
        serviceTest.createPlatform(214810, 2148533, "BTN3", "Blacktown Station Platform 3", 3, "#3");
        serviceTest.createPlatform(214810, 2148534, "BTN4", "Blacktown Station Platform 4", 4, "#4");
        System.out.println("platforms inserted.");

        //Network
        serviceTest.createNetwork("SydneyTrains", "Sydney Trains");
        System.out.println("network inserted.");

        // network line
        serviceTest.createNetworkLine("CMB", "Cumberland Line", "C21DAC", "FFFFFF", "SydneyTrains", "Suburban" );
        System.out.println("network line inserted.");

        //line path
        String[] powerTypes2 = {"AC","Diesel"};
        String[] stationPath = {"BTN", "SVH", "TGB", "PDH"};
        String[] pathInclude = {"SVH", "TGB"};
        serviceTest.createLinePath("CMB", "CUL_1a", "Blacktown to Campbelltown", powerTypes2, null, stationPath, pathInclude);
        System.out.println("line path inserted.");

        //////////////////////////////////////////////////////////////// Nodal Geography /////////////////////////////////////////////////////////

        serviceTest.createNode("BTN1", "Blacktown", "#1", false, false, true, true, false, null, null, null, 0);
        serviceTest.createNode("BTN2", "Blacktown", "#2", false, false, true, true, false, null, null, null, 0);
        serviceTest.createNode("BTN3", "Blacktown", "#3", false, false, true, true, false, null, null, null, 0);
        serviceTest.createNode("BTN4", "Blacktown", "#4", false, false, true, true, false, null, null, null, 0);
        serviceTest.createNode("BTJB", "Blacktown Middle", "BTNM", false, true, false, false, false, null, null, null, 0);

        serviceTest.defineNodeMasterTimingPoint("BTN3", "BTN3");
        serviceTest.defineNodeMasterTimingPoint("BTN1", "BTN3");
        serviceTest.defineNodeMasterTimingPoint("BTN2", "BTN3");
        serviceTest.defineNodeMasterTimingPoint("BTN4", "BTN3");
        serviceTest.defineNodeMasterTimingPoint("BTJB", "BTN3");

        serviceTest.defineNodeMasterJunction("BTN3", "BTN3");
        serviceTest.defineNodeMasterJunction("BTN1", "BTN3");
        serviceTest.defineNodeMasterJunction("BTN2", "BTN3");
        serviceTest.defineNodeMasterJunction("BTN4", "BTN3");
        serviceTest.defineNodeMasterJunction("BTJB", "BTJB");


        Duration duration = null;
        try {

            duration = DatatypeFactory.newInstance().newDuration("PT6S");

        } catch (Exception e) {

        }
        System.out.println("duration= " +duration.toString());
        serviceTest.createTurnPenaltyBan("BTN1", "BTN4", duration.toString());
        serviceTest.createTurnPenaltyBan("BTJB", "BTN1", duration.toString());

        // speed band //////////

        serviceTest.createSpeedBand(1, "High Speed","");
        serviceTest.createSpeedBand(18, "LE - 5","");

        // track section ///////////

        serviceTest.createTrackSection(1553, "URIC", false, false);
        serviceTest.createTrackSection(1554, "URIC", true, false);

        //gauge

        serviceTest.createGauge("BusGauge", "Bus Gauge");
        serviceTest.createGauge("NarrowGauge", "Narrow Gauge");
        serviceTest.createGauge("StandardGauge", "Standard Gauge");
        serviceTest.createGauge("BroadGauge", "Broad Gauge");

        // create node link///////////////
        String[] powerTypes3 = {"AC","Diesel"};
        String[] gauges = {"StandardGauge"};
        final NodeLink nodeLink = serviceTest.createNodeLink("BTN1", "BTJB", 200, false, false, true, 1553, powerTypes3, gauges);

        NodeLinkage nodeLinkage = serviceTest.createNodeLinkage("BTN1", "BTJB", 200, false, true, false, false, false, false, true, false, false, false, true, 1553);

        nodeLinkage = serviceTest.createNodeLinkage("BTN1", "BTJB", 201, false, true, false, false, false, false, true, false, false, false, true, 1553);

        // running time ////////////////
        serviceTest.createRuningTime(nodeLink, 18, null, null);
        serviceTest.createRuningTime(nodeLink, 1, null, null);

    }

}
