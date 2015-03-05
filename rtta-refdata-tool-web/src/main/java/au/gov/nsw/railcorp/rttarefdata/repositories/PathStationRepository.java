// RailCorp 2014
package au.gov.nsw.railcorp.rttarefdata.repositories;

import au.gov.nsw.railcorp.rttarefdata.domain.PathStation;
import au.gov.nsw.railcorp.rttarefdata.mapresult.ILinePathStation;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.List;

/**
 * Created by arash on 29/10/14.
 */
public interface PathStationRepository extends GraphRepository<PathStation> {
    /**
     * get all line path station.
     * @param linePathName linePathName
     * @param version version
     * @return ILinePath
     */
    @Query("MATCH (version:DataVersion{name:{0}})-[:VERSION_PATH]-(linePath:LinePath)-[lps:LINE_PATH_STATION]->(station:Station) where linePath.name={1} RETURN station.stationId as stationId,"
            + " station.gtfsStopId as gtfsStopId, station.shortName as name, station.longName as longName, station.latitude AS latitude,"
            + " station.longtitude AS longtitude, station.interchangePoint as interchangePoint, "
            + "lps.pathMatchInclude AS pathMatchInclude,  lps.sequence AS sequence order by sequence"
    )
    List<ILinePathStation> getAllLinePathStations(String version, String linePathName);

}
