/**
 * 
 */
package by.satalin.library.main;



import org.apache.log4j.Logger;

import by.satalin.library.builder.CommandBuilder;
import by.satalin.library.command.CommandResponse;
import by.satalin.library.command.CommandVO;
import by.satalin.library.exceptions.BaseException;
import by.satalin.library.exceptions.ShowResponseException;
import by.satalin.library.executer.CommandExecuter;
import by.satalin.library.implementatormanager.ImplementatorManager;
import by.satalin.library.view.Outputer;

/**
 * @author alexander.salin
 * the core of application, execute all base functions . 
 */
public class LauncherCore {
	private static final Logger log = Logger.getLogger(LauncherCore.class);
	public void launch(CommandVO command) {
		log.info("start core...");
		log.info("Command "+command.getName());
		CommandExecuter executer;
		CommandResponse cmdResponse = new CommandResponse();
		CommandBuilder builder = new CommandBuilder();
		Outputer outputer = null;
		try {
			executer = builder.getExecuter(command);
			log.info("Executer is "+executer.getClass().getSimpleName());
			outputer = builder.getOutputConsole(command);
			log.info("Outputer is "+outputer.getClass().getSimpleName());
			cmdResponse = executer.perform(command,
					ImplementatorManager.getInstance().getLibraries());
		} catch (BaseException e) {
			log.error(e);
			cmdResponse.setException(e);
		}
		// Because outputer must show response with exception
		try {
			outputer.showResponse(cmdResponse);
		} catch (ShowResponseException e) {
			log.error(e);
			outputer.showException(e);
			e.printStackTrace();
		}
		log.info("finish core...");
	}
public CommandResponse launchForResponse(CommandVO command){
	log.info("start core...");
	log.info("Command "+command.getName());
	CommandExecuter executer;
	CommandResponse cmdResponse = new CommandResponse();
	CommandBuilder builder = new CommandBuilder();
	try {
		executer = builder.getExecuter(command);
		log.info("Executer is "+executer.getClass().getSimpleName());
		cmdResponse = executer.perform(command,
				ImplementatorManager.getInstance().getLibraries());
	} catch (BaseException e) {
		log.error(e);
		cmdResponse.setException(e);
	}
	log.info("stop core...");
	return cmdResponse;
}
}
