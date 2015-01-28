package au.gov.nsw.railcorp.rttarefdata.domain;

import au.gov.nsw.railcorp.rttarefdata.util.IConstants;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;

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
}
