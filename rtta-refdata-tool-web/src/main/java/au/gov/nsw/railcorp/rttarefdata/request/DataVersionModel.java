package au.gov.nsw.railcorp.rttarefdata.request;


/**
 * Created by arash on 26/02/2015.
 */
public class DataVersionModel {
    private Long id;
    private String name;
    private String description;
    private String createDate;
    private String commenceDate;
    private boolean active;
    private String baseVersion;

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

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCommenceDate() {
        return commenceDate;
    }

    public void setCommenceDate(String commenceDate) {
        this.commenceDate = commenceDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getBaseVersion() {
        return baseVersion;
    }

    public void setBaseVersion(String baseVersion) {
        this.baseVersion = baseVersion;
    }
}
