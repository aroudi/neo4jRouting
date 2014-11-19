// RailCorp 2014
package au.gov.nsw.railcorp.rttarefdata.domain;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by arash on 3/11/14.
 */
@NodeEntity
public class NodeLink {
    @GraphId
    private Long nodeLinkId;

    private long length;
    private boolean sliding;
    private boolean crossOver;
    private boolean runningLine;

    //@Fetch
    @RelatedTo(type = Links.NODE_LINK_FROM, direction = Direction.INCOMING)
    private Node fromNode;

    //@Fetch
    @RelatedTo(type = Links.NODE_LINK_TO, direction = Direction.INCOMING)
    private Node toNode;

    @RelatedTo(type = Links.NODE_LINK_POWER, direction = Direction.INCOMING)
    private Collection<PowerType> powerTypes;

    @RelatedTo(type = Links.NODE_LINK_TRACK_SECTION, direction = Direction.BOTH)
    private TrackSection trackSection;

    @Fetch
    @RelatedTo(type = Links.NODE_LINK_RUN_TIME, direction = Direction.OUTGOING)
    private Collection<RuningTime> runingTimes;

    @RelatedTo(type = Links.NODE_LINK_GAUGE, direction = Direction.BOTH)
    private Collection<Gauge> gauges;

    /**
     * Constructor.
     * @param length length
     * @param sliding sliding
     * @param crossOver crossOver
     * @param runningLine runningLine
     */
    public NodeLink(long length, boolean sliding, boolean crossOver, boolean runningLine) {
        this.length = length;
        this.sliding = sliding;
        this.crossOver = crossOver;
        this.runningLine = runningLine;
    }


    /**
     * Default Constructor.
     */
    public NodeLink() {
    }

    /**
     * add new powerType.
     * @param powerType powerType
     */
    public void addPowerType(PowerType powerType) {
        if (powerTypes == null) {
            powerTypes = new HashSet<PowerType>();
        }
        powerTypes.add(powerType);
    }

    /**
     * add gauge to node link.
     * @param gauge gauge
     */
    public void addGauge(Gauge gauge) {
        if (gauges == null) {
            gauges = new HashSet<Gauge>();
        }
        gauges.add(gauge);
    }
    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public boolean isSliding() {
        return sliding;
    }

    public void setSliding(boolean sliding) {
        this.sliding = sliding;
    }

    public boolean isCrossOver() {
        return crossOver;
    }

    public void setCrossOver(boolean crossOver) {
        this.crossOver = crossOver;
    }

    public boolean isRunningLine() {
        return runningLine;
    }

    public void setRunningLine(boolean runningLine) {
        this.runningLine = runningLine;
    }

    public Node getFromNode() {
        return fromNode;
    }

    public void setFromNode(Node fromNode) {
        this.fromNode = fromNode;
    }

    public Node getToNode() {
        return toNode;
    }

    public void setToNode(Node nodeLinkTo) {
        this.toNode = nodeLinkTo;
    }

    public Collection<PowerType> getPowerTypes() {
        return powerTypes;
    }

    public void setPowerTypes(Collection<PowerType> powerTypes) {
        this.powerTypes = powerTypes;
    }

    public TrackSection getTrackSection() {
        return trackSection;
    }

    public void setTrackSection(TrackSection trackSection) {
        this.trackSection = trackSection;
    }

    public Collection<RuningTime> getRunningTimes() {
        return runingTimes;
    }

    public void setRunningTimes(Collection<RuningTime> runningTimes) {
        this.runingTimes = runningTimes;
    }

    public Collection<Gauge> getGauges() {
        return gauges;
    }

    public void setGauges(Collection<Gauge> gauges) {
        this.gauges = gauges;
    }
}
