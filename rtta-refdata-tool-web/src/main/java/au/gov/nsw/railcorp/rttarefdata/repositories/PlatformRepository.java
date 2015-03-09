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
     * @param version version
     * @return IStationPlatformData
     */
    @Query(
            "MATCH (version:DataVersion{name:{0}})-[:VERSION_STATION]-(s:Station)-[:STATION_PLATFORM]->(p:Platform) RETURN s.stationId AS stationId "
                    + ",s.gtfsStopId AS stationStopId, s.shortName AS stationShortName, p.name AS platformName "
                    + ", p.longName AS platformLongName , p.platformNo AS platformNo, p.gtfsStopId AS platformStopId, p.latitude As latitude"
                    + ", p.longitude As longtitude"
    )
    List<IStationPlatformData> getAllPlatforms(String version);

    /**
     * Return all platforms of station.
     * @param version version
     * @param stationName stationName
     * @return List of platforms
     */
    @Query(
            "MATCH (version:DataVersion{name:{0}})-[:VERSION_STATION]-(s:Station{shortName:{1}})-[:STATION_PLATFORM]->(p:Platform) RETURN p "
    )
    List<Platform> getAllStationPlatforms(String version, String stationName);
    /**
     * Return platform per name.
     * @param version version
     * @param name name
     * @return platform
     */
    @Query("MATCH (version:DataVersion{name:{0}})-[:VERSION_NODE]-(platform:Platform{name:{1}}) RETURN platform")
    Platform getPlatformPerName(String version, String name);

    /**
     * Return platform per gtfsStopId.
     * @param version version
     * @param gtfsStopId gtfsStopId
     * @return platform
     */
    @Query("MATCH (version:DataVersion{name:{0}})-[:VERSION_NODE]-(platform:Platform{gtfsStopId:{1}}) RETURN platform")
    Platform getPlatformPerGtfsStopId(String version, int gtfsStopId);

    /**
     * delete all Platforms with specific version.
     * @param version version;
     */
    @Query("MATCH (version:DataVersion)-[:VERSION_NODE]-(platform:Platform) WHERE version.name={0} DELETE platform")
    void deletePlatformsPerVersion(String version);

}
