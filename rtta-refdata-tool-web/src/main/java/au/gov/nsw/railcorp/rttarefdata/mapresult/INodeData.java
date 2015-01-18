package au.gov.nsw.railcorp.rttarefdata.mapresult;

import org.springframework.data.neo4j.annotation.MapResult;
import org.springframework.data.neo4j.annotation.ResultColumn;

/**
 * Created by arash on 16/12/2014.
 */
@MapResult

public interface INodeData {
    /**
     * nodeId.
     * @return id
     */
    @ResultColumn("nodeId")
    Long getNodeId();

    /**
     * name.
     * @return String
     */
    @ResultColumn("name")
    String getName();

    /**
     * longName.
     * @return String
     */
    @ResultColumn("longName")
    String getLongName();

    /**
     * platformName.
     * @return String
     */
    @ResultColumn("platformName")
    String getPlatformName();

    /**
     * dummy.
     * @return boolean
     */
    @ResultColumn("dummy")
    boolean isDummy();

    /**
     * junction.
     * @return boolean
     */
    @ResultColumn("junction")
    boolean isJunction();

    /**
     * workingTimingPoint.
     * @return boolean
     */
    @ResultColumn("workingTimingPoint")
    boolean isWorkingTimingPoint();

    /**
     * publicTimingPoint.
     * @return boolean
     */
    @ResultColumn("publicTimingPoint")
    boolean isPublicTimingPoint();

    /**
     * endOfLine.
     * @return boolean
     */
    @ResultColumn("endOfLine")
    boolean isEndOfLine();

    /**
     * wellDuration.
     * @return String
     */
    @ResultColumn("wellDuration")
    String getWellDuration();

    /**
     * upRecoveryDuration.
     * @return String
     */
    @ResultColumn("upRecoveryDuration")
    String getUpRecoveryDuration();

    /**
     * downRecoveryDuration.
     * @return String
     */
    @ResultColumn("downRecoveryDuration")
    String getDownRecoveryDuration();

    /**
     * length.
     * @return double
     */
    @ResultColumn("length")
    long getLength();

    /**
     * latitude.
     * @return double
     */
    @ResultColumn("latitude")
    double getLatitude();

    /**
     * longtitude.
     * @return double
     */
    @ResultColumn("longtitude")
    double getLongtitude();

    /**
     * masterTimingPointName.
     * @return String
     */
    @ResultColumn("masterTimingPointName")
    String getMasterTimingPointName();

    /**
     * masterJunctionName.
     * @return String
     */
    @ResultColumn("masterJunctionName")
    String getMasterJunctionName();
}
