/**
 * 
 */
package by.satalin.library.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import by.satalin.library.command.CommandResponse;
import by.satalin.library.datamodel.Book;
import by.satalin.library.executer.CommandExecuter;

/**
 * @author alexander.salin
 *
 */
public class FindOutputConsole extends OutputConsole {
	private static final Logger log = Logger.getLogger( FindOutputConsole.class);
	@Override
	protected void show(CommandResponse commandResponse) throws IOException {
		List<Book> books = new ArrayList<Book>();
		books = (List<Book>) commandResponse.getResultParameters(CommandExecuter.PARAMETER_BOOKS);

		if (books.size() == 0) {
			writer.write("NOTFOUND");
		} else {
			for (int i = 0; i < books.size(); i++) {
				String strItem = null;
				Book book = books.get(i);
				if (book.getDate() != null) {
					strItem = "FOUNDMISSING id=" + book.getId() + " lib="
							+ book.getTitle() + " issued=" + book.getDate();	
				} else {
					strItem = "FOUND id=" + book.getId() + " lib="
							+ book.getTitle();
				}
				log.info(strItem);
				writer.write(strItem);
				writer.newLine();
			}
		}

		writer.flush();
		
	}

}
