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
     * @return List of trackSections
     */
    @Query("MATCH (trackSection:TrackSection) RETURN trackSection")
    List<TrackSection> getAllTrackSections();

}
