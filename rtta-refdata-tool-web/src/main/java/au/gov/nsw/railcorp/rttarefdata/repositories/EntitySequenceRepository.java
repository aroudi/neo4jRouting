package au.gov.nsw.railcorp.rttarefdata.repositories;

import au.gov.nsw.railcorp.rttarefdata.domain.EntitySequence;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by arash on 6/11/14.
 */
public interface EntitySequenceRepository extends GraphRepository<EntitySequence> {
}
