// RailCorp 2014
package au.gov.nsw.railcorp.rttarefdata.domain;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.*;

import java.util.Collection;

/**
 * Created by arash on 3/11/14.
 */
@NodeEntity
public class SpeedBand {
    @GraphId
    private Long speedBandId;

    @Indexed
    private String name;

    @Indexed
    private int id;
    private String description;

    @RelatedTo(type = Links.RUN_TIME_SPEED_BAND, direction = Direction.OUTGOING)
    private Collection<RuningTime> runingTimeCollection;

    @RelatedTo(type = Links.VERSION_SPEEDBAND, direction = Direction.BOTH)
    @Fetch
    private DataVersion version;

    /**
     * Constructor.
     * @param name name
     * @param id id
     * @param description description
     */
    public SpeedBand(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    /**
     * Default Constructor.
     */
    public SpeedBand() {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Collection<RuningTime> getRuningTimeCollection() {
        return runingTimeCollection;
    }

    public void setRuningTimeCollection(Collection<RuningTime> runingTimeCollection) {
        this.runingTimeCollection = runingTimeCollection;
    }

    public DataVersion getVersion() {
        return version;
    }

    public void setVersion(DataVersion version) {
        this.version = version;
    }
}
