/**
 * 
 */
package by.satalin.library.executer;

import java.util.List;
import org.apache.log4j.Logger;
import by.satalin.library.command.CommandResponse;
import by.satalin.library.command.CommandVO;
import by.satalin.library.datamodel.Book;
import by.satalin.library.datamodel.Library;
import by.satalin.library.exceptions.BooksNotFoundException;
import by.satalin.library.exceptions.RequiredParameterMissedException;

/**
 * @author alexander.salin
 * 
 */
public class ListLibraryExecuter extends CommandExecuter {
	private static final Logger log = Logger
			.getLogger(ListLibraryExecuter.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * by.satalin.library.executer.CommandExecuter#perform(by.satalin.library
	 * .command.CommandVO)
	 */
	@Override
	public CommandResponse perform(CommandVO command, List<Library> libraries) {
		CommandResponse commandResponse = new CommandResponse();
		commandResponse.setCommand(command);
		commandResponse.putParameters(PARAMETER_LIBRARIES, libraries);
		return commandResponse;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * by.satalin.library.executer.CommandExecuter#findByParams(java.lang.String
	 * , java.lang.String)
	 */
	@Override
	protected List<Book> findByParams(String author, String title,
			List<Library> libraries) throws BooksNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * by.satalin.library.executer.CommandExecuter#findByParams(java.lang.String
	 * )
	 */
	@Override
	protected Book findBookByParams(String id, List<Library> libraries)
			throws BooksNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * by.satalin.library.executer.CommandExecuter#validate(by.satalin.library
	 * .command.CommandVO)
	 */
	@Override
	protected void validate(CommandVO com)
			throws RequiredParameterMissedException {
		// TODO Auto-generated method stub

	}

}
