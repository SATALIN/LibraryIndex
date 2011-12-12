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
import java.util.List;

import org.apache.log4j.Logger;

import by.satalin.library.datamodel.Book;
import by.satalin.library.datamodel.FileBook;
import by.satalin.library.datamodel.Library;
import by.satalin.library.datamodel.TXTLibrary;
import by.satalin.library.datamodel.TxtFileBook;
import by.satalin.library.exceptions.ChangesNotCompliteException;
import by.satalin.library.exceptions.LibraryNotFoundException;
import by.satalin.library.exceptions.ReadFileException;

/**
 * @author alexander.salin implementator for working with TXT files
 */
public class LibraryTxtFileImplementator extends LibraryFileImplementator {
	private static final String DD_MM_YYYY = "dd/MM/yyyy";
	private static final Logger log = Logger
			.getLogger(LibraryTxtFileImplementator.class);
	private static final String ISSUEDTO = "Issuedto=";
	private static final String ISSUED = "Issued=";
	private static final String NAME = "Name=";
	private static final String AUTHOR = "Author=";
	private static final String INDEX = "Index=";
	private static final String TXT = "TXT";
	/*
	 * (non-Javadoc)
	 * 
	 * @see by.satalin.library.library.implementation.LibraryFileImplementator#
	 * insertChanges(by.satalin.library.book.Book)
	 */
	private void writeItem(String item, BufferedWriter buffered)
			throws IOException {
		log.info("write item" + item);
		buffered.write(item);
		buffered.newLine();
	}

	@Override
	public void insertChanges(Library library)
			throws ChangesNotCompliteException {
		for (Book book: library.getBooks()){
		   saveBook(book);
		}
	}

	/**
	 * @param book
	 * @return
	 * @throws ChangesNotCompliteException
	 */
	private void saveBook(Book book)
			throws ChangesNotCompliteException {
		File file = new File(((FileBook) book).getFileName());
		SimpleDateFormat dateFormat = new SimpleDateFormat(DD_MM_YYYY);
		BufferedWriter buffered = null;
		try {
			if (file.exists()) {
				file.delete();
				file.createNewFile();
				buffered = new BufferedWriter(new FileWriter(file));
				writeItem(INDEX + book.getId(), buffered);
				writeItem(AUTHOR + book.getAuthor(), buffered);
				writeItem(NAME + book.getTitle(), buffered);
				if (book.getAbonent() != null) {
					writeItem(ISSUED + dateFormat.format(book.getDate()), buffered);
					writeItem(ISSUEDTO + book.getAbonent(), buffered);
				}
			}

		} catch (IOException ex) {
			log.error(ex);
			throw new ChangesNotCompliteException("Insert changes faild -"
					+ book.getTitle());

		} finally {
			if (buffered != null) {
				try {
					buffered.close();
				} catch (IOException e) {
					log.error(e);
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public  List<Library> getLibraries(String path) throws LibraryNotFoundException  {
		List<Library> repositories = new ArrayList<Library>();
		try {
			String[] libraryDirs = getRepositoryDirs(path);
			for (Integer i = COUNTER_ID; i < libraryDirs.length; i++) {
				if (getTypeFileLibrary(libraryDirs[i]).contains(TXT)) {
					Library library = new TXTLibrary();
					library.setId(i.longValue());
					library.setTitle(libraryDirs[i]);
					library.setBooks(getListBooks(library,path));
					repositories.add(library);
					library = null;
				}
			}
		} catch (ReadFileException e) {
			log.error(e);
			e.printStackTrace();
		}
		return repositories;
	}

	protected synchronized List<Book> getContents(File file, List<Book> oldFileList,
			Library library) throws ReadFileException  {
		FileBook book = new TxtFileBook();
		BufferedReader bufferedReader = null;
		try {
			// use buffering, reading one line at a time
			bufferedReader = new BufferedReader(new FileReader(file));
			log.info("Get book from library:" + library.getTitle());
			String line = null; // not declared within while loopdrf
			for (int i = COUNTER_ID; i < 5; i++) {
				if ((line = bufferedReader.readLine()) != null) {
					switch (i) {
					case COUNTER_ID: {
						book.setId(Long.parseLong(line.replaceFirst(INDEX, "")));
					}
						break;
					case COUNTER_AUTHOR: {
						book.setAuthor(line.replaceFirst(AUTHOR, ""));
					}
						break;
					case COUNTER_TITLE: {
						book.setTitle(line.replaceFirst(NAME, ""));
					}
						break;
					case COUNTER_DATE: {
						SimpleDateFormat dateFormat = new SimpleDateFormat(DD_MM_YYYY);
						try {
							book.setDate(dateFormat.parse(line.replaceFirst(ISSUED, "")));
						} catch (ParseException e) {
							log.error(e);
							throw new ReadFileException(e.toString());
						}
					}
						break;
					case COUNTER_ABONENT: {
						book.setAbonent(Long.valueOf(line.replaceFirst(ISSUEDTO, "")));
					}
						break;
					default:

						break;
					}
				}
			}

		} catch (Exception ex) {
			throw new ReadFileException(ex.getMessage());
		} finally {
			try {
				bufferedReader.close();
			} catch (IOException e) {
				log.error(e);
				e.printStackTrace();
			}
		}
		book.setFileName(file.getAbsolutePath());
		log.info("===book filename=" + book.toString());
		oldFileList.add(book);
		return oldFileList;
	}
}
