package com.bpanalytics.tasksmanager.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.transaction.annotation.Transactional;

public class JpaDao<T, ID extends Serializable> implements Dao<T, ID>
{
	@PersistenceContext
	private EntityManager entityManager;

	protected Class<T> entityClass;

	@SuppressWarnings("unchecked")
	public JpaDao(Class<T> entityClass) {
		this.entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@Override
	@Transactional(readOnly = true)
	public List<T> findAll() {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<T> criteriaQuery = builder.createQuery(this.entityClass);

		criteriaQuery.from(this.entityClass);

		TypedQuery<T> typedQuery = entityManager.createQuery(criteriaQuery);
		return typedQuery.getResultList();
	}

	@Override
	@Transactional(readOnly = true)
	public T find(ID id) {
		return entityManager.find(this.entityClass, id);
	}

	@Override
	@Transactional
	public T save(T entity) {
		return entityManager.merge(entity);
	}

	@Override
	@Transactional
	public void delete(ID id) {
		if (id == null) {
			return;
		}

		T entity = this.find(id);
		if (entity == null) {
			return;
		}

		entityManager.remove(entity);
	}
	
	@Override
	@Transactional
	public void update(T entity) {
		entityManager.merge(entity);
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
