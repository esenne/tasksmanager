package com.bpanalytics.tasksmanager.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.joda.time.DateTime;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bpanalytics.tasksmanager.persistence.model.Task;
import com.bpanalytics.tasksmanager.persistence.model.User;

@Repository
public class JpaTasksDao extends JpaDao<Task, Long> implements TasksDao {
	
	public JpaTasksDao() {
		super(Task.class);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Task> findTasks(User user) {
		TypedQuery<Task> query = getEntityManager().createQuery("SELECT t FROM Task t WHERE t.user.id = :userId", Task.class);
		query.setParameter("userId", user.getId());
		
		return query.getResultList();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Task> findUnfinishedTasks() {
		TypedQuery<Task> query = getEntityManager().createQuery("SELECT t FROM Task t "
													+ "WHERE t.done = 0 "
													+ "AND t.notifyByEmail = 1 "
													+ "AND t.dueDate = :tomorrow", Task.class);
		query.setParameter("tomorrow", new DateTime().withTimeAtStartOfDay().plusDays(1).toDate());
		
		return query.getResultList();
	}
}
