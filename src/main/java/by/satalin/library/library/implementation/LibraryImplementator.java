/**
 * 
 */
package by.satalin.library.library.implementation;

import java.util.List;
import by.satalin.library.datamodel.Library;
import by.satalin.library.exceptions.ChangesNotCompliteException;
import by.satalin.library.exceptions.LibraryNotFoundException;

/**
 * @author alexander.salin
 *  base implementator for all types of library
 */

public interface LibraryImplementator {
	public void insertChanges(Library library)
			throws ChangesNotCompliteException;
	
/**
 * 
 * @return
 * @throws LibraryNotFoundException
 */
	public  List<Library> getLibraries(String path) throws LibraryNotFoundException;
}
