/**
 * 
 */
package by.satalin.library.view;

import java.io.IOException;

import org.apache.log4j.Logger;

import by.satalin.library.command.CommandResponse;
import by.satalin.library.datamodel.Book;
import by.satalin.library.exceptions.AlreadyOrderException;
import by.satalin.library.exceptions.BaseException;
import by.satalin.library.exceptions.BooksNotFoundException;
import by.satalin.library.exceptions.RequiredParameterMissedException;
import by.satalin.library.executer.CommandExecuter;

/**
 * @author alexander.salin
 * 
 */
public class OrderOutputConsole extends OutputConsole {
	private static final Logger log = Logger
			.getLogger(OrderOutputConsole.class);

	@Override
	public void show(CommandResponse commandResponse) throws IOException {
		Book book = (Book) commandResponse
				.getResultParameters(CommandExecuter.PARAMETER_BOOK);
		if (commandResponse.getException() == null) {
			writer.write("OK abonent=" + book.getAbonent() + " date="
					+ book.getDate());
		} else {
			try {
				throw commandResponse.getException();
			} catch (BooksNotFoundException e) {
				writer.write("NOTFOUND");
				log.error(e);
			} catch (RequiredParameterMissedException e) {
				writer.write("SYNTAXERROR");
				log.error(e);
			} catch (AlreadyOrderException e) {
				writer.write("RESERVED abonent=" + book.getAbonent() + " date="
						+ book.getDate());
				log.error(e);
			} catch (BaseException e) {
				writer.write("error :" + e);
				log.error(e);
			}
		}
		writer.flush();
	}
}
