package au.gov.nsw.railcorp.rttarefdata.domain;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.*;

/**
 * Created by arash on 20/01/2015.
 */

@NodeEntity
public class Location {

    /**
     * graph Id.
     */
    @GraphId
    private Long id;

    @Indexed
    //(unique = true)
    private String name;
    private String systemName;
    private double longtitude;
    private double latitude;
    private int excludeFringe;
    @RelatedTo(type = Links.NODE_LOCATION, direction = Direction.BOTH)
    private Node node;
    private String nodeName;

    @RelatedTo(type = Links.VERSION_LOCATION, direction = Direction.BOTH)
    @Fetch
    @JsonIgnore
    private DataVersion version;

    /**
     * Default constructor.
     */
    public Location() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public int getExcludeFringe() {
        return excludeFringe;
    }

    public void setExcludeFringe(int excludeFringe) {
        this.excludeFringe = excludeFringe;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
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
