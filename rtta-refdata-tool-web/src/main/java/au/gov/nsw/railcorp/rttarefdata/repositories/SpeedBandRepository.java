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
     * @param version version
     * @return List of speedBands
     */
    @Query("MATCH (version:DataVersion{name:{0}})-[:VERSION_SPEEDBAND]-(speedBand:SpeedBand) RETURN speedBand")
    List<SpeedBand> getAllSpeedBands(String version);

    /**
     * delete all SpeedBands with specific version.
     * @param version version;
     */
    @Query("MATCH (version:DataVersion)-[:VERSION_SPEEDBAND]-(sb:SpeedBand) WHERE version.name={0} DELETE sb")
    void deleteSpeedBandPerVersion(String version);

}
