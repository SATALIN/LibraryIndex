/**
 * 
 */
package by.satalin.library.builder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import by.satalin.library.builder.CommandBuilder;
import by.satalin.library.command.CommandVO;
import by.satalin.library.exceptions.AccessDeniedLowPermissionException;
import by.satalin.library.exceptions.OutputerNotFoundException;
import by.satalin.library.exceptions.ExecuterNotFoundException;
import by.satalin.library.executer.*;
import by.satalin.library.security.Role;
import by.satalin.library.view.FindOutputConsole;
import by.satalin.library.view.LibraryOutputConsole;
import by.satalin.library.view.OrderOutputConsole;
import by.satalin.library.view.Outputer;
import by.satalin.library.view.ReturnedOutputConsole;
import by.satalin.library.view.UserListOutputConsole;
import by.satalin.library.view.UserOperationOutputConsole;

/**
 * @author SATALIN builder for outputer and executer by command
 */
public class CommandBuilder {
	public static final Logger log = Logger.getLogger(CommandBuilder.class);
	public static final String LISTUSER = "LISTUSER";
	public static final String DELETEUSER = "DELETEUSER";
	public static final String EDITUSER = "EDITUSER";
	public static final String GETUSER = "GETUSER";	
	public static final String FIND = "FIND";
	public static final String ORDER = "ORDER";
	public static final String RETURN = "RETURN";
	public static final String LIST_LIBRARIES = "LIST";
	public static final String CREATEUSER = "CREATEUSER";
	protected static Map<String, List<Role>> roleRegistry = new HashMap<String, List<Role>>();
	static {
		roleRegistry.put(FIND, Arrays.asList(Role.ABONENT, Role.OPERATOR));
		roleRegistry.put(ORDER, Arrays.asList(Role.OPERATOR));
		roleRegistry.put(RETURN, Arrays.asList(Role.OPERATOR));
		roleRegistry.put(CREATEUSER, Arrays.asList(Role.OPERATOR));
		roleRegistry.put(EDITUSER, Arrays.asList(Role.OPERATOR));
		roleRegistry.put(GETUSER, Arrays.asList(Role.OPERATOR));
		roleRegistry.put(DELETEUSER, Arrays.asList(Role.OPERATOR));
		roleRegistry.put(LISTUSER, Arrays.asList(Role.OPERATOR));
		roleRegistry.put(LIST_LIBRARIES,
				Arrays.asList(Role.ABONENT, Role.OPERATOR));
	}
	protected static Map<String, CommandExecuter> executerRegistry = new HashMap<String, CommandExecuter>();
	static {
		executerRegistry.put(FIND, new FindExecuter());
		executerRegistry.put(ORDER, new OrderExecuter());
		executerRegistry.put(RETURN, new ReturnedExecuter());
		executerRegistry.put(CREATEUSER, new CreateUserExecuter());
		executerRegistry.put(EDITUSER, new EditUserExecuter());
		executerRegistry.put(GETUSER, new FindUserByIdExecuter());
		executerRegistry.put(DELETEUSER, new DeleteUserExecuter());
		executerRegistry.put(LISTUSER, new ListUserExecuter());
		executerRegistry.put(LIST_LIBRARIES, new ListLibraryExecuter());
	}
	protected static Map<String, Outputer> consoleRegistry = new HashMap<String, Outputer>();
	static {
		consoleRegistry.put(FIND, new FindOutputConsole());
		consoleRegistry.put(ORDER, new OrderOutputConsole());
		consoleRegistry.put(RETURN, new ReturnedOutputConsole());
		consoleRegistry.put(LIST_LIBRARIES, new LibraryOutputConsole());
		consoleRegistry.put(EDITUSER, new UserOperationOutputConsole());
		consoleRegistry.put(DELETEUSER, new UserOperationOutputConsole());
		consoleRegistry.put(CREATEUSER, new UserOperationOutputConsole());
		consoleRegistry.put(LISTUSER, new UserListOutputConsole());
	}

	public CommandBuilder() {

	}

	/**
	 * @param command
	 * @return
	 * @throws ExecuterNotFoundException
	 * @throws AccessDeniedLowPermissionException
	 */
	public CommandExecuter getExecuter(CommandVO command)
			throws ExecuterNotFoundException,
			AccessDeniedLowPermissionException {
		checkPermission(command);
		CommandExecuter executer = executerRegistry.get(command.getName());
		if (executer != null) {
			return executer;

		} else {
			log.fatal("Executer not found - " + command.getName());
			throw new ExecuterNotFoundException(
					"Executer not found. Enter right request. -"
							+ command.getName());
		}
	}

	private void checkPermission(CommandVO command)
			throws AccessDeniedLowPermissionException, ExecuterNotFoundException {
		if (!roleRegistry.containsKey(command.getName())) throw new ExecuterNotFoundException("Commamd: "+command.getName()+" dont have executer");
		if (! roleRegistry.get(command.getName()).contains(command.getRole()))
			throw new AccessDeniedLowPermissionException("Low permission:"
					+ command.getRole());
	}

	/**
	 * @param command
	 * @return
	 * @throws OutputerNotFoundException
	 */
	public Outputer getOutputConsole(CommandVO command)
			throws OutputerNotFoundException {
		Outputer console = consoleRegistry.get(command.getName());
		if (console != null) {
			return console;

		} else {
			log.fatal("Outputer not found - " + command.getName());
			throw new OutputerNotFoundException(
					"Printer not found not found. Enter right request. -"
							+ command.getName());
		}
	}
}
