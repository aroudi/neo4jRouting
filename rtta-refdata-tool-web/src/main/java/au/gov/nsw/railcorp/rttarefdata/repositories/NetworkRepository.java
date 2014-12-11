// RailCorp 2014
package au.gov.nsw.railcorp.rttarefdata.repositories;

import au.gov.nsw.railcorp.rttarefdata.domain.Network;
import au.gov.nsw.railcorp.rttarefdata.mapresult.IRefData;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.List;

/**
 * Created by arash on 29/10/14.
 */
public interface NetworkRepository extends GraphRepository<Network> {
    /**
     * return network list as ref data.
     * @return List of refdata
     */
    @Query("MATCH (network:Network) RETURN id (network) AS refDataId ,network.name AS refDataCode, network.description AS refDataName"
    )
    List<IRefData> getNetworksAsRefData();

}

