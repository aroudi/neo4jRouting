// RailCorp 2014
package au.gov.nsw.railcorp.rttarefdata.repositories;

import au.gov.nsw.railcorp.rttarefdata.domain.TrackSection;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.List;

/**
 * Created by arash on 3/11/14.
 */
public interface TrackSectionRepository extends GraphRepository<TrackSection> {
    /**
     * return all track sections.
     * @param version version
     * @return List of trackSections
     */
    @Query("MATCH (version:DataVersion{name:{0}})-[:VERSION_TRACK_SECTION]-(trackSection:TrackSection) RETURN trackSection")
    List<TrackSection> getAllTrackSections(String version);

    /**
     * delete all TrackSections with specific version.
     * @param version version;
     */
    @Query("MATCH (version:DataVersion)-[:VERSION_TRACK_SECTION]-(ts:TrackSection) WHERE version.name={0} DELETE ts")
    void deleteTrackSectionPerVersion(String version);

    /**
     * Return tracksection per id.
     * @param version version
     * @param id id
     * @return trackSection
     */
    @Query("MATCH (version:DataVersion{name:{0}})-[:VERSION_TRACK_SECTION]-(ts:TrackSection{id:{1}}) RETURN ts")
    TrackSection getTrackSectionPerId(String version, String id);

}
