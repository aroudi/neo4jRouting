// RailCorp 2014
package au.gov.nsw.railcorp.rttarefdata.domain;

import org.springframework.data.neo4j.annotation.*;

/**
 * Created by arash on 29/10/14.
 */
@RelationshipEntity(type = Links.LINE_PATH_STATION)
public class PathStation {
    @GraphId
    private Long pathStationId;
    @StartNode
    private LinePath linePath;
    @EndNode
    private Station station;

    private int sequence;
    private boolean pathMatchInclude;
    @Indexed
    private String version;

    /**
     * Constructor.
     * @param linePath linePath
     * @param station station
     * @param sequence sequence
     * @param pathMatchInclude pathMatchInclude
     */
    public PathStation(LinePath linePath, Station station, int sequence, boolean pathMatchInclude) {
        this.linePath = linePath;
        this.station = station;
        this.sequence = sequence;
        this.pathMatchInclude = pathMatchInclude;
    }

    /**
     * default constructor.
     */
    public PathStation() {
    }

    public Long getPathStationId() {
        return pathStationId;
    }

    public void setPathStationId(Long pathStationId) {
        this.pathStationId = pathStationId;
    }

    public LinePath getLinePath() {
        return linePath;
    }

    public void setLinePath(LinePath linePath) {
        this.linePath = linePath;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public boolean isPathMatchInclude() {
        return pathMatchInclude;
    }

    public void setPathMatchInclude(boolean pathMatchInclude) {
        this.pathMatchInclude = pathMatchInclude;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
