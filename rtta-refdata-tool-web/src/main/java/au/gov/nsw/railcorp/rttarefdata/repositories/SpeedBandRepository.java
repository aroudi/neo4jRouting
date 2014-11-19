// RailCorp 2014
package au.gov.nsw.railcorp.rttarefdata.repositories;

import au.gov.nsw.railcorp.rttarefdata.domain.SpeedBand;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by arash on 3/11/14.
 */
public interface SpeedBandRepository extends GraphRepository<SpeedBand> {
}
