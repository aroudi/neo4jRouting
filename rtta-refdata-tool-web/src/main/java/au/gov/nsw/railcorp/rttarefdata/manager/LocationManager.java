package au.gov.nsw.railcorp.rttarefdata.manager;

import au.gov.nsw.railcorp.rttarefdata.domain.Location;
import au.gov.nsw.railcorp.rttarefdata.domain.Node;
import au.gov.nsw.railcorp.rttarefdata.repositories.LocationRepository;
import au.gov.nsw.railcorp.rttarefdata.repositories.NodeRepository;
import au.gov.nsw.railcorp.rttarefdata.session.SessionState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by arash on 20/01/2015.
 */

@Component
@Transactional
public class LocationManager implements ILocationManager {

    private final Logger logger = LoggerFactory.getLogger(TopologyManager.class);
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private NodeRepository nodeRepository;

    @Autowired
    private SessionState sessionState;

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
    public Location createLocation(String name, String systemName, double longtitude, double latitude, String nodeName, int excludeFringe) {
        try {
            if (name == null || name.isEmpty()) {
                logger.error("location name is empty");
                return null;
            }
            Location location = locationRepository.getLocationByName(sessionState.getWorkingVersion().getName(), name);
            if (location == null) {
                location = new Location();
            }
            location.setName(name);
            location.setSystemName(systemName);
            location.setLongtitude(longtitude);
            location.setLatitude(latitude);
            location.setNodeName(nodeName);
            if (nodeName != null || !nodeName.isEmpty()) {
                final Node node = nodeRepository.getNodePerName(sessionState.getWorkingVersion().getName(), nodeName);
                if (node == null) {
                    logger.error("not able to locate node " + nodeName);
                }
                location.setNode(node);
            }
            location.setExcludeFringe(excludeFringe);
            location.setVersion(sessionState.getWorkingVersion());
            return locationRepository.save(location);
        } catch (Exception e) {
            logger.error("Unable to create location : ", e);
            return null;
        }
    }

    /**
     * return list of locations.
     * @return list of locations
     */
    public List<Location> getAllLocations() {
        try {
            logger.info("sessionState = " + sessionState);
            logger.info("working version = " + sessionState.getWorkingVersion());
            logger.info("working version name = " + sessionState.getWorkingVersion().getName());
            return locationRepository.getAllLocations(sessionState.getWorkingVersion().getName());
        } catch (Exception e) {
            logger.error("error in retrieving location list: ", e);
            return  null;
        }
    }

    /**
     * find location by name.
     * @param name name
     * @return location
     */
    public Location getLocationByName(String name) {
        try {
            return locationRepository.getLocationByName(sessionState.getWorkingVersion().getName(), name);
        } catch (Exception e) {
            logger.error("Error in finding location by name: ", e);
            return null;
        }
    }

    /**
     * delete location by id.
     * @param locationId locationId
     * @return status
     */
    public int deleteLocation (Long locationId) {
        try {
            locationRepository.delete(locationId);
            return 0;
        } catch (Exception e) {
            logger.error("Not able to delete location");
            return -1;
        }
    }

    /**
     * remove all locations per version.
     * @param versionName versionName
     * @return boolean
     */
    public boolean deleteAllLocationsPerVersion (String versionName) {
        try {
            locationRepository.deleteLocationsPerVersion(versionName);
            return true;
        } catch (Exception e) {
            logger.error("Exception in removing locations:", e);
            return false;
        }
    }
}
