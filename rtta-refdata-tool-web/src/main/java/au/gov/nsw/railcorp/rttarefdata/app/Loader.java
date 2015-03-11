package au.gov.nsw.railcorp.rttarefdata.app;

import au.gov.nsw.railcorp.rttarefdata.service.DataVersionService;
import au.gov.nsw.railcorp.rttarefdata.session.SessionState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by arash on 11/03/2015.
 */
@Component
public class Loader implements ApplicationListener<ContextRefreshedEvent> {

    private final Logger logger = LoggerFactory.getLogger(Loader.class);

    @Autowired
    private SessionState sessionState;

    @Autowired
    private DataVersionService dataVersionService;

    /**
     * set default version to active version on startup.
     * @param event event
     */
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (dataVersionService == null || sessionState == null) {
            logger.info("Not able to set default version.");
            return;
        }
        sessionState.setWorkingVersion(dataVersionService.getActiveVersion());
        sessionState.setActiveVersion(dataVersionService.getActiveVersion());
        logger.info("Default version set to active version.");
    }
}
