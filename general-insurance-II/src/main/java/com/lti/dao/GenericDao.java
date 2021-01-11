package com.lti.dao;
import javax.persistence.PersistenceContext;

import javax.persistence.EntityManager;

public class GenericDao {
	
	@PersistenceContext
	protected EntityManager entityManager;
	
	public Object save(Object object) {
		Object updatedObject = entityManager.merge(object);
		return updatedObject;
	}
	
	public <T> T fetch(Class<T> clazz, Object pk) {
		T t = entityManager.find(clazz, pk);
		return t;
	}
	
}
