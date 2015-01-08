package au.gov.nsw.railcorp.rttarefdata.manager;

import au.gov.nsw.railcorp.rttarefdata.domain.Platform;
import au.gov.nsw.railcorp.rttarefdata.domain.PowerType;
import au.gov.nsw.railcorp.rttarefdata.domain.Station;
import au.gov.nsw.railcorp.rttarefdata.domain.StationTriplet;
import au.gov.nsw.railcorp.rttarefdata.mapresult.IStationData;
import au.gov.nsw.railcorp.rttarefdata.mapresult.IStationPlatformData;
import au.gov.nsw.railcorp.rttarefdata.mapresult.StationData;
import au.gov.nsw.railcorp.rttarefdata.mapresult.StationPlatformData;
import au.gov.nsw.railcorp.rttarefdata.repositories.PlatformRepository;
import au.gov.nsw.railcorp.rttarefdata.repositories.StationRepository;
import au.gov.nsw.railcorp.rttarefdata.repositories.StationTripletRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by arash on 6/11/14.
 */
@Component
@Transactional
public class StopManager implements IStopManager {

    private final Logger logger = LoggerFactory.getLogger(TopologyManager.class);

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
    private EntitySequenceManager entitySequenceManager;

    @Autowired
    private IDataTypeManager dataTypeManager;
    /**
     * create station.
     * @param shortName shortName
     * @param longName longName
     * @param gtfsStopId gtfsStopId
     * @param latt latt
     * @param longt longt
     * @return Station station
     */
    public Station createStation(String shortName, String longName, int gtfsStopId, Double latt, Double longt) {
        //try to find station if not found create it.
        Station station = stationRepository.findBySchemaPropertyValue("gtfsStopId", gtfsStopId);
        if (station == null) {
            station = new Station();
            station.setStationId(entitySequenceManager.getNextStationSequence());
            station.setGtfsStopId(gtfsStopId);
        }
        station.setShortName(shortName);
        station.setLongName(longName);
        station.setLatitude(latt);
        station.setLongtitude(longt);
        station.setInterchangePoint(false);
        stationRepository.save(station);
        return station;
    }

    /**
     * create platform.
     * @param parentStopId parentStopId
     * @param gtfsStopId gtfsStopId
     * @param nodeName nodeName
     * @param longName longName
     * @param platformNo platformNo
     * @param platformName platformName
     * @param latt latt
     * @param longt longt
     * @return Platform platform
     */
    public Platform createPlatform(int parentStopId, int gtfsStopId, String nodeName, String longName, int platformNo, String platformName, double latt, double longt) {

        Platform platform = platformRepository.findBySchemaPropertyValue("gtfsStopId", gtfsStopId);
        if (platform == null) {
            platform = new Platform();
        }
        platform.setGtfsStopId(gtfsStopId);
        platform.setPlatformNo(platformNo);
        platform.setName(nodeName);
        platform.setPlatfromName(platformName);
        platform.setLongName(longName);
        platform.setLatitude(latt);
        platform.setLongitude(longt);

        final Station station = stationRepository.findBySchemaPropertyValue("gtfsStopId", parentStopId);
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
    public StationTriplet createTriplet(int inStopId, int stopId, int outStopId, boolean reversible, Collection<String> powerTypes) {

        //search for triplet if fount then delete and recreate it.

        StationTriplet stationTriplet = stationTripletRepository.findByFromStationGtfsStopIdAndStationGtfsStopIdAndToStationGtfsStopId(inStopId, stopId, outStopId);
        if (stationTriplet != null) {
            stationTripletRepository.delete(stationTriplet);
        }
        stationTriplet = new StationTriplet();

        final Station mainStation = stationRepository.findBySchemaPropertyValue("gtfsStopId", stopId);
        final Station fromStation = stationRepository.findBySchemaPropertyValue("gtfsStopId", inStopId);
        final Station toStation = stationRepository.findBySchemaPropertyValue("gtfsStopId", outStopId);

        //all nodes must be exists. otherwise return
        if (mainStation == null || fromStation == null || toStation == null) {
            return null;
        }
        stationTriplet.setFromStation(fromStation);
        stationTriplet.setStation(mainStation);
        stationTriplet.setToStation(toStation);
        stationTriplet.setReversible(reversible);


        for (String powertypeName : powerTypes) {
            final PowerType powerType = dataTypeManager.addPowerType(powertypeName, powertypeName);
            if (powerType != null) {
                stationTriplet.addPowerType(powerType);
            }
        }
        stationTripletRepository.save(stationTriplet);
        return stationTriplet;
    }

    /**
     * return all stations.
     * @return list of stations
     */
    public List getAllStations() {
        final List<StationData> result = new ArrayList<StationData>();
        final List<IStationData> stations;
        StationData stationData;
        try {
            stations = stationRepository.getAllStations();
            for (IStationData station: stations) {
                stationData = new StationData();
                stationData.setStationId(station.getStationId());
                stationData.setShortName(station.getShortName());
                stationData.setLongName(station.getLongName());
                stationData.setGtfsStopId(station.getGtfsStopId());
                try {
                    stationData.setLongtitude(station.getLongtitude());
                } catch (NullPointerException e1) {
                    stationData.setLongtitude(0);
                }
                try {
                    stationData.setLatitude(station.getLatitude());
                } catch (NullPointerException e2) {
                    stationData.setLatitude(0);
                }
                result.add(stationData);
            }
            return result;
        } catch (Exception e) {
            logger.error("Exception in returning station list ", e);
            return null;
        }
    }

    /**
     * get all platforms.
     * @return platform list
     */
    public List getAllPlatforms() {
        List<IStationPlatformData> platformList;
        final List<StationPlatformData> result = new ArrayList<StationPlatformData>();
        StationPlatformData stationPlatformData;
        try {
            platformList = platformRepository.getAllPlatforms();
            for (IStationPlatformData platformData: platformList) {
                stationPlatformData = new StationPlatformData();
                stationPlatformData.setStationId(platformData.getStationId());
                stationPlatformData.setPlatformLongName(platformData.getPlatformLongName());
                stationPlatformData.setPlatformName(platformData.getPlatformName());
                stationPlatformData.setPlatformNo(platformData.getPlatformNo());
                stationPlatformData.setPlatformStopId(platformData.getPlatformStopId());
                stationPlatformData.setStationShortName(platformData.getStationShortName());
                stationPlatformData.setStationStopId(platformData.getStationStopId());
                stationPlatformData.setLatitude(platformData.getLatitude());
                stationPlatformData.setLongtitude(platformData.getLongtitude());
                result.add(stationPlatformData);
            }
            return result;
        } catch (Exception e) {
            logger.error("Exception in returning platform list ", e);
            return null;
        }
    }

    /**
     * return all triplets.
     * @return list of triplet
     */
    public List getAllTriplets() {
        try {
            return stationTripletRepository.getAllTriplets();
        } catch (Exception e) {
            logger.error("Exception in returning triplet list ", e);
            return null;
        }
    }

    /**
     * Return powertypes for specific triplet.
     * @param inSTopId inStopId.
     * @param stopId stopId.
     * @param outStopId outStopId.
     * @return List of power type
     */
    public List getTripletPowerTypes(int inSTopId, int stopId, int outStopId) {
        try {
            return stationTripletRepository.getTripletPowerTypes(inSTopId, stopId, outStopId);
        } catch (Exception e) {
            logger.error("Exception in returning triplet list ", e);
            return null;
        }
    }
}
