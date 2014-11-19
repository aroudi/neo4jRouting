// RailCorp 2013
package au.gov.nsw.railcorp.rttarefdata.domain;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

import javax.xml.datatype.Duration;

/**
 * Created by arash on 3/11/14.
 */
@NodeEntity
public class RuningTime {
    @GraphId
    private Long runingTimeId;

    private String stopToStop;
    private String passToPass;

    @RelatedTo(type = Links.RUN_TIME_SPEED_BAND, direction = Direction.INCOMING)
    private SpeedBand speedBand;

    @RelatedTo(type = Links.NODE_LINK_RUN_TIME, direction = Direction.INCOMING)
    private NodeLink nodeLink;

    /**
     * Constructor.
     * @param stopToStop stopToStop
     * @param passToPass passToPass
     */
    public RuningTime(String stopToStop, String passToPass) {
        this.stopToStop = stopToStop;
        this.passToPass = passToPass;
    }

    /**
     * Default constructor.
     */
    public RuningTime() {
    }

    public String getStopToStop() {
        return stopToStop;
    }

    public void setStopToStop(String stopToStop) {
        this.stopToStop = stopToStop;
    }

    public String getPassToPass() {
        return passToPass;
    }

    public void setPassToPass(String passToPass) {
        this.passToPass = passToPass;
    }

    public SpeedBand getSpeedBand() {
        return speedBand;
    }

    public void setSpeedBand(SpeedBand speedBand) {
        this.speedBand = speedBand;
    }

    public NodeLink getNodeLink() {
        return nodeLink;
    }

    public void setNodeLink(NodeLink nodeLink) {
        this.nodeLink = nodeLink;
    }

}
