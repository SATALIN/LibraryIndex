/**
 * 
 */
package by.satalin.library.executer;

import java.util.Calendar;
import java.util.List;
import org.apache.log4j.Logger;
import by.satalin.library.command.CommandResponse;
import by.satalin.library.command.CommandVO;
import by.satalin.library.datamodel.Book;
import by.satalin.library.datamodel.FileBook;
import by.satalin.library.datamodel.Library;
import by.satalin.library.datamodel.User;
import by.satalin.library.exceptions.AlreadyOrderException;
import by.satalin.library.exceptions.BaseException;
import by.satalin.library.exceptions.BooksNotFoundException;
import by.satalin.library.exceptions.RequiredParameterMissedException;

/**
 * @author SATALIN
 * 
 */
public class OrderExecuter extends CommandExecuter {
	private static final Logger log = Logger.getLogger(OrderExecuter.class);

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
			String abonent = command.getUser().getNickName();
			String id = command.getParameterByKey(ID);
			log.info("is validate=true  :  ID=" + id + " abonent=" + abonent);
			Book book = new FileBook();
			book = findBookByParams(id, libraries);
			updateInternalData(book, command.getUser());
			commandResponse.setCommand(command);
			commandResponse.putParameters(PARAMETER_BOOK, book);
			commandResponse.addParameters(addListBooksToResponse(command,
					libraries));
		} catch (BaseException e) {
			log.error(e);
			commandResponse.setException(e);
		}

		return commandResponse;
	}

	private void updateInternalData(Book book, User user)
			throws AlreadyOrderException {
		if (book.getDate() == null) {
			book.setAbonent(user.getId());
			book.setDate(Calendar.getInstance().getTime());
		} else {
			throw new AlreadyOrderException("Book reserved date ="
					+ book.getDate());
		}
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
