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
import by.satalin.library.exceptions.RequiredParameterMissedException;
import by.satalin.library.exceptions.SelfDeleteException;

/**
 * @author alexander.salin
 *
 */
public class DeleteUserExecuter extends CommandExecuter {
	private static final Logger log = Logger.getLogger(DeleteUserExecuter.class);

	/* (non-Javadoc)
	 * @see by.satalin.library.executer.CommandExecuter#perform(by.satalin.library.command.CommandVO, java.util.List)
	 */
	@Override
	public CommandResponse perform(CommandVO command, List<Library> libraries) {
		CommandResponse commandResponse = new CommandResponse();
		try {
			validate(command);
			String id= command.getParameterByKey(ID);
			log.info("is validate=true  :  ID=" + id );
			UserDaoImpl userImpl = new UserDaoImpl();
			User user = userImpl.get(Long.parseLong(id));
			if (command.getUser().equals( user)) {
				commandResponse.setException( new SelfDeleteException("the user has deleted your account"));
			}
			userImpl.remove(user);
			commandResponse.setCommand(command);
			commandResponse.putParameters(PARAMETER_USER, user);
			commandResponse.addParameters(addListUsersToResponse(command));
		} catch (BaseException e) {
			log.error(e);
			commandResponse.setException(e);
		}

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
	protected void validate(CommandVO command)
			throws RequiredParameterMissedException {
		validateRequiredParameter(command.getParameterByKey(ID), ID);
		validateRequiredNumParameter(command.getParameterByKey(ID), ID);
	}

}
