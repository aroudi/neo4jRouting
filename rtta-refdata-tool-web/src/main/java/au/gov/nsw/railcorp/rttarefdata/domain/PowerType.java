// RailCorp 2014
package au.gov.nsw.railcorp.rttarefdata.domain;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

import java.util.Set;

/**
 * Created by arash on 28/10/14.
 */
@NodeEntity
public class PowerType {
    @GraphId
    private Long powerTypeId;

    @Indexed(unique = true)
    private String name;
    private String description;

    @RelatedTo (type = Links.LINE_PATH_POWER, direction = Direction.BOTH)
    private Set<LinePath> linePaths;

    @RelatedTo (type = Links.STATION_LINK_POWER, direction = Direction.BOTH)
    private Set<LinePath> stationTriplets;

    /**
     * Constructor.
     * @param name name
     * @param description description
     */
    public PowerType(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Default Constructor.
     */
    public PowerType() {

    }
    public Long getPowerTypeId() {
        return powerTypeId;
    }

    public void setPowerTypeId(Long powerTypeId) {
        this.powerTypeId = powerTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<LinePath> getLinePaths() {
        return linePaths;
    }

    public void setLinePaths(Set<LinePath> linePaths) {
        this.linePaths = linePaths;
    }

    public Set<LinePath> getStationTriplets() {
        return stationTriplets;
    }

    public void setStationTriplets(Set<LinePath> stationTriplets) {
        this.stationTriplets = stationTriplets;
    }
}
