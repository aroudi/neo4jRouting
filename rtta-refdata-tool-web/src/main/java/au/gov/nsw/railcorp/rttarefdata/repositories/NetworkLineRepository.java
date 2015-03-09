// RailCorp 2014
package au.gov.nsw.railcorp.rttarefdata.repositories;

import au.gov.nsw.railcorp.rttarefdata.domain.NetworkLine;
import au.gov.nsw.railcorp.rttarefdata.domain.ServiceType;
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
     * @param version version
     * @return INetworkLineData
     */
    @Query("MATCH (version:DataVersion{name:{0}})-[:VERSION_NETWORK]-(net:Network)-->(line:NetworkLine)<--(st:ServiceType) RETURN id(line)"
            + " AS lineId, net.name AS networkName ,line.name AS name, line.longName AS longName ,"
                    + "line.backgroundColourHex AS backgroundColourHex, line.textColourHex AS textColourHex, st.name AS serviceTypeName"
    )
    List<INetworkLineData> getAllNetworkLines(String version);

    /**
     * Return network lines as reference data.
     * @param version version
     * @return IRefData
     */
    @Query(
            "MATCH (version:DataVersion{name:{0}})-[:VERSION_LINE]-(line:NetworkLine) RETURN id(line) AS refDataId, line.name AS refDataCode, line.longName AS refDataName"
    )
    List<IRefData> getLinesAsRefData(String version);

    /**
     * get all network lines as RefData.
     * @param version version
     * @return ILineData
     */
    @Query("MATCH (version:DataVersion{name:{0}})-[:VERSION_LINE]-(line:NetworkLine) RETURN id(line) AS lineId, line.name AS name, line.longName AS longName ,"
            + "line.backgroundColourHex AS backgroundColourHex, line.textColourHex AS textColourHex"
    )
    List<ILineData> getAllLinesAsRefData(String version);

    /**
     * get line service type.
     * @param lineName lineName
     * @param version version
     * @return Service Type
     */
    @Query("MATCH (version:DataVersion{name:{0}})-[:VERSION_LINE]-(line:NetworkLine{name:{1}})<-[:LINE_SERVICE_TYPE]-(st:ServiceType) RETURN st")
    ServiceType getLineServiceType(String version, String lineName);

    /**
     * get line per name.
     * @param version version
     * @param name name
     * @return NetworkLine
     */
    @Query("MATCH (version:DataVersion{name:{0}})-[:VERSION_LINE]-(line:NetworkLine{name:{1}}) RETURN line")
    NetworkLine getLinePerName(String version, String name);

    /**
     * get all lines per version.
     * @param version version
     * @return NetworkLines
     */
    @Query("MATCH (version:DataVersion{name:{0}})-[:VERSION_LINE]-(line:NetworkLine) RETURN line")
    List<NetworkLine> getAllLinesPerVersion(String version);
    /**
     * delete all networkKines with specific version.
     * @param version version;
     */
    @Query("MATCH (version:DataVersion)-[:VERSION_LINE]-(line:NetworkLine) WHERE version.name={0} DELETE line")
    void deleteNetworkLinesPerVersion(String version);

}
