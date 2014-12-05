// RailCorp 2014
package au.gov.nsw.railcorp.rttarefdata.repositories;

import au.gov.nsw.railcorp.rttarefdata.domain.Platform;
import au.gov.nsw.railcorp.rttarefdata.mapresult.IStationPlatformData;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.List;

/**
 * Created by arash on 29/10/14.
 */
public interface PlatformRepository extends GraphRepository<Platform> {
    /**
     * Return all platforms.
     * @return IStationPlatformData
     */
    @Query(
            "MATCH (s:Station)-[:STATION_PLATFORM]->(p:Platform) RETURN s.stationId AS stationId "
                    + ",s.gtfsStopId AS stationStopId, s.shortName AS stationShortName, p.name AS platformName "
                    + ", p.longName AS platformLongName , p.platformNo AS platformNo, p.gtfsStopId AS platformStopId, p.latitude As latitude"
                    + ", p.longitude As longtitude"
    )
    List<IStationPlatformData> getAllPlatforms();

}
