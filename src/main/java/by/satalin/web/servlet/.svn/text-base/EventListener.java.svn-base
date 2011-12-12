package by.satalin.web.servlet;

import javax.servlet.ServletContextEvent;

import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import by.satalin.library.exceptions.ChangesNotCompliteException;
import by.satalin.library.exceptions.LibraryNotFoundException;
import by.satalin.library.implementatormanager.ImplementatorManager;

public class EventListener implements ServletContextListener {
	private static final Logger log = Logger.getLogger(EventListener.class);
    public void contextDestroyed(ServletContextEvent arg0) {
    	try {
			ImplementatorManager.getInstance().flush();
		} catch (ChangesNotCompliteException e) {
			 log.error(e);
		}
        log.info("Context destroyed ...");

    }

    public void contextInitialized(ServletContextEvent arg0) {
    	try {
			ImplementatorManager.getInstance().getLibraries();
		} catch (LibraryNotFoundException e) {
	        log.error(e);
		}
    	log.info("Context initialized ...");

    }

}

