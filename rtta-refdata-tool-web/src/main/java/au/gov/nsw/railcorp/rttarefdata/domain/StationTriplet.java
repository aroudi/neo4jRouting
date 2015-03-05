// RailCorp 2014
package au.gov.nsw.railcorp.rttarefdata.domain;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by arash on 28/10/14.
 */
@NodeEntity
public class StationTriplet {
    @GraphId
    private Long stationTripletId;

    private boolean isReversible;


    @Fetch
    @RelatedTo (type = Links.FROM_NODE, direction = Direction.INCOMING)
    private Station fromStation;

    @Fetch
    @RelatedTo (type = Links.TO_NODE, direction = Direction.INCOMING)
    private Station toStation;

    @Fetch
    @RelatedTo (type = Links.MAIN_NODE, direction = Direction.INCOMING)
    private Station station;

    @RelatedTo (type = Links.STATION_LINK_POWER, direction = Direction.BOTH)
    private Set<PowerType> powerTypes;

    @RelatedTo(type = Links.VERSION_TRIPLET, direction = Direction.BOTH)
    @Fetch
    private DataVersion version;

    /**
     * Constuctor.
     * @param fromStation fromStation
     * @param toStation toStation
     * @param isReversible isReversible
     * @param powerTypes powerType
     * @param station station
     */
    public StationTriplet(Station fromStation, Station toStation, boolean isReversible, Set<PowerType> powerTypes, Station station) {
        this.fromStation = fromStation;
        this.toStation = toStation;
        this.isReversible = isReversible;
        this.powerTypes = powerTypes;
        this.station = station;
    }

    /**
     *  Default Constructor.
     */
    public StationTriplet() {
    }

    /**
     * add powertype to this triplet.
     * @param powerType power type
     */
    public void addPowerType (PowerType powerType) {
        if (powerTypes == null) {
            powerTypes = new HashSet<PowerType>();
        }
        powerTypes.add(powerType);
    }
    public Long getStationTripletId() {
        return stationTripletId;
    }

    public void setStationTripletId(Long stationTripletId) {
        this.stationTripletId = stationTripletId;
    }

    public Station getFromStation() {
        return fromStation;
    }

    public void setFromStation(Station fromStation) {
        this.fromStation = fromStation;
    }

    public Station getToStation() {
        return toStation;
    }

    public void setToStation(Station toStation) {
        this.toStation = toStation;
    }

    public boolean isReversible() {
        return isReversible;
    }

    public void setReversible(boolean isReversible) {
        this.isReversible = isReversible;
    }

    public Set<PowerType> getPowerTypes() {
        return powerTypes;
    }

    public void setPowerTypes(Set<PowerType> powerTypes) {
        this.powerTypes = powerTypes;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public DataVersion getVersion() {
        return version;
    }

    public void setVersion(DataVersion version) {
        this.version = version;
    }
}
