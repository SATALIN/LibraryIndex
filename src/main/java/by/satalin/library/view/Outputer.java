/**
 * 
 */
package by.satalin.library.view;

import org.apache.log4j.Logger;

import by.satalin.library.command.CommandResponse;
import by.satalin.library.exceptions.BaseException;
import by.satalin.library.exceptions.ShowResponseException;

/**
 * @author alexander.salin
 *
 */
public abstract class Outputer {
	private static final Logger log = Logger.getLogger(Outputer.class);
	public abstract void showResponse(CommandResponse commandResponse) throws ShowResponseException;
	public void showException(BaseException e){
		log.fatal(e.toString());
		System.out.println(e);
	}
}
