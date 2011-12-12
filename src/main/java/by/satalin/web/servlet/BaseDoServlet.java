/**
 * 
 */
package by.satalin.web.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

import by.satalin.config.Configuration;
import by.satalin.library.command.CommandResponse;
import by.satalin.library.command.CommandVO;
import by.satalin.library.command.CommandRequest;
import by.satalin.library.datamodel.User;
import by.satalin.library.exceptions.UserNotFoundException;
import by.satalin.library.main.LauncherCore;
import by.satalin.library.servises.UserService;

/**
 * @author alexander.salin Servlet call Executer by command and redirect
 *         response on chosen page
 */
public class BaseDoServlet extends HttpServlet {
	private static final String LOGIN = "LOGIN";
	// TODO
	private static final Logger log = Logger.getLogger(BaseDoServlet.class);
	private static final String ERROR = "ERROR";
	private static final String COMMAND = "command";
	public static final String USER = "USER";
	public static final String SAVE_RESPONSE ="SAVERESPONSE";

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		//Configuration.getInstance();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		forwardToJsp(request, response, handleRequest(request));
	}

	/**
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void forwardToJsp(HttpServletRequest request,
			HttpServletResponse response, String destination) throws ServletException, IOException {
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				destination);
		rd.forward(request, response);
	}

	/**
	 * @param request
	 */
	protected String handleRequest(HttpServletRequest request) {
		String destination =null;
		CommandVO command = new CommandRequest();
		LauncherCore webCore = new LauncherCore();
		HttpSession session = request.getSession();
		command.setName(request.getParameter(COMMAND));
		destination = Configuration.getInstance().getProperty(command.getName());    
		command.setUser((User) session.getAttribute(USER));
		command.setParameters(getParamsFromRequest(request.getParameterMap()));
		CommandResponse comResponse = (CommandResponse) webCore
				.launchForResponse(command);
		if (comResponse.getException() != null) {
			// check on user self delete
			User user = command.getUser();
			UserService authService = new UserService();
			destination = Configuration.getInstance().getProperty(ERROR);
			try {
				session.setAttribute(
						BaseDoServlet.USER,
						authService.login(user.getNickName(),
								user.getPassword()));
			} catch (UserNotFoundException e) {
				session.setAttribute(USER, null);
				destination = Configuration.getInstance().getProperty(LOGIN);
			}

		}
		session.setAttribute(SAVE_RESPONSE, comResponse);
		return destination;
	}

	/**
	 * @param params
	 * @return
	 */
	private Map<String, String> getParamsFromRequest(
			Map<String, String[]> params) {
		Map<String, String> responseParams = new HashMap<String, String>();
		for (String key : params.keySet()) {
			responseParams.put(key, params.get(key)[0]);
		}
		return responseParams;
	}

	public void destroy() {

	}



}
