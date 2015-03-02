// RailCorp 2014
package au.gov.nsw.railcorp.rttarefdata.domain;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.*;

import java.util.Set;

/**
 * Created by arash on 27/10/14.
 */
@NodeEntity
public class Network {

    /*
     * GraphId for managing internal id's
     */
    @Indexed
    @GraphId private Long networkId;

    @Indexed(unique = true)
    private String name;
    private String description;
    private String url;
    private String lang;
    private String phone;
    private String fareUrl;
    private String timeZone;


    @JsonIgnore
    @RelatedTo (type = Links.NETWORK_LINE, direction = Direction.OUTGOING)
    //@Fetch
    private Set<NetworkLine> networkLines;

    @JsonIgnore
    @RelatedTo(type = Links.VERSION_NETWORK, direction = Direction.BOTH)
    @Fetch
    private DataVersion version;

    /**
     * Constructor.
     * @param name name
     * @param description description
     * @param url url
     * @param lang lang
     * @param phone phone
     * @param fareUrl fareUrl
     * @param timeZone timeZone
     */
    public Network(String name, String description, String url, String lang, String phone, String fareUrl, String timeZone) {
        this.name = name;
        this.description = description;
        this.url = url;
        this.lang = lang;
        this.phone = phone;
        this.fareUrl = fareUrl;
        this.timeZone = timeZone;
    }

    /**
     * Defaulat Constructor.
     */
    public Network() {
    }

    public Long getNetworkId() {
        return networkId;
    }

    public void setNetworkId(Long networkId) {
        this.networkId = networkId;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFareUrl() {
        return fareUrl;
    }

    public void setFareUrl(String fareUrl) {
        this.fareUrl = fareUrl;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }
    public Set<NetworkLine> getNetworkLines() {
        return networkLines;
    }

    public void setNetworkLines(Set<NetworkLine> networkLines) {
        this.networkLines = networkLines;
    }

    public DataVersion getVersion() {
        return version;
    }

    public void setVersion(DataVersion version) {
        this.version = version;
    }
}
