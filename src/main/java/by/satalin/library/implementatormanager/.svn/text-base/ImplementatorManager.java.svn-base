/**
 * 
 */
package by.satalin.library.implementatormanager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import org.apache.log4j.Logger;

import by.satalin.config.Configuration;
import by.satalin.library.datamodel.CsvLibrary;
import by.satalin.library.datamodel.Library;
import by.satalin.library.datamodel.TXTLibrary;

import by.satalin.library.exceptions.BaseException;
import by.satalin.library.exceptions.ChangesNotCompliteException;
import by.satalin.library.exceptions.LibraryNotFoundException;
import by.satalin.library.library.implementation.LibraryCsvFileImplementator;
import by.satalin.library.library.implementation.LibraryDaoImplementator;
import by.satalin.library.library.implementation.LibraryImplementator;
import by.satalin.library.library.implementation.LibraryTxtFileImplementator;

/**
 * @author alexander.salin choose type and implementator for working with book
 *         and library
 */
public class ImplementatorManager {
	private static final String ALL = "ALL";
	private static volatile ImplementatorManager instance;
	protected List<Library> libraries = new Vector<Library>();

	/**
	 * @return the libraries
	 * @throws LibraryNotFoundException
	 */
	public List<Library> getLibraries() throws LibraryNotFoundException {
		if (libraries.size() == 0)
			libraries = reread();
		return libraries;
	}

	public ImplementatorManager() {
		log.warn("Create ImplementatorManager :" + this.toString());
		log.warn("Create ImplementatorManager hashcode:" + this.hashCode());
	}

	private static final Logger log = Logger
			.getLogger(ImplementatorManager.class);
	private static Map<String, LibraryImplementator> classRegistry = new HashMap<String, LibraryImplementator>();
	static {
		classRegistry.put(TXTLibrary.class.getName(),
				new LibraryTxtFileImplementator());
		classRegistry.put(CsvLibrary.class.getName(),
				new LibraryCsvFileImplementator());
		classRegistry.put(Library.class.getName(),
				new LibraryDaoImplementator());
		// libraryRegistry.put(LIST_LIBRARIES, new ListLibraryExecuter());
	}

	public void flush() throws ChangesNotCompliteException {
		BaseException complitedException = null;
		for (Library library : libraries) {
			try{
			insertChanges(library);
			}catch (ChangesNotCompliteException e) {
				log.error(e);
				complitedException = e;
			}
		}
		if(complitedException!= null) throw new ChangesNotCompliteException("Not all operation complited: "+complitedException.toString());
	}

	private List<Library> reread() throws LibraryNotFoundException {
		List<Library> listLibraries = new Vector<Library>();
		String path = Configuration.getInstance().getProperty(ALL);
		for (LibraryImplementator libraryImplementator : classRegistry.values()) {
			try {
				listLibraries.addAll(libraryImplementator.getLibraries(path));
			} catch (LibraryNotFoundException e) {
				log.error(e);
			}
			log.info("Update list of libraries; size =" + listLibraries.size());
		}
		if (listLibraries.isEmpty()) {
			throw new LibraryNotFoundException("Libraries not found");
		}
		return listLibraries;

	}

	private void insertChanges(Library library)
			throws ChangesNotCompliteException {
		log.info("Try insert change to Library id=" + library.getId());
		classRegistry.get(library.getClass().getName()).insertChanges(library);

	}

	public static ImplementatorManager getInstance() {
		if (instance == null) {
			synchronized (ImplementatorManager.class) {
				if (instance == null) {
					instance = new ImplementatorManager();
				}
			}
		}
		return instance;
	}
}
