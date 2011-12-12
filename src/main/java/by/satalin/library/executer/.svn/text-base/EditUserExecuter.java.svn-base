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
import by.satalin.library.exceptions.BaseException;
import by.satalin.library.exceptions.BooksNotFoundException;
import by.satalin.library.exceptions.ChangesNotCompliteException;
import by.satalin.library.exceptions.RequiredParameterMissedException;
import by.satalin.library.exceptions.UserNotFoundException;
import by.satalin.library.security.Role;

/**
 * @author alexander.salin
 * 
 */
public class EditUserExecuter extends CommandExecuter {
	private static final Logger log = Logger.getLogger(EditUserExecuter.class);

	@Override
	public CommandResponse perform(CommandVO command, List<Library> Libraries) {
		CommandResponse commandResponse = new CommandResponse();
		try {
			validate(command);
			User user = updateUser(command);
			commandResponse.setCommand(command);
			commandResponse.putParameters(PARAMETER_USER, user);
		} catch (BaseException e) {
			log.error(e);
			commandResponse.setException(e);
		}

		return commandResponse;
	}

	/**
	 * @param command
	 * @return
	 * @throws UserNotFoundException
	 * @throws ChangesNotCompliteException
	 */
	private User updateUser(CommandVO command) throws UserNotFoundException,
			ChangesNotCompliteException {
		final String id = command.getParameterByKey(ID);
		log.info("is validate=true  :  id=" + id);
		UserDaoImpl userImpl = new UserDaoImpl();
		User user = userImpl.get(Long.parseLong(id));
		user.setNickName(command.getParameterByKey(NICKNAME));
		user.setFirstName(command.getParameterByKey(FNAME));
		user.setLastName(command.getParameterByKey(LNAME));
		user.setPassword(command.getParameterByKey(PASSWORD));
		user.setRole(Role.valueOf(command.getParameterByKey(ROLE)));
		userImpl.saveOrUpdate(user);
		return user;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * by.satalin.library.executer.CommandExecuter#findByParams(java.lang.String
	 * , java.lang.String, java.util.List)
	 */
	@Override
	protected List<Book> findByParams(String author, String title,
			List<Library> Libraries) throws BooksNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * by.satalin.library.executer.CommandExecuter#findByParams(java.lang.String
	 * , java.util.List)
	 */
	@Override
	protected Book findBookByParams(String id, List<Library> Libraries)
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
	protected void validate(CommandVO command)
			throws RequiredParameterMissedException {
		validateRequiredParameter(command.getParameterByKey(ID), ID);
		validateRequiredNumParameter(command.getParameterByKey(ID), ID);
	}

}
