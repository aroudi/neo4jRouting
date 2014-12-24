package au.gov.nsw.railcorp.rttarefdata.mapresult;

import org.springframework.data.neo4j.annotation.MapResult;
import org.springframework.data.neo4j.annotation.ResultColumn;

/**
 * Created by arash on 22/12/2014.
 */
@MapResult
public interface ILinePath {
    /**
     * pathId.
     * @return pathId
     */
    @ResultColumn("pathId")
    Long getPathId();

    /**
     * name.
     * @return name
     */
    @ResultColumn("name")
    String getName();
    /**
     * longName.
     * @return longName
     */
    @ResultColumn("longName")
    String getLongName();

    /**
     * lineName.
     * @return lineName
     */
    @ResultColumn("lineName")
    String getLineName();

    /**
     * lineLongName.
     * @return lineLongName
     */
    @ResultColumn("lineLongName")
    String getLineLongName();

    /**
     * backgroundColourHex.
     * @return backgroundColourHex
     */
    @ResultColumn("backgroundColourHex")
    String getBackgroundColourHex();

    /**
     * textColourHex.
     * @return textColourHex
     */
    @ResultColumn("textColourHex")
    String getTextColourHex();
}
