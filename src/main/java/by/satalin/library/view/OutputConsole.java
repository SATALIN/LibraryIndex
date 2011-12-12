/**
 * 
 */
package by.satalin.library.view;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.apache.log4j.Logger;

import by.satalin.library.command.CommandResponse;
import by.satalin.library.exceptions.ShowResponseException;

/**
 * @author alexander.salin
 * 
 */
public abstract class OutputConsole extends Outputer {
	private static final Logger log = Logger.getLogger(OutputConsole.class);
	protected BufferedWriter writer;

	public OutputConsole() {

		OutputStreamWriter writer = new OutputStreamWriter(System.out);

		this.writer = new BufferedWriter(writer);
	}

	public void showResponse(CommandResponse commandResponse) throws ShowResponseException {
		try {
			show(commandResponse); 
		} catch (IOException e) {
			log.error(e);
			throw new ShowResponseException(e.toString());
		}
	}

	protected abstract void show(CommandResponse commandResponse)
			throws IOException;
}
