/**
 * 
 */
package by.satalin.library.main;

import java.io.IOException;

import org.apache.log4j.Logger;

import by.satalin.library.command.CommandVO;
import by.satalin.library.command.CommandRequest;
import by.satalin.library.dao.UserDaoImpl;
import by.satalin.library.datamodel.User;
import by.satalin.library.exceptions.UserNotFoundException;
import by.satalin.library.servises.UserService;
import by.satalin.library.view.InputConsole;

/**
 * @author SATALIN Point to start application from console
 */
public class ConsoleStarter {
	private static final Logger log = Logger.getLogger(ConsoleStarter.class);
	private static final String EXIT = "EXIT";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CommandVO cmd = new CommandRequest();
		log.info("console start . . .");
		LauncherCore launcher = new LauncherCore();
		InputConsole console = null;
		try {
			console = new InputConsole(System.in, System.out);
			while (cmd.getUser() == null) {
				User user = console.authentification();
				UserService service = new UserService();
				try {
					cmd.setUser(service.login(user.getNickName(),
							user.getPassword()));
				} catch (UserNotFoundException e) {
					log.info("nickname =" + user.getNickName()
							+ " or password=" + user.getPassword()
							+ "incorrect");
					log.error("e");
				}
			}

			cmd = console.getNextCommand(cmd.getUser());
			while (cmd.getName() != EXIT) {
				//console.destroyBuffered();
				launcher.launch(cmd);
				//console = new InputConsole(System.in, System.out);
				cmd = console.getNextCommand(cmd.getUser());
			}
		} catch (Exception e) {
			log.error(e);
			System.out.println(e.toString());
		} finally {
			try {
				console.destroyBuffered();
			} catch (IOException e) {
				log.error(e);
				e.printStackTrace();
			}
		}
	}
}
