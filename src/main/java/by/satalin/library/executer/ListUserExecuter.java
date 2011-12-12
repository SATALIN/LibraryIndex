/**
 * 
 */
package by.satalin.library.executer;

import java.util.List;
import org.apache.log4j.Logger;
import by.satalin.library.command.CommandResponse;
import by.satalin.library.command.CommandVO;
import by.satalin.library.dao.UserDaoImpl;
import by.satalin.library.datamodel.Book;
import by.satalin.library.datamodel.Library;
import by.satalin.library.datamodel.User;
import by.satalin.library.exceptions.BooksNotFoundException;
import by.satalin.library.exceptions.RequiredParameterMissedException;

/**
 * @author alexander.salin
 *
 */
public class ListUserExecuter extends CommandExecuter {
	private static final Logger log = Logger.getLogger(ListUserExecuter.class);
	/* (non-Javadoc)
	 * @see by.satalin.library.executer.CommandExecuter#perform(by.satalin.library.command.CommandVO, java.util.List)
	 */
	@Override
	public CommandResponse perform(CommandVO command, List<Library> Libraries) {
		CommandResponse commandResponse = new CommandResponse();
		UserDaoImpl userImpl = new UserDaoImpl();
		List<User> userList = userImpl.getList();
		commandResponse.setCommand(command);
		commandResponse.putParameters(PARAMETER_USERS, userList);
		return commandResponse;
	}

	/* (non-Javadoc)
	 * @see by.satalin.library.executer.CommandExecuter#findByParams(java.lang.String, java.lang.String, java.util.List)
	 */
	@Override
	protected List<Book> findByParams(String author, String title,
			List<Library> Libraries) throws BooksNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see by.satalin.library.executer.CommandExecuter#findByParams(java.lang.String, java.util.List)
	 */
	@Override
	protected Book findBookByParams(String id, List<Library> Libraries)
			throws BooksNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see by.satalin.library.executer.CommandExecuter#validate(by.satalin.library.command.CommandVO)
	 */
	@Override
	protected void validate(CommandVO com)
			throws RequiredParameterMissedException {
		// TODO Auto-generated method stub

	}

}
