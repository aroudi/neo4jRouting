// RailCorp 2013
package au.gov.nsw.railcorp.rttarefdata.repositories;

import au.gov.nsw.railcorp.rttarefdata.domain.LinePath;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by arash on 29/10/14.
 */
public interface LinePathRepository extends GraphRepository<LinePath> {
}
