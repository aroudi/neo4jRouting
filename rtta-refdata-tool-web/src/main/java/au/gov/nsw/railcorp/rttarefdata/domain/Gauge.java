// RailCorp 2014
package au.gov.nsw.railcorp.rttarefdata.domain;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

import java.util.Collection;

/**
 * Created by arash on 4/11/14.
 */
@NodeEntity
public class Gauge {
    @GraphId
    private Long gaugeId;

    @Indexed
    private String name;
    private String description;

    /**
     * relate to node link.
     */
    @RelatedTo(type = Links.NODE_LINK_GAUGE, direction = Direction.BOTH)
    private Collection<NodeLink> nodeLinks;

    /**
     * Constructor.
     * @param name name
     * @param description description
     */
    public Gauge(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Default Constructor.
     */
    public Gauge() {
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

    public Collection<NodeLink> getNodeLinks() {
        return nodeLinks;
    }

    public void setNodeLinks(Collection<NodeLink> nodeLinks) {
        this.nodeLinks = nodeLinks;
    }
}
