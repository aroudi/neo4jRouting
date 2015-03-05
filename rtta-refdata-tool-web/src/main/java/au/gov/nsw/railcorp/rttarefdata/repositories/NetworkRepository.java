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
     * @param version version
     * @return List of refdata
     */
    @Query("MATCH (version:DataVersion{name:{0}})-[:VERSION_NETWORK]-(network:Network) RETURN id (network) AS refDataId ,network.name AS refDataCode, network.description AS refDataName"
    )
    List<IRefData> getNetworksAsRefData(String version);
    /**
     * get network per name.
     * @param version version
     * @param name name
     * @return Network
     */
    @Query("MATCH (version:DataVersion{name:{0}})-[:VERSION_NETWORK]-(net:Network{name:{1}}) RETURN net")
    Network getNetworkPerName(String version, String name);

    /**
     * get all networks per version.
     * @param version version
     * @return List of Networks
     */
    @Query("MATCH (version:DataVersion{name:{0}})-[:VERSION_NETWORK]-(net:Network) RETURN net")
    List<Network> getAllNetworkPerVersion(String version);

}

