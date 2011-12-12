package by.satalin.library.view;

import java.io.IOException;

import org.apache.log4j.Logger;

import by.satalin.library.command.CommandResponse;
import by.satalin.library.exceptions.AlreadyReturnedException;
import by.satalin.library.exceptions.BaseException;
import by.satalin.library.exceptions.BooksNotFoundException;
import by.satalin.library.exceptions.RequiredParameterMissedException;

public class ReturnedOutputConsole extends OutputConsole {
	private static final Logger log = Logger.getLogger(ReturnedOutputConsole.class);
	@Override
	public void show(CommandResponse commandResponse) throws IOException {
		if (commandResponse.getException() == null) {
			writer.write("OK abonent=" + commandResponse.getCommand().getUser().getNickName());
		} else {
			try {
				throw commandResponse.getException();
			} catch (BooksNotFoundException e) {
				writer.write("NOTFOUND");
				log.error(e);
			} catch (RequiredParameterMissedException e) {
				writer.write("SYNTAXERROR");
				log.error(e);
			} catch (AlreadyReturnedException e) {
				writer.write("ALREADYRETURNED");
				log.error(e);
			} catch (BaseException e) {
				writer.write("error :" + e);
				log.error(e);
			}
		}
		writer.flush();
	}
}
