// RailCorp 2014
package au.gov.nsw.railcorp.rttarefdata.repositories;

import au.gov.nsw.railcorp.rttarefdata.domain.Gauge;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by arash on 4/11/14.
 */
public interface GaugeRepository extends GraphRepository<Gauge> {
}
