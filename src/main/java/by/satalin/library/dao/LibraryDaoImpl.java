/**
 * 
 */
package by.satalin.library.dao;

import by.satalin.library.dao.impl.GenericDao;
import by.satalin.library.datamodel.Library;

/**
 * @author alexander.salin
 *
 */
public class LibraryDaoImpl extends GenericDao<Library> {
	public LibraryDaoImpl() {
	super();	
	 setPersistentClass(Library.class);
	}
}
