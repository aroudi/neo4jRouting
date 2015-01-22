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
     * @return List of Location
     */
    @Query(
            "MATCH (location:Location) RETURN location "
    )
    List<Location> getAllLocations();
}
