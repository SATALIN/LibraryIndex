/**
 * 
 */
package by.satalin.library.library.implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

import by.satalin.config.Configuration;
import by.satalin.library.datamodel.Book;
import by.satalin.library.datamodel.CsvFileBook;
import by.satalin.library.datamodel.CsvLibrary;
import by.satalin.library.datamodel.FileBook;
import by.satalin.library.datamodel.Library;
import by.satalin.library.exceptions.ChangesNotCompliteException;
import by.satalin.library.exceptions.LibraryNotFoundException;
import by.satalin.library.exceptions.ReadFileException;
import by.satalin.library.exceptions.config.ConfigNotFoundException;

/**
 * @author alexander.salin implementator for working with CVS files
 */
public class LibraryCsvFileImplementator extends LibraryFileImplementator {

	private static final String DD_MM_YYYY = "dd/MM/yyyy";
	private static final Logger log = Logger
			.getLogger(LibraryCsvFileImplementator.class);
	private static final String SEPARATOR = ",";

	private static final String CVS = "CVS";

	/*
	 * (non-Javadoc)
	 * 
	 * @see by.satalin.library.library.implementation.LibraryFileImplementator#
	 * insertChanges(by.satalin.library.book.Book)
	 */
	@Override
	public void insertChanges(Library library)
			throws ChangesNotCompliteException {
		File file=null;
		if  (library.getBooks().size()>0){
		file = new File(((FileBook) library.getBooks().get(0)).getFileName());
		}
		try {
			if (file.exists()) {
				file.delete();
				file.createNewFile();
				StringBuilder contents = new StringBuilder();
				for (Book book:library.getBooks()){
				contents.append(generateNewBook(book));
				contents.append(System.getProperty("line.separator"));
				}
				// add all books to file and insert new book
				addAllBooksToFile(file, contents);
		
			}
		} catch (IOException e) {
			log.error(e);
			throw new ChangesNotCompliteException("Insert changes faild -"
					+ library.getTitle()+e.toString());
		}

	}

	/**
	 * @param file
	 * @param contents
	 * @param outText
	 * @throws IOException
	 */
	private void addAllBooksToFile(File file, StringBuilder contents)
			throws IOException {
		BufferedWriter bufferedWriter = null;
		try {
			bufferedWriter = new BufferedWriter(new FileWriter(file));
			String text = contents.toString();
			bufferedWriter.write(text);
		} finally {
			if (bufferedWriter != null) {
				bufferedWriter.close();
			}
		}
	}

	/**
	 * @param book
	 * @return
	 */
	private String generateNewBook(Book book) {
		String outText;
		
		SimpleDateFormat dateFormat = new SimpleDateFormat(DD_MM_YYYY);
		outText = book.getId() + SEPARATOR + book.getAuthor() + SEPARATOR
				+ book.getTitle();
		if (book.getAbonent() != null) {
			outText = outText + SEPARATOR +dateFormat.format(new Date())
					+ SEPARATOR + book.getAbonent();
		}
		return outText;
	}

	/**
	 * @param book
	 * @param contents
	 * @param bufferedReader
	 * @throws IOException
	 */
	private void getBooksFromFile(Book book, StringBuilder contents,
			BufferedReader bufferedReader) throws IOException {
		String line = null;
		while ((line = bufferedReader.readLine()) != null) {
			if (!line.contains(Long.toString(book.getId()))) {
				contents.append(line);
				contents.append(System.getProperty("line.separator"));
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see by.satalin.library.library.implementation.LibraryFileImplementator#
	 * getLibraries()
	 */
	@Override
	public  List<Library> getLibraries(String path) throws LibraryNotFoundException {
		List<Library> libraries = new ArrayList<Library>();
			String[] libraryDirs = getRepositoryDirs(path);

			for (Integer i = COUNTER_ID; i < libraryDirs.length; i++) {
				try{
				if (getTypeFileLibrary(libraryDirs[i]).contains(CVS)) {
					Library library = new CsvLibrary();
					library.setId(i.longValue());
					library.setTitle(libraryDirs[i]);
					library.setBooks(getListBooks(library, path));
					libraries.add(library);
					library = null;
				}
				}catch (ReadFileException e) {
					log.error(e);
					//if file can't read
				}
			}
	
		return libraries;
	}

	protected synchronized List<Book> getContents(File file, List<Book> oldFileList,
			Library library) throws ReadFileException {
		BufferedReader csvFile = null;
		try {
			// use buffering, reading one line at a time
			csvFile = new BufferedReader(new FileReader(file));
			String dataLine = csvFile.readLine();
			while (dataLine != null) {
				String[] dataArray = dataLine.split(SEPARATOR);
				FileBook book = new CsvFileBook();
				book.setId(Long.parseLong(dataArray[COUNTER_ID]));
				book.setAuthor(dataArray[COUNTER_AUTHOR]);
				book.setTitle(dataArray[COUNTER_TITLE]);
				book.setFileName(file.getAbsolutePath());
				if (dataArray.length > COUNTER_DATE) {
					SimpleDateFormat dateFormat = new SimpleDateFormat(DD_MM_YYYY);
					try{
					book.setDate(dateFormat.parse(dataArray[COUNTER_DATE]));
					}catch (ParseException e) {
						log.error(e);
						throw new ReadFileException(e.toString());
					}
					book.setAbonent(Long.valueOf(dataArray[COUNTER_ABONENT]));	
				}
				if (log.isInfoEnabled()){ 
				log.info("Get book from library:" + book.toString());
				}
				oldFileList.add(book);
				dataLine = csvFile.readLine(); // Read next line of data.
			}

		} catch (IOException ex) {
			throw new ReadFileException(ex.getMessage());
		} finally {
			if (csvFile != null) {
				try {
					csvFile.close();
				} catch (IOException e) {
					log.error(e);
				}
			}
		}
		return oldFileList;
	}
}
