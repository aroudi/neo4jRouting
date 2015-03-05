// RailCorp 2014
package au.gov.nsw.railcorp.rttarefdata.repositories;

import au.gov.nsw.railcorp.rttarefdata.domain.NodalHeader;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.List;

/**
 * Created by arash on 14/01/2015.
 */
public interface NodalHeaderRepository extends GraphRepository<NodalHeader> {
    /**
     * delete all NodalHeader with specific version.
     * @param version version;
     */
    @Query("MATCH (version:DataVersion)-[:VERSION_NODAL_HEADER]-(nh:NodalHeader) WHERE version.name={0} DELETE nh")
    void deleteNodalHeaderPerVersion(String version);

    /**
     * find all NodalHeader with specific version.
     * @param version version;
     * @return List of NodalHeader
     */
    @Query("MATCH (version:DataVersion)-[:VERSION_NODAL_HEADER]-(nh:NodalHeader) WHERE version.name={0} RETURN nh")
    List<NodalHeader> getAllNodalHeaderPerVersion(String version);
}
