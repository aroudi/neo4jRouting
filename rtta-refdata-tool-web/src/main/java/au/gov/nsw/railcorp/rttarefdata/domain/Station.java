// RailCorp 2014
package au.gov.nsw.railcorp.rttarefdata.domain;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.*;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import static org.neo4j.graphdb.Direction.OUTGOING;

/**
 * Created by arash on 27/10/14.
 */
@NodeEntity
@XmlRootElement(name = "station")
public class Station {
    @JsonIgnore
    @GraphId
    private Long id;

    @Indexed
    private Long stationId;

    @Indexed
    private String shortName;
    private String longName;

    @Indexed(unique = true)
    private int gtfsStopId;

    private Double latitude;
    private Double longtitude;
    private boolean interchangePoint;


    @JsonIgnore
    @RelatedTo(type = Links.FROM_NODE, direction = OUTGOING)
    private Collection<StationTriplet> outGoingTriplets;

    @JsonIgnore
    @RelatedTo(type = Links.TO_NODE, direction = OUTGOING)
    private Collection<StationTriplet> incomingTriplets;

    @JsonIgnore
    @RelatedTo(type = Links.MAIN_NODE, direction = OUTGOING)
    private Collection<StationTriplet> triplets;

    @JsonIgnore
    @RelatedTo(type = Links.LINE_PATH_STATION, direction = Direction.INCOMING)
    private Set<LinePath> linePaths;

    @JsonIgnore
    @RelatedToVia(type = Links.LINE_PATH_STATION, direction = Direction.INCOMING)
    private Collection<PathStation> pathStations;

    @JsonIgnore
    @RelatedTo(type = Links.STATION_PLATFORM, direction = OUTGOING)
    @Fetch
    private Collection<Platform> platforms;

    @RelatedTo(type = Links.VERSION_STATION, direction = Direction.BOTH)
    @Fetch
    private DataVersion version;

    /**
     * Constructor.
     * @param shortName name
     * @param stationId stationId
     * @param longName description
     * @param gtfsStopId stop Id
     * @param latitude Latt
     * @param longtitude Long
     * @param interchangePoint Is Interchange Point?
     */
    public Station(Long stationId, String shortName, String longName, int gtfsStopId, Double latitude, Double longtitude, boolean interchangePoint) {
        this.stationId = stationId;
        this.shortName = shortName;
        this.longName = longName;
        this.gtfsStopId = gtfsStopId;
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.interchangePoint = interchangePoint;
    }

    /**
     * Default Constructor.
     */
    public Station() {
    }

    /**
     * add outgoing triplet.
     * @param stationTriplet stationTriplet
     */
    public void addOutgoingTriplet(StationTriplet stationTriplet) {
        if (outGoingTriplets == null) {
            outGoingTriplets = new ArrayList<StationTriplet>();
        }
        outGoingTriplets.add(stationTriplet);
    }

    /**
     * add incoming triplet.
     * @param stationTriplet stationTriplet
     */
    public void addIncomingTriplet(StationTriplet stationTriplet) {
        if (incomingTriplets == null) {
            incomingTriplets = new ArrayList<StationTriplet>();
        }
        incomingTriplets.add(stationTriplet);
    }

    /**
     * add triplet.
     * @param stationTriplet stationTriplet
     */
    public void addTriplet(StationTriplet stationTriplet) {
        if (triplets == null) {
            triplets = new ArrayList<StationTriplet>();
        }
        triplets.add(stationTriplet);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStationId() {
        return stationId;
    }

    public void setStationId(Long stationId) {
        this.stationId = stationId;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public int getGtfsStopId() {
        return gtfsStopId;
    }

    public void setGtfsStopId(int gtfsStopId) {
        this.gtfsStopId = gtfsStopId;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(Double longtitude) {
        this.longtitude = longtitude;
    }

    public boolean isInterchangePoint() {
        return interchangePoint;
    }

    public void setInterchangePoint(boolean interchangePoint) {
        this.interchangePoint = interchangePoint;
    }

    public Collection<StationTriplet> getTriplets() {
        return triplets;
    }

    public void setTriplets(Collection<StationTriplet> triplets) {
        this.triplets = triplets;
    }

    public Collection<StationTriplet> getOutGoingTriplets() {
        return outGoingTriplets;
    }

    public void setOutGoingTriplets(Collection<StationTriplet> outGoingTriplets) {
        this.outGoingTriplets = outGoingTriplets;
    }

    public Collection<StationTriplet> getIncomingTriplets() {
        return incomingTriplets;
    }

    public void setIncomingTriplets(Collection<StationTriplet> incomingTriplets) {
        this.incomingTriplets = incomingTriplets;
    }

    public Set<LinePath> getLinePaths() {
        return linePaths;
    }

    public void setLinePaths(Set<LinePath> linePaths) {
        this.linePaths = linePaths;
    }

    public Collection<PathStation> getPathStations() {
        return pathStations;
    }

    public void setPathStations(Collection<PathStation> pathStations) {
        this.pathStations = pathStations;
    }

    public Collection<Platform> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(Collection<Platform> platforms) {
        this.platforms = platforms;
    }

    public DataVersion getVersion() {
        return version;
    }

    public void setVersion(DataVersion version) {
        this.version = version;
    }
}
