// RailCorp 2014
package au.gov.nsw.railcorp.rttarefdata.repositories;

import au.gov.nsw.railcorp.rttarefdata.domain.NetworkLine;
import au.gov.nsw.railcorp.rttarefdata.mapresult.ILineData;
import au.gov.nsw.railcorp.rttarefdata.mapresult.INetworkLineData;
import au.gov.nsw.railcorp.rttarefdata.mapresult.IRefData;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.List;

/**
 * Created by arash on 29/10/14.
 */
public interface NetworkLineRepository extends GraphRepository<NetworkLine> {

    /**
     * get all network lines.
     * @return INetworkLineData
     */
    @Query("MATCH (net:Network)-->(line:NetworkLine)<--(st:ServiceType) RETURN id(line) AS lineId, net.name AS networkName ,line.name AS name, line.longName AS longName ,"
                    + "line.backgroundColourHex AS backgroundColourHex, line.textColourHex AS textColourHex, st.name AS serviceTypeName"
    )
    List<INetworkLineData> getAllNetworkLines();

    /**
     * Return network lines as reference data.
     * @return IRefData
     */
    @Query(
            "MATCH (line:NetworkLine) RETURN id(line) AS refDataId, line.name AS refDataCode, line.longName AS refDataName"
    )
    List<IRefData> getLinesAsRefData();

    /**
     * get all network lines as RefData.
     * @return ILineData
     */
    @Query("MATCH (line:NetworkLine) RETURN id(line) AS lineId, line.name AS name, line.longName AS longName ,"
            + "line.backgroundColourHex AS backgroundColourHex, line.textColourHex AS textColourHex"
    )
    List<ILineData> getAllLinesAsRefData();
}
