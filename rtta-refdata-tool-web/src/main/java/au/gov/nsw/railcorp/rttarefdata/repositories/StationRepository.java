// RailCorp 2014
package au.gov.nsw.railcorp.rttarefdata.repositories;

import au.gov.nsw.railcorp.rttarefdata.domain.Station;
import au.gov.nsw.railcorp.rttarefdata.mapresult.IRefData;
import au.gov.nsw.railcorp.rttarefdata.mapresult.IStationData;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.List;

/**
 * Created by arash on 29/10/14.
 */
public interface StationRepository extends GraphRepository<Station> {
    /**
     * Return stations as reference data.
     * @return IRefData
     */
    @Query(
            "MATCH (s:Station) RETURN s.stationId AS refDataId ,s.shortName AS refDataCode, s.longName AS refDataName"
    )
    List<IRefData> getStationsAsRefData();
    /**
     * Return stations as reference data.
     * @return IRefData
     */
    @Query(
            "MATCH (s:Station) RETURN s.stationId AS stationId ,s.shortName AS shortName, s.longName AS longName, "
                    + "s.gtfsStopId AS gtfsStopId, s.latitude as latitude, s.longtitude as longtitude, s.interchangePoint As interchangePoint "
    )
    List<IStationData> getAllStations();
}
