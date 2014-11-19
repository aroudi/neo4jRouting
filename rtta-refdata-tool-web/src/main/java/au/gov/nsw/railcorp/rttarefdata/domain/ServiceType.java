// RailCorp 2014
package au.gov.nsw.railcorp.rttarefdata.domain;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

import java.util.Set;

/**
 * Created by arash on 27/10/14.
 */

@NodeEntity
public class ServiceType {

    @GraphId
    private Long serviceTypeId;

    @Indexed(unique = true)
    private String name;
    private String description;

    @RelatedTo (type = Links.LINE_SERVICE_TYPE, direction = Direction.OUTGOING)
    private Set<NetworkLine> networkLines;

    /**
     * Constructor.
     * @param name service type name
     * @param description service type desc
     */
    public ServiceType(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Default Constructor.
     */
    public ServiceType() {

    }

    public Long getServiceTypeId() {
        return serviceTypeId;
    }

    public void setServiceTypeId(Long serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
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

    public Set<NetworkLine> getNetworkLines() {
        return networkLines;
    }

    public void setNetworkLines(Set<NetworkLine> networkLines) {
        this.networkLines = networkLines;
    }
}
