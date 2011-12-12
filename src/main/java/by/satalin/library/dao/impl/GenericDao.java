/**
 * 
 */
package by.satalin.library.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import by.satalin.library.dao.interfaces.IGenCriteria;
import by.satalin.library.dao.interfaces.IGenericDao;
import by.satalin.library.exceptions.ChangesNotCompliteException;
import by.satalin.library.exceptions.UserNotFoundException;
import by.satalin.persist.HibernateUtils;

/**
 * @author alexander.salin
 * 
 */
public class GenericDao<T> implements IGenericDao<T> {
	private static final Logger log = Logger.getLogger(GenericDao.class);
	protected Class<T> persistentClass;

	/**
	 * @return the persistentClass
	 */
	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	/**
	 * @param persistentClass the persistentClass to set
	 */
	public void setPersistentClass(Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}

	@Override
	public T saveOrUpdate(T entity) throws ChangesNotCompliteException {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.saveOrUpdate(entity);
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			log.error(e);
			throw new ChangesNotCompliteException(e.toString());
		} finally {
			session.clear();
			session.close();
		}
		return entity;
	}
	
	public T save(T entity) throws ChangesNotCompliteException {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(entity);
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			log.error(e);
			throw new ChangesNotCompliteException(e.toString());
		} finally {
			session.clear();
			session.close();
		}
		return entity;
	}

	@Override
	public List<T> getList() {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = null;

		List<T> entities = new ArrayList<T>();
		try {
			transaction = session.beginTransaction();
			entities = session.createQuery("from "+getPersistentClass().getSimpleName()).list();
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return entities;
	}

	@Override
	public List<T> findByCriteria(IGenCriteria genCriteria)
			throws UserNotFoundException {
		log.warn("findByCriteria");
		Session session = HibernateUtils.getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(getPersistentClass());
		if (genCriteria != null) {
			genCriteria.initCriteria(criteria);
		}
		final List list = criteria.list();
		if (list.isEmpty())
			throw new UserNotFoundException("User id="+genCriteria.toString());
		return list;
	}

	@Override
	public T get(Long id) throws UserNotFoundException {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = null;
		T entity = null;
		try {
			transaction = session.beginTransaction();
			entity = (T) session.get(getPersistentClass(), id);
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		if (entity == null)
			throw new UserNotFoundException("User id="+id);
		return entity;
	}

	@Override
	public void remove(T entity) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.delete(entity);
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@Override
	public void removeById(Long id) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		String hqlQuery = "delete from " + getPersistentClass().getSimpleName()
				+ " where id = :id";
		Query query = session.createQuery(hqlQuery);
		query.setLong("id", id);
		Integer row = query.executeUpdate();
	}


}
