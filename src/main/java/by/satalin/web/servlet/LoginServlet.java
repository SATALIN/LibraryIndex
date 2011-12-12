/**
 * 
 */
package by.satalin.web.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import by.satalin.library.command.CommandResponse;
import by.satalin.library.datamodel.User;
import by.satalin.library.exceptions.UserNotFoundException;
import by.satalin.library.servises.UserService;

/**
 * @author alexander.salin
 *Login and save current user to session
 */
public class LoginServlet extends HttpServlet {
	private static final Logger log = Logger.getLogger( LoginServlet.class);
	private static final String PAGES_ERROR_AUTH_JSP = "/pages/error.jsp";
	private static final String PAGES_MAIN_JSP = "/pages/libraryDo/listLibrary.jsp";
	private static final String LOGIN = "LOGIN";
	private static final String PASSWORD = "PASSWORD";


	@Override
	public void init(ServletConfig config) throws ServletException {
	super.init(config);
	}
	 
	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.warn("LoginServlet start with:");
		String login = request.getParameter(LOGIN);
		log.warn("Login: " +login);
		String password = request.getParameter(PASSWORD);
		log.warn("password:"+password);
		String destination = PAGES_MAIN_JSP;
		log.warn("destination = "+PAGES_MAIN_JSP);
		UserService authService =new UserService();
		try {
			HttpSession session = request.getSession();
			User user = authService.login(login, password);
			if (user!= null){
				session.setAttribute(BaseDoServlet.USER, user);
			}
		} catch (UserNotFoundException e) {
			log.error(e);
			CommandResponse comResponse = new CommandResponse();
			HttpSession session = request.getSession();
			comResponse.setException(e);
			session.setAttribute(BaseDoServlet.USER, null);
			session.setAttribute(BaseDoServlet.SAVE_RESPONSE, comResponse);
			destination = PAGES_ERROR_AUTH_JSP;
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
		rd.forward(request, response);
	}
	 
	 
	public void destroy() {
	 
	}
}
