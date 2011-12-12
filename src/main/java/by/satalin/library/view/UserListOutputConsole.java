/**
 * 
 */
package by.satalin.library.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import by.satalin.library.command.CommandResponse;
import by.satalin.library.datamodel.User;
import by.satalin.library.exceptions.BaseException;
import by.satalin.library.executer.CommandExecuter;

/**
 * @author alexander.salin
 * 
 */
public class UserListOutputConsole extends OutputConsole {

	private static final Logger log = Logger
			.getLogger(UserListOutputConsole.class);

	@SuppressWarnings("unchecked")
	@Override
	protected void show(CommandResponse commandResponse) throws IOException {
		List<User> users = new ArrayList<User>();
		users = (List<User>) commandResponse
				.getResultParameters(CommandExecuter.PARAMETER_USERS);
		if (commandResponse.getException() == null) {
			for (User user : users) {
				log.info(user.toString());
				writer.write(user.toString());
			}
		} else {
			try {
				throw commandResponse.getException();
			} catch (BaseException e) {
				writer.write("NOTFOUND");
				log.error(e);
			}
		}
	}

}
