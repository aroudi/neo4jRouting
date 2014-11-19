// RailCorp 2014
package au.gov.nsw.railcorp.rttarefdata.repositories;

import au.gov.nsw.railcorp.rttarefdata.domain.StationTriplet;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by arash on 29/10/14.
 */
public interface StationTripletRepository extends GraphRepository<StationTriplet> {


    /**
     * return triplet based on station nodes.
     * @param fromGtfsStopId fromGtfsStopId
     * @param gtfsStopId gtfsStopId
     * @param toGtfsStopId toGtfsStopId
     * @return StationTriplet
     */
    @Query("MATCH (mainNode:Station{gtfsStopId:{1}})-[:MAIN_NODE]->(triplet)<-[:FROM_NODE]-(fromNode:Station{gtfsStopId:{0}}) "
            + "WITH mainNode,fromNode,triplet "
                + "MATCH (toNode:Station{gtfsStopId:{2}})-[:TO_NODE]->(triplet) "
            + "RETURN triplet")
    StationTriplet  getTriplet(int fromGtfsStopId, int gtfsStopId, int toGtfsStopId);

    /**
     * return triplet based on links.
     * @param fromGtfsStopId fromGtfsStopId
     * @param gtfsStopId gtfsStopId
     * @param toGtfsStopId toGtfsStopId
     * @return StationTriplet
     */
    StationTriplet findByFromStationGtfsStopIdAndStationGtfsStopIdAndToStationGtfsStopId(int fromGtfsStopId, int gtfsStopId, int toGtfsStopId);
}
