package au.gov.nsw.railcorp.rttarefdata.manager;

import au.gov.nsw.railcorp.rtta.refint.generated.stops.RefStopLinks;
import au.gov.nsw.railcorp.rtta.refint.generated.stops.RttaStops;
import au.gov.nsw.railcorp.rttarefdata.domain.Platform;
import au.gov.nsw.railcorp.rttarefdata.domain.Station;
import au.gov.nsw.railcorp.rttarefdata.domain.StationTriplet;

import java.util.Collection;
import java.util.List;

/**
 * Created by arash on 7/11/14.
 */
public interface IStopManager {
    /**
     * create station.
     * @param shortName shortName
     * @param longName longName
     * @param gtfsStopId gtfsStopId
     * @param latt latt
     * @param longt longt
     * @return Station station
     */
    Station createStation(String shortName, String longName, int gtfsStopId, Double latt, Double longt);

    /**
     * create Platform.
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
    Platform createPlatform(int parentStopId, int gtfsStopId, String nodeName, String longName, int platformNo, String platformName, double latt, double longt);

    /**
     * create Triplet.
     * @param inStopId inStopId
     * @param stopId stopId
     * @param outStopId outStopId
     * @param reversible reversible
     * @param powerTypes powerTypes
     * @return StationTriplet stationTriplet
     */
    StationTriplet createTriplet(int inStopId, int stopId, int outStopId, boolean reversible, Collection<String> powerTypes);

    /**
     * return all stations.
     * @return list of stations
     */
    List getAllStations();

    /**
     * return all platforms.
     * @return list of platform
     */
    List getAllPlatforms();
    /**
     * return all triplets.
     * @return list of triplet
     */
    List getAllTriplets();

    /**
     * Return powertypes for specific triplet.
     * @param inSTopId inStopId.
     * @param stopId stopId.
     * @param outStopId outStopId.
     * @return List of power type
     */
    List getTripletPowerTypes(int inSTopId, int stopId, int outStopId);
    /**
     * return list of all platfroms.
     * @return List of Platform
     */
    List<Station> getAllStops();
    /**
     * buildUp and return stops link.
     * @return RefStopLinks
     */
    RefStopLinks buildStopLinks();
    /**
     * build stop list.
     * @return RttaStops
     */
    RttaStops buildStopList();
    /**
     * remove all stations per version.
     * @param versionName versionName
     * @return boolean
     */
    boolean deleteAllStationsPerVersion (String versionName);

    /**
     * remove all platforms per version.
     * @param versionName versionName
     * @return boolean
     */
    boolean deleteAllPlatformsPerVersion (String versionName);
    /**
     * remove all triplets per version.
     * @param versionName versionName
     * @return boolean
     */
    boolean deleteAllTripletsPerVersion (String versionName);

}
