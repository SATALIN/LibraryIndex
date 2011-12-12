/**
 * 
 */
package by.satalin.library.executer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import by.satalin.library.command.CommandResponse;
import by.satalin.library.command.CommandVO;
import by.satalin.library.datamodel.Book;
import by.satalin.library.datamodel.Library;
import by.satalin.library.exceptions.BooksNotFoundException;
import by.satalin.library.exceptions.RequiredParameterMissedException;
import by.satalin.library.security.Role;

/**
 * @author SATALIN
 * 
 */
public abstract class CommandExecuter  {
	public static final String PARAMETER_BOOKS = "books";
	public static final String PARAMETER_BOOK = "book";
	public static final String PARAMETER_LIBRARIES = "library";
	public static final String PARAMETER_USER = "user";
	public static final String PARAMETER_USERS = "users";
	protected static final String IS_RETURN_LIST= "IS_RETURN_LIST";
	protected static final String PARAMETER_GET_USER = "get_user";
	
	protected static final String NAME = "NAME";
	protected static final String AUTHOR = "AUTHOR";
	protected static final String ID = "ID";
	protected static final String ABONENT = "ABONENT";
	protected static final String ROLE = "ROLE";
	protected static final String LNAME = "LNAME";
	protected static final String FNAME = "FNAME";
	protected static final String PASSWORD = "PASSWORD";
	protected static final String NICKNAME = "NICKNAME";
	
	protected static final String ID_DB = "id";
	
	protected static final List<Role> roles = Arrays.asList(Role.ABONENT, Role.OPERATOR); 
	public abstract CommandResponse perform(CommandVO command, List<Library> Libraries);
	protected abstract List<Book> findByParams(String author, String title ,List<Library> Libraries) throws BooksNotFoundException;
	protected abstract Book findBookByParams(String id,List<Library> Libraries) throws BooksNotFoundException;
	protected abstract void validate(CommandVO com) throws RequiredParameterMissedException;
	
	protected void validateRequiredParameter(String string, String key) throws RequiredParameterMissedException {
		if (string == null) {
			throw new RequiredParameterMissedException("Required parameter missed:"+ key +"=null");
		}
	}
	protected void validateRequiredParameter(Role role, String key) throws RequiredParameterMissedException {
		if ((role == null)||(!roles.contains(role))) {
			throw new RequiredParameterMissedException("Required parameter missed:"+ key +"=null");
		}
	}
	protected void validateRequiredNumParameter(String string, String key) throws RequiredParameterMissedException {
		try{
		Long.parseLong(string);
		}
		catch (Exception e) {
			throw new RequiredParameterMissedException("Required parameter missed:"+ key +"=null");
		}		
	}
	protected void validateLengthRequiredParameter(String string, String key) throws RequiredParameterMissedException {
		if (string.length() == 0) {
			throw new RequiredParameterMissedException("Required parameter missed:"+ key +"=null");
		}
	}
	/**
	 * @param command
	 * @param libraries
	 * @param commandResponse
	 * @param parameters
	 */
	protected Map<String, Object> addListBooksToResponse(CommandVO command,
			List<Library> libraries) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		Boolean isReturnList = false;
		String str = command.getParameterByKey(IS_RETURN_LIST);
		if (str.length() > 0) {
			isReturnList = Boolean.valueOf(str);
		}
		if (isReturnList) { 
		
			parameters.put(PARAMETER_BOOKS,
					new FindExecuter().perform(command, libraries)
							.getResultParameters(PARAMETER_BOOKS));
		}
		return parameters;
	}
	
	protected Map<String, Object> addListUsersToResponse(CommandVO command) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		Boolean isReturnList = false;
		String str = command.getParameterByKey(IS_RETURN_LIST);
		if (str.length() > 0) {
			isReturnList = Boolean.valueOf(str);
		}
		if (isReturnList) { 
		
			parameters.put(PARAMETER_USERS,
					new ListUserExecuter().perform(command, null)
							.getResultParameters(PARAMETER_USERS));
		}
		return parameters;
	}
	protected Library findLibraryByParams(String id, List<Library> Libraries)
			throws BooksNotFoundException {
		for (Library library : Libraries) {
			for (Book book : library.getBooks()) {
				if (book.getId() == Integer.parseInt(id)) {
					return library;
				}
			}
		}
		throw new BooksNotFoundException("id:"+id);
	}
}
