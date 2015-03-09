// RailCorp 2015
package au.gov.nsw.railcorp.rttarefdata.repositories;

import au.gov.nsw.railcorp.rttarefdata.domain.DataVersion;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.Date;
import java.util.List;


/**
 * Created by arash on 29/10/14.
 */
public interface DataVersionRepository extends GraphRepository<DataVersion> {
    /**
     * return dataVersion list which commence date is smaller than specific date.
     * @param currentDate currentDate
     * @return List of DataVersion
     */
    @Query("MATCH (version:DataVersion) WHERE version.commenceDate <= {0} RETURN version ORDER BY version.commenceDate DESC ")
    List<DataVersion> getDataVersionWhitPastCommenceDate(Date currentDate);

    /**
     * delete all Bi-Directional Relationships for specific versioin.
     * @param version version;
     */
    @Query("MATCH (version:DataVersion)-[vn]-(n)-[r]-() WHERE r<>vn and NOT n:DataVersion and version.name={0} DELETE r")
    void deleteBiDirectionRelations(String version);

    /**
     * delete all Outgoing Relationships from all nodes for specific versioin.
     * @param version version;
     */
    @Query("MATCH (version:DataVersion)-[vn]-(n)-[r]->() WHERE r<>vn and NOT n:DataVersion and version.name={0} DELETE r")
    void deleteOutDirectionRelations(String version);

    /**
     * delete all Incomming Relationships from all nodes for specific versioin.
     * @param version version;
     */
    @Query("MATCH (version:DataVersion)-[vn]-(n)<-[r]-() WHERE r<>vn and NOT n:DataVersion and version.name={0} DELETE r")
    void deleteInDirectionRelations(String version);

    /**
     * delete all nodes belongs to a specific version.
     * @param version version;
     */
    @Query("MATCH (version:DataVersion)-[vn]-(n) WHERE NOT n:DataVersion and version.name={0} DELETE vn, n")
    void deleteAllNodesPerVersion(String version);
}

