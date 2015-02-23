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
     * @return ILinePath
     */
    @Query("MATCH (line:NetworkLine)-[:LINE_PATH]->(linePath:LinePath) RETURN id(linePath) AS pathId, linePath.name AS name,"
            + "linePath.longName AS longName, line.name AS lineName, line.longName AS lineLongName, "
            + "line.backgroundColourHex AS backgroundColourHex, line.textColourHex AS textColourHex"
    )
    List<ILinePath> getAllLinePaths();

    /**
     * get all line path for specific network.
     * @param networkName networkName
     * @return ILinePath
     */
    @Query("MATCH (network:Network{name:{0}})-[:NETWORK_LINE]->(line:NetworkLine)-[:LINE_PATH]->(linePath:LinePath) RETURN id(linePath) AS pathId, linePath.name AS name,"
            + "linePath.longName AS longName, line.name AS lineName, line.longName AS lineLongName, "
            + "line.backgroundColourHex AS backgroundColourHex, line.textColourHex AS textColourHex"
    )
    List<ILinePath> getAllLinePathsPerNetwork(String networkName);
    /**
     * get linePath power type.
     * @param linePath linePath
     * @return Power Type
     */
    @Query("MATCH (linePath:LinePath{name:{0}})-[:LINE_PATH_POWER]->(pt:PowerType) RETURN pt")
    List<PowerType> getLinePathPowerType(String linePath);

}
