package au.gov.nsw.railcorp.rttarefdata.manager;

import au.gov.nsw.railcorp.rttarefdata.domain.Location;

import java.util.List;

/**
 * Created by arash on 20/01/2015.
 */
public interface ILocationManager {

    /**
     * create a location.
     * @param name name
     * @param systemName systemName
     * @param longtitude longtitude
     * @param latitude latitude
     * @param nodeName nodeName
     * @param excludeFringe excludeFringe
     * @return Location
     */
    Location createLocation(String name, String systemName, double longtitude, double latitude, String nodeName, int excludeFringe);

    /**
     * return list of locations.
     * @return list of locations
     */
    List<Location> getAllLocations();

    /**
     * find location by name.
     * @param name name
     * @return location
     */
    Location getLocationByName(String name);

    /**
     * delete location by id.
     * @param locationId locationId
     * @return status
     */
    int deleteLocation (Long locationId);


}
