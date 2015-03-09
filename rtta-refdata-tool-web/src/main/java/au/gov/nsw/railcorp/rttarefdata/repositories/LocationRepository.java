package au.gov.nsw.railcorp.rttarefdata.repositories;

import au.gov.nsw.railcorp.rttarefdata.domain.Location;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.List;

/**
 * Created by arash on 20/01/2015.
 */
public interface LocationRepository extends GraphRepository<Location> {
    /**
     * Return all Locations.
     * @param version version
     * @return List of Location
     */
    @Query(
            "MATCH (version:DataVersion{name:{0}})-[:VERSION_LOCATION]-(location:Location) RETURN location "
    )
    List<Location> getAllLocations(String version);
    /**
     * Return location per name.
     * @param version version
     * @param name name
     * @return Location
     */
    @Query(
            "MATCH (version:DataVersion{name:{0}})-[:VERSION_LOCATION]-(location:Location{name:{1}}) RETURN location "
    )
    Location getLocationByName(String version, String name);
    /**
     * delete all locations with specific version.
     * @param version version;
     */
    @Query("MATCH (version:DataVersion)-[:VERSION_LOCATION]-(location:Location) WHERE version.name={0} DELETE location")
    void deleteLocationsPerVersion(String version);

}
