/**
 * 
 */
package by.satalin.library.view;

import java.io.IOException;
import org.apache.log4j.Logger;
import by.satalin.library.command.CommandResponse;
import by.satalin.library.datamodel.User;
import by.satalin.library.exceptions.BaseException;
import by.satalin.library.exceptions.UserNotFoundException;
import by.satalin.library.executer.CommandExecuter;

/**
 * @author alexander.salin
 * 
 */
public class UserOperationOutputConsole extends OutputConsole {
	private static final Logger log = Logger
			.getLogger(UserOperationOutputConsole.class);

	@Override
	protected void show(CommandResponse commandResponse) throws IOException {
		User user = new User();
		user = (User) commandResponse
				.getResultParameters(CommandExecuter.PARAMETER_USER);
		String response = "";
		if (commandResponse.getException() == null) {
			response = "OK";
		}else{
			try{
				throw commandResponse.getException();
			}catch (UserNotFoundException e) {
				response = "NOTFOUND";
			} catch (BaseException e) {
				response = "USERALREDYEXISTS";
				log.error(e);
			}
		}
		log.info(response + " User=" + user.getNickName() + " ID="
				+ user.getId());
		writer.write(response + " User=" + user.getNickName() + " ID="
				+ user.getId());
	}
}
