/**
 * 
 */
package by.satalin.library.library.implementation;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import by.satalin.library.dao.LibraryDaoImpl;
import by.satalin.library.datamodel.Book;
import by.satalin.library.datamodel.Library;
import by.satalin.library.exceptions.ChangesNotCompliteException;
import by.satalin.persist.HibernateUtils;

/**
 * @author alexander.salin implementator for working with database
 */
public class LibraryDaoImplementator implements LibraryImplementator {
	private static final Logger log = Logger
			.getLogger(LibraryDaoImplementator.class);

	
	@Override
	public  void insertChanges(Library library)
			throws ChangesNotCompliteException {
	LibraryDaoImpl daoImpl = new LibraryDaoImpl();
	//daoImpl.remove(library);
	//library.setId(Long.parseLong("0"));
	daoImpl.saveOrUpdate(library);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * by.satalin.library.library.implementation.LibraryImplementator#getLibraries
	 * ()
	 */
	@Override
	public synchronized List<Library> getLibraries(String path) {
		LibraryDaoImpl daoImpl = new LibraryDaoImpl();
		return daoImpl.getList();
	}

}
