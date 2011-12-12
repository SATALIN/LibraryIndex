/**
 * 
 */
package by.satalin.library.library.implementation;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import by.satalin.config.Configuration;
import by.satalin.library.datamodel.Book;
import by.satalin.library.datamodel.Library;
import by.satalin.library.exceptions.BooksNotFoundException;
import by.satalin.library.exceptions.ChangesNotCompliteException;
import by.satalin.library.exceptions.LibraryNotFoundException;
import by.satalin.library.exceptions.ReadFileException;

/**
 * @author alexander.salin
 * base implementator for working with files
 */
public abstract class LibraryFileImplementator implements LibraryImplementator {
	private static final String ALL = "ALL";
	public static final String LIBRARY_LOCATION = "LibraryLocation";
	private static final Logger log = Logger.getLogger(LibraryFileImplementator.class);
	protected static final int COUNTER_ABONENT = 4;
	protected static final int COUNTER_DATE = 3;
	protected static final int COUNTER_TITLE = 2;
	protected static final int COUNTER_AUTHOR = 1;
	protected static final int COUNTER_ID = 0;

	public LibraryFileImplementator() {
		super();
	
	}

	@Override
	public abstract void insertChanges(Library library)
			throws ChangesNotCompliteException;

	protected abstract List<Book> getContents(File file,
			List<Book> oldFileList, Library library) throws ReadFileException;

	@Override
	public abstract List<Library> getLibraries(String path)
			throws  LibraryNotFoundException;

	protected String[] getFiles(String libraryName, String path) throws BooksNotFoundException {
		String dirName = path + "/" + libraryName;
		log.info("find library in "+dirName);
		File repositoryDir = new File(dirName);
		if (repositoryDir.isDirectory()) {
			String s[] = repositoryDir.list();
			return s;
		} else{
			throw new BooksNotFoundException("Books into Library="+libraryName+" not found");
		}
	}

	protected String getTypeFileLibrary(String name) {
		String[] fullName;
		String delimiter = "_";
		fullName = name.split(delimiter);
		return fullName[COUNTER_ID].toUpperCase();
	}

	protected String[] getRepositoryDirs(String path) throws LibraryNotFoundException {
		String dirName = path + "/";
		log.info("Get repository from path"+dirName);
		File repositoryMainDir = new File(dirName);
		if (repositoryMainDir.isDirectory()) {
			String libraresDirs[] = repositoryMainDir.list();
			return libraresDirs;
		} else
			throw new LibraryNotFoundException("library not faund; path="+dirName);
	}

	protected List<Book> getListBooks(Library library,String path) throws ReadFileException {
		List<Book> listBooks = new ArrayList<Book>();
		try{
		String[] listFiles = getFiles(library.getTitle(),path);
		for (String item : listFiles) {
			File file = new File(path + "/" + library.getTitle() + "/" + item);
			if (!file.isDirectory()) {
				listBooks = getContents(file, listBooks, library);
			}
		}	
		} catch (BooksNotFoundException e) {
			log.error(e);
			e.printStackTrace();
		}
		return listBooks;
	}

}
