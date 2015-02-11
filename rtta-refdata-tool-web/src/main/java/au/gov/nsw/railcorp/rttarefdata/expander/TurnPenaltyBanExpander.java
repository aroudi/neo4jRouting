package au.gov.nsw.railcorp.rttarefdata.expander;

import au.gov.nsw.railcorp.rttarefdata.domain.TurnPenaltyBan;
import au.gov.nsw.railcorp.rttarefdata.repositories.TurnPenaltyBanRepository;
import au.gov.nsw.railcorp.rttarefdata.util.IConstants;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Path;
import org.neo4j.graphdb.PathExpander;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.traversal.BranchState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Iterator;

/**
 * Created by arash on 10/02/2015.
 */
public class TurnPenaltyBanExpander  implements PathExpander {
    private RelationshipType relationshipType;
    private final Logger logger = LoggerFactory.getLogger(TurnPenaltyBanExpander.class);
    private final Direction direction;
    private TurnPenaltyBanRepository turnPenaltyBanRepository;

    /**
     * returning the reverse direction.
     * @param direction direction
     */
    private TurnPenaltyBanExpander(Direction direction) {
        this.direction = direction;
    }

    /**
     * Default Constructor.
     */
    public TurnPenaltyBanExpander() {
        this.direction = Direction.OUTGOING;
    }

    /**
     * Constructor.
     * @param relationshipType relationshipType
     * @param direction direction
     * @param turnPenaltyBanRepository turnPenaltyBanRepository
     */
    public TurnPenaltyBanExpander(RelationshipType relationshipType, Direction direction, TurnPenaltyBanRepository turnPenaltyBanRepository) {
        this.relationshipType = relationshipType;
        this.direction = direction;
        this.turnPenaltyBanRepository = turnPenaltyBanRepository;
    }

    @Override
     public Iterable<org.neo4j.graphdb.Relationship> expand(Path path, BranchState state) {
        logger.info("path length = " + path.length());
        if (path.length() < 2) {
            return path.endNode().getRelationships(relationshipType, Direction.OUTGOING);
        }
         logPath(path);

         logger.info("expand: path= " + path);
         if (path == null || path.lastRelationship() == null) {
             return Collections.emptyList();
         }
         final String currentDirection = (String) path.lastRelationship().getProperty("direction");
         logger.info("direction = " + currentDirection);
         if (currentDirection.equals(IConstants.TRACK_DOWN_DIRECTION)) {
             return Collections.emptyList();
         }
         org.neo4j.graphdb.Node node;
         final Iterator iterator = path.reverseNodes().iterator();
         node = (org.neo4j.graphdb.Node) iterator.next();
         final String toNodeName = (String) node.getProperty("name");
         node = (org.neo4j.graphdb.Node) iterator.next();
         final String viaNodeName = (String) node.getProperty("name");
         node = (org.neo4j.graphdb.Node) iterator.next();
         final String fromNodeName = (String) node.getProperty("name");
         logger.info("checking :" + fromNodeName + "-->" + viaNodeName + "-->" + toNodeName);

         final TurnPenaltyBan turnPenaltyBan = turnPenaltyBanRepository.getNodeTurnPenaltyBan(fromNodeName, viaNodeName, toNodeName);

         if (turnPenaltyBan != null && "PT99999S".equals(turnPenaltyBan.getPenalty())) {
             logger.info("       PATH excluded");
             return Collections.emptyList();
         }
         return path.endNode().getRelationships(relationshipType, Direction.OUTGOING);
     }

    /**
     * Reverse.
     * @return PathExpander
     */
    @Override
    public PathExpander reverse () {
        return new TurnPenaltyBanExpander(direction.reverse());
    }
    /**
     * log path.
     * @param path path
     */
    public void logPath (Path path) {

        try {
            if (path == null) {
                return;
            }
            logger.info("Path Start Node = " + (String) path.startNode().getProperty("name"));
            logger.info("Path End Node = " + (String) path.endNode().getProperty("name"));
            final Iterator nodeIterator = path.nodes().iterator();
            final StringBuffer pathStr = new StringBuffer("");

            while (nodeIterator.hasNext()) {
                final org.neo4j.graphdb.Node foundNode = (org.neo4j.graphdb.Node) nodeIterator.next();
                pathStr.append((String) foundNode.getProperty("name"));
                pathStr.append(" ");
            }
            logger.info("-------------------------------------------------------------------------------------------------------------");
            logger.info("Path :" + pathStr.toString());
        } catch (Exception e) {
            logger.error("Exception :", e);
            return;
        }
    }

    public RelationshipType getRelationshipType() {
        return relationshipType;
    }

    public void setRelationshipType(RelationshipType relationshipType) {
        this.relationshipType = relationshipType;
    }

    public Direction getDirection() {
        return direction;
    }

    public TurnPenaltyBanRepository getTurnPenaltyBanRepository() {
        return turnPenaltyBanRepository;
    }

    public void setTurnPenaltyBanRepository(TurnPenaltyBanRepository turnPenaltyBanRepository) {
        this.turnPenaltyBanRepository = turnPenaltyBanRepository;
    }
}
