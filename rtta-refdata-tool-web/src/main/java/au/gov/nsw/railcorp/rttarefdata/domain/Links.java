// RailCorp 2014
package au.gov.nsw.railcorp.rttarefdata.domain;

/**
 * Created by arash on 28/10/14.
 */
public interface Links {
    /**
     * Link between (Network) --> (NetworkLine).
     */
     String NETWORK_LINE = "NETWORK_LINE";

    /**
     * Link between (NetworkLine) <-- (ServiceType).
     */
    String LINE_SERVICE_TYPE = "LINE_SERVICE_TYPE";

    /**
     * Link between (NetworkLine) --> (LinePath).
     */
    String LINE_PATH = "LINE_PATH";

    /**
     * Link between (PowerTytpe) --> (LinePath).
     */
    String LINE_PATH_POWER = "LINE_PATH_POWER";

    /**
     * Link between (PowerTytpe) --> (StationTriplet).
     */
    String STATION_LINK_POWER = "STATION_LINK_POWER";

    /**
     * Link between (LinePath) --> (Station).
     */
    String LINE_PATH_STATION = "LINE_PATH_STATION";

    /**
     * Link between (Station) --> (StationTriplet).
     */
    String STATION_TRIPLET = "STATION_TRIPLET";
    /**
     * Link between (Station) --> (StationTriplet).
     */
    String MAIN_NODE = "MAIN_NODE";
    /**
     * Link between (Station) --> (StationTriplet).
     */
    String FROM_NODE = "FROM_NODE";
    /**
     * Link between (Station) --> (StationTriplet).
     */
    String TO_NODE = "TO_NODE";
    /**
     * Link between (Station) --> (Platform).
     */
    String STATION_PLATFORM = "STATION_PLATFORM";

    /**
     * Link between (Node) -[MASTER_TIMING_POINT]-> (Node).
     */
    String MASTER_TIMING_POINT = "MASTER_TIMING_POINT";

    /**
     * Link between (Node) -[MASTER_JUNCTION]-> (Node).
     */
    String MASTER_JUNCTION = "MASTER_JUNCTION";

    /**
     * Link between (Node) -[TURN_PENALTY_BAN]-> (Node).
     */
    String TURN_PENALTY_BAN = "TURN_PENALTY_BAN";

    /**
     * Link between (Node) -[NODE_LINK_TO]-> (NodeLink).
     */
    String NODE_LINK_TO = "NODE_LINK_TO";
    /**
     * Link between (Node) -[NODE_LINK_FROM]-> (NodeLink).
     */
    String NODE_LINK_FROM = "NODE_LINK_FROM";

    /**
     * Link between (NodeLink) -[NODE_LINK_TRACK_SECTION]-> (TrackSection).
     */
    String NODE_LINK_TRACK_SECTION = "NODE_LINK_TRACK_SECTION";

    /**
     * Link between (NodeLink) -[NODE_LINK_RUN_TIME]-> (RunningTime).
     */
    String NODE_LINK_RUN_TIME = "NODE_LINK_RUN_TIME";

    /**
     * Link between (RunningTime) -[RUN_TIME_SPEED_BAND]-> (SpeedBand).
     */
    String RUN_TIME_SPEED_BAND = "RUN_TIME_SPEED_BAND";

    /**
     * Link between (NodeLink) -[NODE_LINK_POWER]-> (PowerType).
     */
    String NODE_LINK_POWER = "NODE_LINK_POWER";
    /**
     * Link between (NodeLink) -[NODE_LINK_GAUGE]-> (Gauge).
     */
    String NODE_LINK_GAUGE = "NODE_LINK_GAUGE";
    /**
     * Link between (Node) -[NODE_LINK]-> (Node).
     */
    String NODE_LINKAGE = "NODE_LINKAGE";

    /**
     * Link between (Node) -[NODE_LOCATION]-> (Location).
     */
    String NODE_LOCATION = "NODE_LOCATION";
    /**
     * Link between (DataVersion) -[VERSION_NETWORK]-> (Network).
     */
    String VERSION_NETWORK = "VERSION_NETWORK";
    /**
     * Link between (DataVersion) -[VERSION_LINE]-> (NetworkLine).
     */
    String VERSION_LINE = "VERSION_LINE";
    /**
     * Link between (DataVersion) -[VERSION_PATH]-> (LinePath).
     */
    String VERSION_PATH = "VERSION_PATH";
    /**
     * Link between (DataVersion) -[VERSION_STATION]-> (STATION).
     */
    String VERSION_STATION = "VERSION_STATION";
    /**
     * Link between (DataVersion) -[VERSION_PLATFORM]-> (PLATFORM).
     */
    String VERSION_PLATFORM = "VERSION_PLATFORM";
    /**
     * Link between (DataVersion) -[VERSION_NODE]-> (NODE).
     */
    String VERSION_NODE = "VERSION_NODE";
    /**
     * Link between (DataVersion) -[VERSION_LOCATION]-> (LOCATION).
     */
    String VERSION_LOCATION = "VERSION_LOCATION";
    /**
     * Link between (DataVersion) -[VERSION_RUN_TIME]-> (RunningTime).
     */
    String VERSION_RUN_TIME = "VERSION_RUN_TIME";
    /**
     * Link between (DataVersion) -[VERSION_NODAL_HEADER]-> (NodalHeader).
     */
    String VERSION_NODAL_HEADER = "VERSION_NODAL_HEADER";
    /**
     * Link between (DataVersion) -[VERSION_NODE_LINK]-> (NodeLink).
     */
    String VERSION_NODE_LINK = "VERSION_NODE_LINK";
    /**
     * Link between (DataVersion) -[VERSION_SEQUENCE]-> (EntitySequence).
     */
    String VERSION_SEQUENCE = "VERSION_SEQUENCE";
    /**
     * Link between (DataVersion) -[VERSION_TRACK_SECTION]-> (TrackSection).
     */
    String VERSION_TRACK_SECTION = "VERSION_TRACK_SECTION";
    /**
     * Link between (DataVersion) -[BASE_VERSION]-> (DataVersion).
     */
    String BASE_VERSION = "BASE_VERSION";
    /**
     * Link between (DataVersion) -[VERSION_TRIPLET-> (StationTriplet).
     */
    String VERSION_TRIPLET = "VERSION_TRIPLET";
    /**
     * Link between (DataVersion) -[VERSION_SPEEDBAND]-> (SpeedBand).
     */
    String VERSION_SPEEDBAND = "VERSION_SPEEDBAND";
}
