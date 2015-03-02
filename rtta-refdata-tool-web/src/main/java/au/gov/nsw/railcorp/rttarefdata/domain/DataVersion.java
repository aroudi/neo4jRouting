package au.gov.nsw.railcorp.rttarefdata.domain;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.*;

import java.util.Date;

/**
 * Created by arash on 26/02/2015.
 */
@NodeEntity
public class DataVersion {
    @GraphId
    private Long id;
    @Indexed(unique = true)
    private String name;
    private String description;
    private Date createDate;
    @Indexed
    private Date commenceDate;
    private boolean active;

    @RelatedTo(type = Links.BASE_VERSION, direction = Direction.OUTGOING)
    @Fetch
    private DataVersion baseVersion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getCommenceDate() {
        return commenceDate;
    }

    public void setCommenceDate(Date commenceDate) {
        this.commenceDate = commenceDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public DataVersion getBaseVersion() {
        return baseVersion;
    }

    public void setBaseVersion(DataVersion baseVersion) {
        this.baseVersion = baseVersion;
    }
}
