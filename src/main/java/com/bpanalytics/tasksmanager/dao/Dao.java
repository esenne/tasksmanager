package com.bpanalytics.tasksmanager.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface Dao<T,ID extends Serializable> {

	List<T> findAll();

	T find(ID id);

	T save(T newsEntry);

	void delete(ID id);

	void update(T entity);
}
