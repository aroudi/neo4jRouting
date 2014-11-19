// RailCorp 2014
package au.gov.nsw.railcorp.rttarefdata.repositories;

import au.gov.nsw.railcorp.rttarefdata.domain.RuningTime;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by arash on 3/11/14.
 */
public interface RuningTimeRepository extends GraphRepository<RuningTime> {
}
