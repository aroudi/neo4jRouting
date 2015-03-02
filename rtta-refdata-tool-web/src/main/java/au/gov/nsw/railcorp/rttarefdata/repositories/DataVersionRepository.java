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

}

