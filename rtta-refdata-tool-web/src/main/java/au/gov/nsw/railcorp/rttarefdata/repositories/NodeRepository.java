// RailCorp 2014
package au.gov.nsw.railcorp.rttarefdata.repositories;

import au.gov.nsw.railcorp.rttarefdata.domain.Node;
import au.gov.nsw.railcorp.rttarefdata.mapresult.INodeData;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.List;

/**
 * Created by arash on 29/10/14.
 */
public interface NodeRepository extends GraphRepository<Node> {

    /**
     * Return all Nodes.
     * @return INodeData
     */
    @Query(
            "MATCH (n:Node) RETURN id(n) AS nodeId,  n.name AS name, n.longName AS longName, n.platfromName AS  platformName"
                    + ", n.isDummy AS dummy, n.isJunction AS junction, n.isWorkingTimingPoint AS  workingTimingPoint, n.isPublicTimingPoint AS publicTimingPoint"
                    + ", n.isEndOfLine AS endOfLine, n.dWellDuration AS wellDuration, n.upRecoveryDuration AS upRecoveryDuration, n.downRecoveryDuration AS downRecoveryDuration"
                    + ", n.length AS length, n.latitude AS latitude, n.longitude AS longtitude"
    )
    List<INodeData> getAllNodes();
}
