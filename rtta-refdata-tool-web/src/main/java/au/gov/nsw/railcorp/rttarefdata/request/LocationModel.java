package au.gov.nsw.railcorp.rttarefdata.request;

import au.gov.nsw.railcorp.rttarefdata.domain.Node;
import org.codehaus.jackson.annotate.JsonIgnore;


/**
 * Created by arash on 21/01/2015.
 */
public class LocationModel {
    private Long id;
    private String name;
    private String systemName;
    private double longtitude;
    private double latitude;
    private int excludeFringe;
    private String nodeName;

    @JsonIgnore
    private Node node;

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

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }
}
