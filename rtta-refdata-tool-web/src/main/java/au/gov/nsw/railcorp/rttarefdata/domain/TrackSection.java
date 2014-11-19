// RailCorp 2014
package au.gov.nsw.railcorp.rttarefdata.domain;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

import java.util.Collection;

/**
 * Created by arash on 3/11/14.
 */
@NodeEntity
public class TrackSection {
    @GraphId
    private Long trackSectionId;

    @Indexed
    private String name;

    @Indexed
    private int id;
    private boolean upDirection;
    private boolean permissive;

    @RelatedTo(type = Links.NODE_LINK_TRACK_SECTION, direction = Direction.BOTH)
    private Collection<NodeLink> nodeLinks;

    /**
     * Constructor.
     * @param id id
     * @param name name
     * @param upDirection upDirection
     * @param permissive permissive
     */
    public TrackSection(int id, String name, boolean upDirection, boolean permissive) {
        this.id = id;
        this.name = name;
        this.upDirection = upDirection;
        this.permissive = permissive;
    }

    /**
     * Default constructor.
     */
    public TrackSection() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isUpDirection() {
        return upDirection;
    }

    public void setUpDirection(boolean upDirection) {
        this.upDirection = upDirection;
    }

    public boolean isPermissive() {
        return permissive;
    }

    public void setPermissive(boolean permissive) {
        this.permissive = permissive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Collection<NodeLink> getNodeLinks() {
        return nodeLinks;
    }

    public void setNodeLinks(Collection<NodeLink> nodeLinks) {
        this.nodeLinks = nodeLinks;
    }
}
