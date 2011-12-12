/**
 * 
 */
package by.satalin.library.servises;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import by.satalin.library.dao.UserDaoImpl;
import by.satalin.library.dao.interfaces.IGenCriteria;
import by.satalin.library.datamodel.User;
import by.satalin.library.exceptions.UserNotFoundException;

/**
 * @author alexander.salin
 * 
 */
public class UserService  {
	public static final Logger log = Logger.getLogger(UserService.class);
	private static final int FIRST_ELEMENT = 0;
	private static final String PASSWORD_DB = "password";
	private static final String NICK_NAME_DB = "nickName";

	public UserService() {
	}

	public User login(final String nickName, final String password)
			throws UserNotFoundException {
		UserDaoImpl userImpl = new UserDaoImpl();
		List<User> list = new ArrayList<User>();
		log.warn("Try login : nickname="+nickName+" password"+password);
		list = userImpl.findByCriteria(new IGenCriteria() {
			@Override
			public void initCriteria(Criteria criteria) {
				criteria.add(Restrictions.eq(NICK_NAME_DB, nickName));
				criteria.add(Restrictions.eq(PASSWORD_DB, password));	
			}
		});
		if (list.isEmpty()) throw new UserNotFoundException("Authentification denied; nickname="+nickName);
		return list.get(FIRST_ELEMENT);
	}
	
	public User login(final User user)
			throws UserNotFoundException {
		if (user == null) throw new UserNotFoundException("Authentification denied; nickname="+user.getNickName());		
		UserDaoImpl userImpl = new UserDaoImpl();
		List<User> list = new ArrayList<User>();
		list = userImpl.findByCriteria(new IGenCriteria() {
			@Override
			public void initCriteria(Criteria criteria) {
				criteria.add(Restrictions.eq(NICK_NAME_DB, user.getNickName()));
				criteria.add(Restrictions.eq(PASSWORD_DB, user.getPassword()));	
			}
		});

		return list.get(FIRST_ELEMENT);
	}
}
