package au.gov.nsw.railcorp.rttarefdata.manager;

import au.gov.nsw.railcorp.rttarefdata.domain.EntitySequence;
import au.gov.nsw.railcorp.rttarefdata.repositories.EntitySequenceRepository;
import au.gov.nsw.railcorp.rttarefdata.util.IConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by arash on 6/11/14.
 */
@Component
@Transactional
public class EntitySequenceManager {
    @Autowired
    private EntitySequenceRepository entitySequenceRepository;

    /**
     * return last station id from sequence.
     * @return station sequence
     */
    public long getNextStationSequence() {
        EntitySequence entitySequence = entitySequenceRepository.findBySchemaPropertyValue("entityName", IConstants.ENTITY_SEQUENCE_NAME);
        if (entitySequence == null) {
            entitySequence = initializeEntitySequence();
        }
        final long stationSeq = entitySequence.getStationSeq();
        final long nextseq = stationSeq + 1;
        entitySequence.setStationSeq(nextseq);
        entitySequenceRepository.save(entitySequence);
        return stationSeq;
    }

    /**
     * Initialize sequences.
     */
    private EntitySequence initializeEntitySequence() {
        final EntitySequence entitySequence = new EntitySequence();
        entitySequence.setEntityName(IConstants.ENTITY_SEQUENCE_NAME);
        entitySequence.setStationSeq(1);
        entitySequenceRepository.save(entitySequence);
        return entitySequence;
    }
}
