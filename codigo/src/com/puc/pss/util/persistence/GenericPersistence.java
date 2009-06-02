package com.puc.pss.util.persistence;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

class InternalEntityManager {
	private static EntityManagerFactory emf;
	static EntityManager getInstance(String factoryName) {
		if (emf == null) {
			emf = Persistence.createEntityManagerFactory(factoryName);
		}
		return emf.createEntityManager();
	}
}

/**
 * Generic DAO for Hibernate entities persistence. 
 */
public class GenericPersistence<T> {

	private Class<T> Ttype;
	private String entityManagerFactoryName;

	public void setEntityManagerFactoryName(String entityManagerFactoryName) {
		this.entityManagerFactoryName = entityManagerFactoryName;
	}

	public GenericPersistence(Class<T> Ttype, String factoryName) {	
		this.Ttype = Ttype;
		this.entityManagerFactoryName = factoryName;
	}

	public void save(T entity) throws SQLException {
		EntityManager em = getInternalManager();
		try {
			em.getTransaction().begin();
			em.persist(entity);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			throw new SQLException(e.getMessage());
		} finally {
			em.close();
		}
	}

	public EntityManager getInternalManager() {
		return InternalEntityManager.getInstance(this.getEntityManagerFactoryName());
	}

	public String getEntityManagerFactoryName() {
		return this.entityManagerFactoryName;
	}

	public void update(T entity) throws SQLException {
		EntityManager em = getInternalManager();
		try {
			em.getTransaction().begin();
			em.merge(entity);
			em.flush();
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			throw new SQLException(e.getMessage());
		} finally {
			em.close();
		}
	}

	public void remove(T entity, Integer id) throws SQLException {
		EntityManager em = getInternalManager();
		try {
			em.getTransaction().begin();
			entity = em.getReference(Ttype, id);
			em.remove(entity);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			getInternalManager().getTransaction().rollback();
			throw new SQLException(e.getMessage());
		} finally {
			em.close();
		}
	}

    /**
     * Returns a list of entities of <code>T</code> using <b>listAll</b> named query 
     * defined by <code>T</code>.
     * @param offset
     * @param limit
     * @return
     */
	@SuppressWarnings("unchecked")
	public List<T> listByOffset(int offset, int limit ) {
        Query query = getInternalManager().createNamedQuery(createQueryName("listAll"));
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.getResultList();
    }

	@SuppressWarnings("unchecked")
	public List<T> listByQuery(String query, String... args) throws NoResultException {
		int i = 1;

		Query localquery = getInternalManager().createNamedQuery(createQueryName(query));

		for (String argument : args) {
			localquery.setParameter(i++, argument);
		}
		return localquery.getResultList();
	}

	@SuppressWarnings("unchecked") 
	public T findByNamedQuery(String query, Object... args) throws NoResultException {
		int i = 1;
		Query localquery = getInternalManager().createNamedQuery(createQueryName(query));
		localquery.setMaxResults(1);

		for (Object argument : args) {
			localquery.setParameter(i++, argument);
		}
		return (T) localquery.getSingleResult();
	}
	
	@SuppressWarnings("unchecked") 
	public Long getScalarResult(String query, Object... args) throws NoResultException {
		int i = 1;
		Query localquery = getInternalManager().createNamedQuery(createQueryName(query));
		localquery.setMaxResults(1);

		for (Object argument : args) {
			localquery.setParameter(i++, argument);
		}
		return (Long)localquery.getSingleResult();
	}
	
	public String createQueryName(String query) {
		StringBuilder sb = new StringBuilder(Ttype.getSimpleName());
		sb.append(".");
		sb.append(query);
		return sb.toString();
	}
}
