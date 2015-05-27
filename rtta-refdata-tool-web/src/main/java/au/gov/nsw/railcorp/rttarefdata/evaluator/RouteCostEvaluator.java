package au.gov.nsw.railcorp.rttarefdata.evaluator;

import org.neo4j.graphalgo.CostEvaluator;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Relationship;

/**
 * Created by arash on 19/05/2015.
 */
public class RouteCostEvaluator implements CostEvaluator {
    /**
     * evaluate the cost based on path length.
     * @param relationship relationship
     * @param direction direction
     * @return double
     */
    public Double getCost(Relationship relationship, Direction direction) {
        final Double pathLength = Double.valueOf(relationship.getProperty("length").toString());
        return pathLength;
    }
}
