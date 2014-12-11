package au.gov.nsw.railcorp.rttarefdata.mapresult;

import org.springframework.data.neo4j.annotation.MapResult;
import org.springframework.data.neo4j.annotation.ResultColumn;

/**
 * Created by arash on 11/12/14.
 */
@MapResult
public interface INetworkLineData {

    /**
     * networkId.
     * @return networkId
     */
    @ResultColumn("lineId")
    Long getLineId();

    /**
     * networkName.
     * @return networkName
     */
    @ResultColumn("networkName")
    String getNetworkName();

    /**
     * line name.
     * @return lineName
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
     * backgroundColouHex.
     * @return backgroundColouHex
     */
    @ResultColumn("backgroundColourHex")
    String getBackgroundColourHex();

    /**
     * textColourHex.
     * @return textColourHex
     */
    @ResultColumn("textColourHex")
    String getTextColourHex();

    /**
     * serviceTypeName.
     * @return serviceTypeName
     */
    @ResultColumn("serviceTypeName")
    String getServiceTypeName();
}
