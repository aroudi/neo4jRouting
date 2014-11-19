// RailCorp 2014
package au.gov.nsw.railcorp.rttarefdata.domain;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by arash on 27/10/14.
 */
@NodeEntity
public class LinePath {
    @GraphId
    private Long linePathId;

    @Indexed
    private String name;
    private String longName;

    @RelatedTo (type = Links.LINE_PATH, direction = Direction.INCOMING)
    private NetworkLine networkLine;

    @RelatedTo (type = Links.LINE_PATH_POWER, direction = Direction.BOTH)
    private Set<PowerType> powerTypes;

    @RelatedTo (type = Links.LINE_PATH_STATION, direction = Direction.OUTGOING)
    private Set<Station> stations;

    @RelatedToVia (type = Links.LINE_PATH_STATION, direction = Direction.OUTGOING)
    private Collection<PathStation> pathStations;

    /**
     * Constructor.
     * @param name name
     * @param longName longName
     */
    public LinePath(String name, String longName) {
        this.name = name;
        this.longName = longName;
    }

    /**
     * Default constructor.
     */
    public LinePath() {
    }

    /**
     * add station to path.
     * @param station station
     * @param sequence sequence
     * @param pathMatchInclude pathMatchInclude
     * @return pathStation
     */
    public PathStation addStationToPath (Station station, int sequence, boolean pathMatchInclude) {
        final PathStation pathStation = new PathStation(this, station, sequence, pathMatchInclude);
        if (pathStations == null) {
            pathStations = new HashSet<PathStation>();
        }
        pathStations.add(pathStation);
        return pathStation;
    }

    /**
     * add power type to line.
     * @param powerType powerType
     * @return powerType powerType
     */
    public PowerType addPowerType (PowerType powerType) {
        if (powerTypes == null) {
            powerTypes = new HashSet<PowerType>();
        }
        powerTypes.add(powerType);
        return powerType;
    }

    public Long getLinePathId() {
        return linePathId;
    }

    public void setLinePathId(Long linePathId) {
        this.linePathId = linePathId;
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

    public NetworkLine getNetworkLine() {
        return networkLine;
    }

    public void setNetworkLine(NetworkLine networkLine) {
        this.networkLine = networkLine;
    }

    public Set<Station> getStations() {
        return stations;
    }

    public void setStations(Set<Station> stations) {
        this.stations = stations;
    }

    public Iterable<PathStation> getPathStations() {
        return pathStations;
    }

    public Set<PowerType> getPowerTypes() {
        return powerTypes;
    }

    public void setPowerTypes(Set<PowerType> powerTypes) {
        this.powerTypes = powerTypes;
    }
}
