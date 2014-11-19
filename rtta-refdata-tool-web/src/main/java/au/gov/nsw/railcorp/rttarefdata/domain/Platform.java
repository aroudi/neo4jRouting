// RailCorp 2014

package au.gov.nsw.railcorp.rttarefdata.domain;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

/**
 * Created by arash on 28/10/14.
 */

@NodeEntity
public class Platform extends Node {

    private int platformNo;
    @Indexed
    private int gtfsStopId;

    @RelatedTo(type = Links.STATION_PLATFORM, direction = Direction.INCOMING)
    private Station station;

    /**
     * Constructor.
     * @param name name
     * @param longName longName
     * @param platfromName platformName
     * @param platformNo platformNo
     * @param gtfsStopId gtfsStopId
     */
    public Platform(String name, String longName, String platfromName, int platformNo, int gtfsStopId) {
        super(name, longName, platfromName);
        this.platformNo = platformNo;
        this.gtfsStopId = gtfsStopId;
    }


    /**
     * Default Constructor.
     */
    public Platform() {
        super();
    }

    public int getPlatformNo() {
        return platformNo;
    }

    public void setPlatformNo(int platformNo) {
        this.platformNo = platformNo;
    }

    public int getGtfsStopId() {
        return gtfsStopId;
    }

    public void setGtfsStopId(int gtfsStopId) {
        this.gtfsStopId = gtfsStopId;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }
}
