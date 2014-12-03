// RailCorp 2013

package au.gov.nsw.railcorp.refdatatool.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * AppServiceContextListener: intialises the servlet.
 * @author Huub
 */
public class AppServiceContextListener implements ServletContextListener {

    private static final Logger log = LoggerFactory.getLogger(AppServiceContextListener.class);

    /**
     * {@inheritDoc}
     * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
     */
    @Override
    public void contextDestroyed(ServletContextEvent arg0) {

        log.info("contextDestroyed");

    }

    /**
     * {@inheritDoc}
     * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
     */
    @Override
    public void contextInitialized(ServletContextEvent arg0) {

        log.info("contextInitialized");
    }

}
