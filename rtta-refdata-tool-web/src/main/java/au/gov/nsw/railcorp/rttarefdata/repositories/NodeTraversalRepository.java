package au.gov.nsw.railcorp.rttarefdata.repositories;

import au.gov.nsw.railcorp.rttarefdata.domain.Node;
import org.springframework.data.neo4j.repository.TraversalRepository;

/**
 * Created by arash on 6/02/2015.
 */
public interface NodeTraversalRepository extends TraversalRepository<Node> {
}
