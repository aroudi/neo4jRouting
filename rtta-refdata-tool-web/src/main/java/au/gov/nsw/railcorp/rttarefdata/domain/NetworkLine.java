// RailCorp 2014
package au.gov.nsw.railcorp.rttarefdata.domain;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.*;

import java.util.Set;


/**
 * Created by arash on 27/10/14.
 */

@NodeEntity
public class NetworkLine {

    @GraphId private Long networkLineId;

    @Indexed
    //(unique = true)
    private String name;
    private String longName;
    private String backgroundColourHex;
    private String textColourHex;

    @RelatedTo(type = Links.NETWORK_LINE, direction = Direction.INCOMING)
    //@Fetch
    private Network network;

    @RelatedTo (type = Links.LINE_SERVICE_TYPE, direction = Direction.INCOMING)
    private ServiceType serviceType;

    @RelatedTo (type = Links.LINE_PATH, direction = Direction.OUTGOING)
    private Set<LinePath> linePath;

    @RelatedTo(type = Links.VERSION_LINE, direction = Direction.BOTH)
    @Fetch
    private DataVersion version;



    /**
     * Constructor.
     * @param name name
     * @param longName long name
     * @param backgroundColourHex background colour
     * @param textColourHex textColourHex
     */
    public NetworkLine(String name, String longName, String backgroundColourHex, String textColourHex) {
        this.name = name;
        this.longName = longName;
        this.backgroundColourHex = backgroundColourHex;
        this.textColourHex = textColourHex;
    }

    /**
     * Default constructor.
     */
    public NetworkLine() {
    }

    public Long getNetworkLineId() {
        return networkLineId;
    }

    public void setNetworkLineId(Long networkLineId) {
        this.networkLineId = networkLineId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public String getBackgroundColourHex() {
        return backgroundColourHex;
    }

    public void setBackgroundColourHex(String backgroundColourHex) {
        this.backgroundColourHex = backgroundColourHex;
    }

    public String getTextColourHex() {
        return textColourHex;
    }

    public void setTextColourHex(String textColourHex) {
        this.textColourHex = textColourHex;
    }

    public Network getNetwork() {
        return network;
    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public Set<LinePath> getLinePath() {
        return linePath;
    }

    public void setLinePath(Set<LinePath> linePath) {
        this.linePath = linePath;
    }

    public DataVersion getVersion() {
        return version;
    }

    public void setVersion(DataVersion version) {
        this.version = version;
    }
}
