package au.gov.nsw.railcorp.rttarefdata.domain;

import au.gov.nsw.railcorp.rttarefdata.util.IConstants;
import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.*;

/**
 * Created by arash on 6/11/14.
 */
@NodeEntity
public class EntitySequence {
    @GraphId
    private Long id;
    private long stationSeq;
    @Indexed
    private String entityName = IConstants.ENTITY_SEQUENCE_NAME;
    @RelatedTo(type = Links.VERSION_SEQUENCE, direction = Direction.BOTH)
    @Fetch
    private DataVersion version;

    /**
     * defulat constructor.
     */
    public EntitySequence() {
    }

    public long getStationSeq() {
        return stationSeq;
    }

    public void setStationSeq(long stationSeq) {
        this.stationSeq = stationSeq;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DataVersion getVersion() {
        return version;
    }

    public void setVersion(DataVersion version) {
        this.version = version;
    }
}
