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
     * @param version version
     * @return IRefData
     */
    @Query(
            "MATCH (version:DataVersion{name:{0}})-[:VERSION_STATION]-(s:Station) RETURN s.stationId AS refDataId ,s.shortName AS refDataCode, s.longName AS refDataName"
    )
    List<IRefData> getStationsAsRefData(String version);
    /**
     * Return stations as reference data.
     * @param version version
     * @return IRefData
     */
    @Query(
            "MATCH (version:DataVersion{name:{0}})-[:VERSION_STATION]-(s:Station) RETURN s.stationId AS stationId ,s.shortName AS shortName, s.longName AS longName, "
                    + "s.gtfsStopId AS gtfsStopId, s.latitude as latitude, s.longtitude as longtitude, s.interchangePoint As interchangePoint "
    )
    List<IStationData> getAllStations(String version);

    /**
     * Return station per name.
     * @param version version
     * @param name name
     * @return station
     */
    @Query("MATCH (version:DataVersion{name:{0}})-[:VERSION_STATION]-(station:Station{shortName:{1}}) RETURN station")
    Station getStationPerName(String version, String name);
    /**
     * Return station per gtfsStopId.
     * @param version version
     * @param gtfsStopId gtfsStopId
     * @return station
     */
    @Query("MATCH (version:DataVersion{name:{0}})-[:VERSION_STATION]-(station:Station{gtfsStopId:{1}}) RETURN station")
    Station getStationPerGtfsStopId(String version, int gtfsStopId);

    /**
     * Return station per gtfsStopId.
     * @param version version
     * @param id id
     * @return station
     */
    @Query("MATCH (version:DataVersion{name:{0}})-[:VERSION_STATION]-(station:Station{stationId:{1}}) RETURN station")
    Station getStationPerStationId(String version, long id);
    /**
     * Return stations as reference data.
     * @param version version
     * @return Stations
     */
    @Query(
            "MATCH (version:DataVersion{name:{0}})-[:VERSION_STATION]-(s:Station) RETURN s "
    )
    List<Station> getAllStationsPerVersion(String version);
}
