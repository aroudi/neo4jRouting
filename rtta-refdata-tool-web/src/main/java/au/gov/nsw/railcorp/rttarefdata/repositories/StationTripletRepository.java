// RailCorp 2014
package au.gov.nsw.railcorp.rttarefdata.repositories;

import au.gov.nsw.railcorp.rttarefdata.domain.PowerType;
import au.gov.nsw.railcorp.rttarefdata.domain.StationTriplet;
import au.gov.nsw.railcorp.rttarefdata.mapresult.ITriplet;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.List;

/**
 * Created by arash on 29/10/14.
 */
public interface StationTripletRepository extends GraphRepository<StationTriplet> {


    /**
     * return triplet based on station nodes.
     * @param version version
     * @param fromGtfsStopId fromGtfsStopId
     * @param gtfsStopId gtfsStopId
     * @param toGtfsStopId toGtfsStopId
     * @return StationTriplet
     */
    @Query("MATCH (version:DataVersion{name:{0}})-[:VERSION_STATION]-(mainNode:Station{gtfsStopId:{2}})-[:MAIN_NODE]->(triplet)<-[:FROM_NODE]-(fromNode:Station{gtfsStopId:{1}}) "
            + "WITH mainNode,fromNode,triplet "
                + "MATCH (toNode:Station{gtfsStopId:{3}})-[:TO_NODE]->(triplet) "
            + "RETURN triplet")
    StationTriplet  getTriplet(String version, int fromGtfsStopId, int gtfsStopId, int toGtfsStopId);


    /**
     * return powertype for triplet.
     * @param version version
     * @param fromGtfsStopId fromGtfsStopId
     * @param gtfsStopId gtfsStopId
     * @param toGtfsStopId toGtfsStopId
     * @return StationTriplet
     */
    @Query("MATCH (version:DataVersion{name:{0}})-[:VERSION_STATION]-(mainNode:Station{gtfsStopId:{2}})-[:MAIN_NODE]->(triplet)<-[:FROM_NODE]-(fromNode:Station{gtfsStopId:{1}}) "
            + "WITH mainNode,fromNode,triplet "
            + "MATCH (toNode:Station{gtfsStopId:{3}})-[:TO_NODE]->(triplet)-[:STATION_LINK_POWER]->(powerType:PowerType) "
            + "RETURN powerType")
    List<PowerType> getTripletPowerTypes(String version, int fromGtfsStopId, int gtfsStopId, int toGtfsStopId);


    /**
     * return triplet based on links.
     * @param fromGtfsStopId fromGtfsStopId
     * @param gtfsStopId gtfsStopId
     * @param toGtfsStopId toGtfsStopId
     * @return StationTriplet
     */
    StationTriplet findByFromStationGtfsStopIdAndStationGtfsStopIdAndToStationGtfsStopId(int fromGtfsStopId, int gtfsStopId, int toGtfsStopId);

    /**
     * get all station triplets.
     * @param version version
     * @return List of station triplet
     */
    @Query("MATCH (version:DataVersion{name:{0}})-[:VERSION_STATION]-(mainNode:Station)-[:MAIN_NODE]->(triplet)<-[:FROM_NODE]-(fromNode:Station) "
            + "WITH mainNode,fromNode,triplet MATCH (toNode:Station)-[:TO_NODE]->(triplet) "
            + "RETURN fromNode.gtfsStopId as inStopId, mainNode.gtfsStopId as stopId, toNode.gtfsStopId as outStopId, "
            + "fromNode.shortName as inStopName, mainNode.shortName as stopName, toNode.shortName as outStopName, triplet.isReversible as reversible"
    )
    List<ITriplet> getAllTriplets(String version);

    /**
     * delete all Station Triplets with specific version.
     * @param version version;
     */
    @Query("MATCH (version:DataVersion)-[:VERSION_TRIPLET]-(st:StationTriplet) WHERE version.name={0} DELETE st")
    void deleteStationTripletsPerVersion(String version);
}
