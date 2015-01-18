// RailCorp 2014
package au.gov.nsw.railcorp.rttarefdata.repositories;

import au.gov.nsw.railcorp.rttarefdata.domain.SpeedBand;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.List;

/**
 * Created by arash on 3/11/14.
 */
public interface SpeedBandRepository extends GraphRepository<SpeedBand> {
    /**
     * return all speed bands.
     * @return List of speedBands
     */
    @Query("MATCH (speedBand:SpeedBand) RETURN speedBand")
    List<SpeedBand> getAllSpeedBands();

}
