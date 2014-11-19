package au.gov.nsw.railcorp.rttarefdata;

import au.gov.nsw.railcorp.rtta.refint.boot.StopsBootManager;
import au.gov.nsw.railcorp.rttarefdata.manager.ServiceTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:rttaDataRefContext.xml")
public class SpringAppTests {
    @Autowired
    private ServiceTest serviceTest;

    /**
     * insertStation.
     */
    public void insertStation() {
        serviceTest.createStation(new Long(43), "BTN", "Blacktown Station", 214810, -33.76822903400, 150.90751054500);
        serviceTest.createStation(new Long(147), "SVH", "Seven Hills Station", 214710, -33.77443043400, 150.93651335900);
        serviceTest.createStation(new Long(43), "TGB", "Toongabbie Station", 214610, -33.78726299620, 150.95143732300);
        serviceTest.createStation(new Long(43), "PDH", "Pendle Hill Station", 214530, -33.80134789910, 150.95634268400);

        serviceTest.createStation(new Long(341), "ADN", "Aberdeen", 233610, -32.1671035802, 150.892052189);
        serviceTest.createStation(new Long(342), "SCN", "Scone", 233710, -32.0469408939, 150.866902291);
        serviceTest.createStation(new Long(343), "Murrurundi", "Murrurundi", 23381, -31.76842900360, 150.83945100200);
    }

    public void insertStationTriplet () {
        String[] powerTypes = {"AC","Diesel"};
        serviceTest.createTriplet(233610, 233710, 23381, true, powerTypes);
    }
    public void insertPlatform() {
        serviceTest.createPlatform(214810, 2148531, "BTN1", "Blacktown Station Platform 1", 1, "#1");
        serviceTest.createPlatform(214810, 2148532, "BTN2", "Blacktown Station Platform 2", 2, "#2");
        serviceTest.createPlatform(214810, 2148533, "BTN3", "Blacktown Station Platform 3", 3, "#3");
        serviceTest.createPlatform(214810, 2148534, "BTN4", "Blacktown Station Platform 4", 4, "#4");

    }

    public void insertNetwork() {
        serviceTest.createNetwork("SydneyTrains", "Sydney Trains");
    }

    public void insertNetworkLine() {
        serviceTest.createNetworkLine("CMB", "Cumberland Line", "C21DAC", "FFFFFF", "SydneyTrains", "Suburban" );
    }

    public void insertLinePath () {
        String[] powerTypes = {"AC","Diesel"};
        String[] stationPath = {"BTN", "SVH", "TGB", "PDH"};
        String[] pathInclude = {"SVH", "TGB"};
        serviceTest.createLinePath("CMB", "CUL_1a", "Blacktown to Campbelltown", powerTypes, null, stationPath, pathInclude);
    }
    /**
     * insert service type.
     */
    public void insertServiceType() {
        serviceTest.createServiceType("InterCity", "InterCity");
        serviceTest.createServiceType("Suburban", "Suburban");
        serviceTest.createServiceType("None", "None");
    }

    /**
     * insertPowerType.
     */
    public void insertPowerType() {
        serviceTest.createPowerType("AC", "Electric");
        serviceTest.createPowerType("DC", "Electric");
        serviceTest.createPowerType("Diesel", "Diesel");
    }

    @Test
    public void insertTestData() {
        insertServiceType();
        System.out.println("service type inserted.");
        insertPowerType();
        System.out.println("power type inserted.");
        insertStation();
        System.out.println("stations inserted.");
        insertStationTriplet();
        System.out.println("station triplets inserted.");
        insertPlatform();
        System.out.println("platforms inserted.");
        insertNetwork();
        System.out.println("network inserted.");
        insertNetworkLine();
        System.out.println("network line inserted.");
        insertLinePath();
        System.out.println("line path inserted.");
    }
}
