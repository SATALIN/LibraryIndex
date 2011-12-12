/**
 * 
 */
package by.satalin.library.servises;

import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.satalin.library.command.CommandResponse;
import by.satalin.library.datamodel.Book;
import by.satalin.library.datamodel.Library;
import by.satalin.library.datamodel.User;
import by.satalin.library.executer.CommandExecuter;

/**
 * @author alexander.salin
 * 
 */
public class JspService {
	private static final String SAVE_RESPONSE = "SAVE_RESPONSE";
	private static final Logger log = Logger.getLogger(JspService.class);
	private Book book;
	private Library library;
	private List<Book> books;
	private List<Library> libraries;
	private User user;
	private List<User> users;
	private boolean execute;

	public JspService(HttpSession session) {
		CommandResponse commandResponse = (CommandResponse) session.getAttribute(SAVE_RESPONSE);
		if (commandResponse == null){
			setExecute(false);
		}else{
			setExecute(true);
			Object entity = new Object();
			try{
			entity = commandResponse.getResultParameters(CommandExecuter.PARAMETER_BOOK);
			setBook((Book) ((entity != null) ? entity : null ));
			
			entity = commandResponse.getResultParameters(CommandExecuter.PARAMETER_BOOKS);
			setBooks((List<Book>) ((entity != null) ? entity : null ));
			
			entity = commandResponse.getResultParameters(CommandExecuter.PARAMETER_LIBRARIES);
			setLibraries((List<Library>) ((entity != null) ? entity : null ));
			
			entity = commandResponse.getResultParameters(CommandExecuter.PARAMETER_USER);
			setUser((User) ((entity != null) ? entity : null ));
			
			entity = commandResponse.getResultParameters(CommandExecuter.PARAMETER_USERS);
			setUsers((List<User>) ((entity != null) ? entity : null ));
			}catch (Exception e) {
				log.error(e);
				setExecute(false);
			}
		}

	}
	/**
	 * @param session
	 */
	public void deleteResponseFromSession(HttpSession session) {
		session.setAttribute(SAVE_RESPONSE, null);
	}
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	private void setUser(User user) {
		this.user = user;
	}
	/**
	 * @return the users
	 */
	public List<User> getUsers() {
		if (users == null ) users = new Vector<User>();
		return users;
	}
	/**
	 * @param users the users to set
	 */
	private void setUsers(List<User> users) {
		this.users = users;
	}
	/**
	 * @return the book
	 */
	public Book getBook() {
		return book;
	}

	/**
	 * @param book the book to set
	 */
	private void setBook(Book book) {
		this.book = book;
	}

	/**
	 * @return the books
	 */
	public List<Book> getBooks() {
		if (books == null) books = new Vector<Book>();
		return books;
	}

	/**
	 * @param books the books to set
	 */
	private void setBooks(List<Book> books) {
		this.books = books;
	}

	/**
	 * @return the libraries
	 */
	public List<Library> getLibraries() {
		if (libraries == null) libraries = new Vector<Library>();
		return libraries;
	}

	/**
	 * @param libraries the libraries to set
	 */
	private void setLibraries(List<Library> libraries) {
		this.libraries = libraries;
	}

	/**
	 * @return the execute
	 */
	public boolean isExecute() {
		return execute;
	}

	/**
	 * @param execute the execute to set
	 */
	private void setExecute(boolean execute) {
		this.execute = execute;
	}
}