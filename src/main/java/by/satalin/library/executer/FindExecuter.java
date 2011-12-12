/**
 * 
 */
package by.satalin.library.executer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import by.satalin.library.command.CommandResponse;
import by.satalin.library.command.CommandVO;
import by.satalin.library.datamodel.Book;
import by.satalin.library.datamodel.FileBook;
import by.satalin.library.datamodel.Library;
import by.satalin.library.exceptions.BaseException;
import by.satalin.library.exceptions.BooksNotFoundException;
import by.satalin.library.exceptions.RequiredParameterMissedException;

/**
 * @author SATALIN
 * 
 */
public class FindExecuter extends CommandExecuter {
	private static final Logger log = Logger.getLogger(FindExecuter.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see by.satalin.library.worker.Worker#perform()
	 */
	@Override
	public CommandResponse perform(CommandVO command, List<Library> Libraries) {
		CommandResponse commandResponse = new CommandResponse();
		try {
			validate(command);
			String author = command.getParameterByKey(AUTHOR);
			String title = command.getParameterByKey(NAME);
			log.info("is validate = true - author=" + author + " name=" + title);
			commandResponse.setCommand(command);
			commandResponse.putParameters(PARAMETER_BOOKS,
					findByParams(author, title, Libraries));
		} catch (BaseException e) {
			log.error(e);
			commandResponse.setException(e);
		}

		return commandResponse;
	}

	@Override
	protected List<Book> findByParams(String author, String title,
			List<Library> Libraries) throws BooksNotFoundException {
		log.info("Start find time:"+(Calendar.getInstance().getTimeInMillis()));
		List<Book> returnBooks = new ArrayList<Book>();
		for (Library library : Libraries) {
			for (Book book : library.getBooks()) {
				if (matchBook(book, author, title))
					returnBooks.add(book);
			}
		}
		log.info("Stop find time:"+(Calendar.getInstance().getTimeInMillis()));
		if (returnBooks.isEmpty()) {
			throw new BooksNotFoundException("Books was not found : author="
					+ author + "  Name=" + title);
		}
		log.info("found count=" + returnBooks.size() + " books");
		return returnBooks;
	}

	private boolean matchBook(Book book, String author, String title) {
		boolean flag = false;
		if (author == null) {
			if (book.getTitle().toUpperCase().contains(title.toUpperCase()))
				flag = true;
		} else if (title == null) {
			if (book.getAuthor().toUpperCase().contains(author.toUpperCase()))
				flag = true;
		} else {
			if ((book.getTitle().toUpperCase().contains(title.toUpperCase()))
					&& (book.getAuthor().toUpperCase().contains(author
							.toUpperCase())))
				flag = true;
		}
		return flag;
	}

	@Override
	protected void validate(CommandVO command)
			throws RequiredParameterMissedException {
		try {
			validateRequiredParameter(
					(String) command.getParameterByKey(AUTHOR), AUTHOR);
		} catch (RequiredParameterMissedException e) {
			validateRequiredParameter((String) command.getParameterByKey(NAME),
					NAME);
		}

	}

	@Override
	protected FileBook findBookByParams(String id, List<Library> Libraries)
			throws BooksNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
