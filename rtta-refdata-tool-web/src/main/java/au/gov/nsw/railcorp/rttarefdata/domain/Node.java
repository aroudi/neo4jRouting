// RailCorp 2014
package au.gov.nsw.railcorp.rttarefdata.domain;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.*;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by arash on 28/10/14.
 */

@NodeEntity
public class Node {

    /**
     * Node Name.
     */
    @Indexed
    //(unique = true)
    protected String name;
    /**
     * Node Long Name.
     */
    protected String longName;
    /**
     * Platform Name.
     */
    protected String platfromName;
    /**
     * Is node dummy.
     */
    protected boolean isDummy;
    /**
     * Is node junction.
     */
    protected boolean isJunction;
    /**
     * Is working timing point.
     */
    protected boolean isWorkingTimingPoint;
    /**
     * Is Public Timing Point.
     */
    protected boolean isPublicTimingPoint;
    /**
     * Is End of Line.
     */
    protected boolean isEndOfLine;
    /**
     * Is well duration.
     */
    protected String dWellDuration;
    /**
     * is up recovery duration.
     */
    protected String upRecoveryDuration;
    /**
     * is down recovery duration.
     */
    protected String downRecoveryDuration;
    /**
     * length.
     */
    protected double length;

    /**
     * lattitude.
     */
    protected double latitude;

    /**
     * longtitude.
     */
    protected double longitude;

    /**
     * master timing junction node name.
     */
    protected String masterJunctionName;

    /**
     * master timing point node name.
     */
    protected String masterTimingPointName;

    /**
     * if the source of the node is railNet?
     */
    protected boolean railNetNode;

    /**
     * Master Timing Point.
     */
   @RelatedTo (type = Links.MASTER_TIMING_POINT, direction = Direction.OUTGOING)
   protected Node masterTimingPoint;

    /**
     * Master Junction.
     */
    @RelatedTo (type = Links.MASTER_JUNCTION, direction = Direction.OUTGOING)
    protected Node masterJunction;

    /**
     * Turnning penalty bans from this node to others.
     */
    @RelatedToVia(type = Links.TURN_PENALTY_BAN, direction = Direction.OUTGOING)
    protected Collection<TurnPenaltyBan> outgoingTurnPenalties;

    /**
     * Turning penalty bans from other nodes to this node.
     */
    @RelatedToVia(type = Links.TURN_PENALTY_BAN, direction = Direction.INCOMING)
    protected Collection<TurnPenaltyBan> incomingTurnPenaltyBans;

    /**
     * Node Links from this node to others.
     */
    @RelatedTo(type = Links.NODE_LINK_FROM, direction = Direction.OUTGOING)
    protected Collection<NodeLink> outGoingNodeLinks;

    /**
     * Node Links from others to this node.
     */
    @RelatedTo(type = Links.NODE_LINK_TO, direction = Direction.OUTGOING)
    protected Collection<NodeLink> incomingNodeLinks;

    /**
     * node linkages.
     */
    @RelatedToVia(type = Links.NODE_LINKAGE, direction = Direction.OUTGOING)
    protected Collection<NodeLinkage> nodeLinkages;

    /**
     * Locations related to this node.
     */
    @RelatedTo(type = Links.NODE_LOCATION, direction = Direction.BOTH)
    protected Collection<Location> locations;


    @RelatedTo(type = Links.VERSION_NODE, direction = Direction.BOTH)
    @Fetch
    private DataVersion version;

    /**
     * graph Id.
     */
    @GraphId
    private Long nodeId;



    /**
     * Constructor.
     * @param name name
     * @param longName long name
     * @param platfromName platfrom name
     */
    public Node(String name, String longName, String platfromName) {
        this.name = name;
        this.longName = longName;
        this.platfromName = platfromName;
    }

    /**
     * Constructor.
     * @param name name
     * @param longName longName
     * @param platfromName platfromName
     * @param isDummy isDummy
     * @param isJunction isJunction
     * @param isWorkingTimingPoint isWorkingTimingPoint
     * @param isPublicTimingPoint isPublicTimingPoint
     * @param isEndOfLine isEndOfLine
     * @param dWellDuration dWellDuration
     * @param upRecoveryDuration upRecoveryDuration
     * @param downRecoveryDuration downRecoveryDuration
     * @param length length
     */
    public Node(String name, String longName, String platfromName, boolean isDummy, boolean isJunction,
                boolean isWorkingTimingPoint, boolean isPublicTimingPoint, boolean isEndOfLine, String dWellDuration,
                String upRecoveryDuration, String downRecoveryDuration, double length)
    {
        this.name = name;
        this.longName = longName;
        this.platfromName = platfromName;
        this.isDummy = isDummy;
        this.isJunction = isJunction;
        this.isWorkingTimingPoint = isWorkingTimingPoint;
        this.isPublicTimingPoint = isPublicTimingPoint;
        this.isEndOfLine = isEndOfLine;
        this.dWellDuration = dWellDuration;
        this.upRecoveryDuration = upRecoveryDuration;
        this.downRecoveryDuration = downRecoveryDuration;
        this.length = length;
    }

    /**
     * Default Constructor.
     */
    public Node() {
    }

    /**
     * add a TurnPenaltyBan from this node to others.
     * @param turnPenaltyBan turnPenaltyBan
     */
    public void addOutgoingTurnPenaltyBan(TurnPenaltyBan turnPenaltyBan) {
        if (outgoingTurnPenalties == null) {
            outgoingTurnPenalties = new HashSet<TurnPenaltyBan>();
        }
        outgoingTurnPenalties.add(turnPenaltyBan);
    }

    /**
     * add a TurnPenaltyBan from other node to this.
     * @param turnPenaltyBan turnPenaltyBan
     */
    public void addIncomingTurnPenaltyBan(TurnPenaltyBan turnPenaltyBan) {
        if (incomingTurnPenaltyBans == null) {
            incomingTurnPenaltyBans = new HashSet<TurnPenaltyBan>();
        }
        incomingTurnPenaltyBans.add(turnPenaltyBan);
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

    public String getPlatfromName() {
        return platfromName;
    }

    public void setPlatfromName(String platfromName) {
        this.platfromName = platfromName;
    }

    public boolean isDummy() {
        return isDummy;
    }

    public void setDummy(boolean isDummy) {
        this.isDummy = isDummy;
    }

    public boolean isJunction() {
        return isJunction;
    }

    public void setJunction(boolean isJunction) {
        this.isJunction = isJunction;
    }

    public boolean isWorkingTimingPoint() {
        return isWorkingTimingPoint;
    }

    public void setWorkingTimingPoint(boolean isWorkingTimingPoint) {
        this.isWorkingTimingPoint = isWorkingTimingPoint;
    }

    public boolean isPublicTimingPoint() {
        return isPublicTimingPoint;
    }

    public void setPublicTimingPoint(boolean isPublicTimingPoint) {
        this.isPublicTimingPoint = isPublicTimingPoint;
    }

    public boolean isEndOfLine() {
        return isEndOfLine;
    }

    public void setEndOfLine(boolean isEndOfLine) {
        this.isEndOfLine = isEndOfLine;
    }

    /**
     * getdWellDuration.
     * @return string
     */
    public String getdWellDuration() {
        return dWellDuration;
    }

    /**
     * setdWellDuration.
     * @param dWellDuration sdfdf
     */
    public void setdWellDuration(String dWellDuration) {
        this.dWellDuration = dWellDuration;
    }

    public String getUpRecoveryDuration() {
        return upRecoveryDuration;
    }

    public void setUpRecoveryDuration(String upRecoveryDuration) {
        this.upRecoveryDuration = upRecoveryDuration;
    }

    public String getDownRecoveryDuration() {
        return downRecoveryDuration;
    }

    public void setDownRecoveryDuration(String downRecoveryDuration) {
        this.downRecoveryDuration = downRecoveryDuration;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public Node getMasterTimingPoint() {
        return masterTimingPoint;
    }

    public void setMasterTimingPoint(Node masterTimingPoint) {
        this.masterTimingPoint = masterTimingPoint;
    }

    public Node getMasterJunction() {
        return masterJunction;
    }

    public void setMasterJunction(Node masterJunction) {
        this.masterJunction = masterJunction;
    }

    public Collection<TurnPenaltyBan> getOutgoingTurnPenalties() {
        return outgoingTurnPenalties;
    }

    public void setOutgoingTurnPenalties(Collection<TurnPenaltyBan> outgoingTurnPenalties) {
        this.outgoingTurnPenalties = outgoingTurnPenalties;
    }

    public Collection<TurnPenaltyBan> getIncomingTurnPenaltyBans() {
        return incomingTurnPenaltyBans;
    }

    public void setIncomingTurnPenaltyBans(Collection<TurnPenaltyBan> incomingTurnPenaltyBans) {
        this.incomingTurnPenaltyBans = incomingTurnPenaltyBans;
    }

    public Collection<NodeLink> getOutGoingNodeLinks() {
        return outGoingNodeLinks;
    }

    public void setOutGoingNodeLinks(Collection<NodeLink> outGoingNodeLinks) {
        this.outGoingNodeLinks = outGoingNodeLinks;
    }

    public Collection<NodeLink> getIncomingNodeLinks() {
        return incomingNodeLinks;
    }

    public void setIncomingNodeLinks(Collection<NodeLink> incomingNodeLinks) {
        this.incomingNodeLinks = incomingNodeLinks;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Collection<NodeLinkage> getNodeLinkages() {
        return nodeLinkages;
    }

    public void setNodeLinkages(Collection<NodeLinkage> nodeLinkages) {
        this.nodeLinkages = nodeLinkages;
    }

    public String getMasterJunctionName() {
        return masterJunctionName;
    }

    public void setMasterJunctionName(String masterJunctionName) {
        this.masterJunctionName = masterJunctionName;
    }

    public String getMasterTimingPointName() {
        return masterTimingPointName;
    }

    public void setMasterTimingPointName(String masterTimingPointName) {
        this.masterTimingPointName = masterTimingPointName;
    }

    public boolean isRailNetNode() {
        return railNetNode;
    }

    public void setRailNetNode(boolean railNetNode) {
        this.railNetNode = railNetNode;
    }

    public Collection<Location> getLocations() {
        return locations;
    }

    public void setLocations(Collection<Location> locations) {
        this.locations = locations;
    }

    public DataVersion getVersion() {
        return version;
    }

    public void setVersion(DataVersion version) {
        this.version = version;
    }
}
