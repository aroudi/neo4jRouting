package au.gov.nsw.railcorp.rttarefdata.domain;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.*;

/**
 * Created by arash on 14/01/2015.
 */
@NodeEntity
public class NodalHeader {
    /*
     * GraphId for managing internal id's
     */
    @Indexed
    @GraphId
    private Long id;

    private String description;
    private String owner;
    private String date;

    @RelatedTo(type = Links.VERSION_NODAL_HEADER, direction = Direction.BOTH)
    @Fetch
    private DataVersion version;

    /**
     * Default Constructor.
     */
    public NodalHeader() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public DataVersion getVersion() {
        return version;
    }

    public void setVersion(DataVersion version) {
        this.version = version;
    }
}
