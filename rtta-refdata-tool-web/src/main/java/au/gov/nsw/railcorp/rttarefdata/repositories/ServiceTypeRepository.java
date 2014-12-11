// RailCorp 2013
package au.gov.nsw.railcorp.rttarefdata.repositories;

import au.gov.nsw.railcorp.rttarefdata.domain.ServiceType;
import au.gov.nsw.railcorp.rttarefdata.mapresult.IRefData;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.List;

/**
 * Created by arash on 29/10/14.
 */
public interface ServiceTypeRepository extends GraphRepository<ServiceType> {
    /**
     * return service type as ref data.
     * @return RefData.
     */
    @Query("MATCH (st:ServiceType) RETURN id(st) AS refDataId, st.name AS refDataCode, st.description AS refDataName"
    )
    List<IRefData> getServiceTypesAsRefData();

}
