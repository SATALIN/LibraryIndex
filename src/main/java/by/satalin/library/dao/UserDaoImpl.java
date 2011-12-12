/**
 * 
 */
package by.satalin.library.dao;

import by.satalin.library.dao.impl.GenericDao;
import by.satalin.library.datamodel.User;

/**
 * @author alexander.salin
 *
 */
public class UserDaoImpl extends GenericDao<User> {

	public UserDaoImpl() {
	super();	
	 setPersistentClass(User.class);
	}

}
