package by.satalin.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.satalin.library.exceptions.ChangesNotCompliteException;
import by.satalin.library.implementatormanager.ImplementatorManager;

public class FlushServlet extends HttpServlet {

	private static final Logger log = Logger.getLogger(FlushServlet.class);
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			ImplementatorManager.getInstance().flush();
		} catch (ChangesNotCompliteException e) {
			log.error(e);
		}
	}
}
