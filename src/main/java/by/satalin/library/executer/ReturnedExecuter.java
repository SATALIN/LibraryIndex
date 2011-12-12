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
import by.satalin.library.datamodel.User;
import by.satalin.library.exceptions.AlreadyReturnedException;
import by.satalin.library.exceptions.BaseException;
import by.satalin.library.exceptions.BooksNotFoundException;
import by.satalin.library.exceptions.MismatchAbonentException;
import by.satalin.library.exceptions.RequiredParameterMissedException;

/**
 * @author SATALIN
 * 
 */
public class ReturnedExecuter extends CommandExecuter {
	private static final Logger log = Logger.getLogger(ReturnedExecuter.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see by.satalin.library.worker.Worker#perform()
	 */
	@Override
	public CommandResponse perform(CommandVO command, List<Library> libraries) {
		CommandResponse commandResponse = new CommandResponse();

		try {
			validate(command);
			String id = command.getParameterByKey(ID);
			log.info("is validate = true : ID=" + id);
			commandResponse.setCommand(command);
			Book book = findBookByParams(command.getParameterByKey(ID),
					libraries);
			commandResponse.putParameters(PARAMETER_BOOK,
					updateInternalData(book, command.getUser()));
			commandResponse.addParameters(addListBooksToResponse(command,
					libraries));
		} catch (BaseException e) {
			log.error(e);
			commandResponse.setException(e);
		}
		return commandResponse;
	}

	private Book updateInternalData(Book book, User user)
			throws AlreadyReturnedException, MismatchAbonentException {
		if (book.getDate() != null) {
			if (!user.getId().equals(book.getAbonent())) {
				throw new MismatchAbonentException(
						"You can't return this book id=" + book.getId());
			}
			book.setDate(null);
			book.setAbonent(null);
		} else {
			throw new AlreadyReturnedException("book already returned id="
					+ book.getId());
		}
		return book;
	}

	@Override
	protected void validate(CommandVO command)
			throws RequiredParameterMissedException {
		String paramId = command.getParameterByKey(ID);
		validateRequiredParameter(paramId, ID);
		validateRequiredNumParameter(paramId, ID);
	}

	@Override
	protected List<Book> findByParams(String author, String title,
			List<Library> Libraries) throws BooksNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Book findBookByParams(String id, List<Library> Libraries)
			throws BooksNotFoundException {
		for (Library library : Libraries) {
			for (Book book : library.getBooks()) {
				if (book.getId() == Integer.parseInt(id)) {
					return book;
				}
			}
		}
		throw new BooksNotFoundException("Book was not found : id=" + id);
	}
}
