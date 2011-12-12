/**
 * 
 */
package by.satalin.library.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import by.satalin.library.command.CommandResponse;
import by.satalin.library.datamodel.Library;
import by.satalin.library.executer.CommandExecuter;

/**
 * @author alexander.salin
 * 
 */
public class LibraryOutputConsole extends OutputConsole {
	private static final Logger log = Logger
			.getLogger(LibraryOutputConsole.class);
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * by.satalin.library.view.OutputConsole#showResponse(by.satalin.library
	 * .command.CommandResponse)
	 */
	@Override
	public void show(CommandResponse commandResponse) throws IOException {
		List<Library> libraries = new ArrayList<Library>();
		libraries = (List<Library>) commandResponse
				.getResultParameters(CommandExecuter.PARAMETER_LIBRARIES);
		if (libraries.size() == 0) {
			writer.write("NOTFOUND");
			log.info("NOTFOUND");
		} else {
			for (Library library : libraries) {
				String strItem = null;
				strItem = "FOUND Library title=" + library.getTitle()
						+ " count book=" + library.getBooks().size();
				writer.write(strItem);
				log.info(strItem);
				writer.newLine();
			}
		}

		writer.flush();
	}

}
