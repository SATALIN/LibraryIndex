/**
 * 
 */
package by.satalin.library.dao.interfaces;

import java.util.List;
import by.satalin.library.exceptions.ChangesNotCompliteException;
import by.satalin.library.exceptions.UserNotFoundException;

/**
 * @author alexander.salin
 * 
 */
public interface IGenericDao<T> {

	T saveOrUpdate(T entity) throws ChangesNotCompliteException;

	List<T> getList();

	T get(Long id) throws UserNotFoundException;

	void remove(T entity);

	void removeById(Long id);

	List<T> findByCriteria(IGenCriteria criteria) throws UserNotFoundException;
}
