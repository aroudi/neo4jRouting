// RailCorp 2013
package au.gov.nsw.railcorp.rttarefdata.repositories;

import au.gov.nsw.railcorp.rttarefdata.domain.LinePath;
import au.gov.nsw.railcorp.rttarefdata.domain.PowerType;
import au.gov.nsw.railcorp.rttarefdata.mapresult.ILinePath;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.List;

/**
 * Created by arash on 29/10/14.
 */
public interface LinePathRepository extends GraphRepository<LinePath> {
    /**
     * get all line path.
     * @param version version
     * @return ILinePath
     */
    @Query("MATCH (version:DataVersion{name:{0}})-[:VERSION_LINE]-(line:NetworkLine)-[:LINE_PATH]->(linePath:LinePath) RETURN id(linePath) AS pathId, linePath.name AS name,"
            + "linePath.longName AS longName, line.name AS lineName, line.longName AS lineLongName, "
            + "line.backgroundColourHex AS backgroundColourHex, line.textColourHex AS textColourHex"
    )
    List<ILinePath> getAllLinePaths(String version);

    /**
     * get all line path for specific network.
     * @param networkName networkName
     * @param version version
     * @return ILinePath
     */
    @Query("MATCH (version:DataVersion{name:{0}})-[:VERSION_NETWORK]-(network:Network{name:{1}})-[:NETWORK_LINE]->(line:NetworkLine)-[:LINE_PATH]->(linePath:LinePath)"
            + " RETURN id(linePath) AS pathId, linePath.name AS name,"
            + "linePath.longName AS longName, line.name AS lineName, line.longName AS lineLongName, "
            + "line.backgroundColourHex AS backgroundColourHex, line.textColourHex AS textColourHex"
    )
    List<ILinePath> getAllLinePathsPerNetwork(String version, String networkName);
    /**
     * get linePath power type.
     * @param linePath linePath
     * @param version version
     * @return Power Type
     */
    @Query("MATCH (version:DataVersion{name:{0}})-[:VERSION_PATH]-(linePath:LinePath{name:{1}})-[:LINE_PATH_POWER]->(pt:PowerType) RETURN pt")
    List<PowerType> getLinePathPowerType(String version, String linePath);

    /**
     * get linePath per name.
     * @param linePath linePath
     * @param version version
     * @return LinePath
     */
    @Query("MATCH (version:DataVersion{name:{0}})-[:VERSION_PATH]-(linePath:LinePath{name:{1}}) RETURN linePath")
    LinePath getLinePathPerName(String version, String linePath);

    /**
     * delete all linePaths with specific version.
     * @param version version;
     */
    @Query("MATCH (version:DataVersion)-[:VERSION_PATH]-(linePath:LinePath) WHERE version.name={0} DELETE linePath")
    void deleteLinePathPerVersion(String version);

}
