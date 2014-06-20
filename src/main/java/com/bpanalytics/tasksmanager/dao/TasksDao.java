package com.bpanalytics.tasksmanager.dao;

import java.util.List;

import com.bpanalytics.tasksmanager.persistence.model.Task;
import com.bpanalytics.tasksmanager.persistence.model.User;

public interface TasksDao extends Dao<Task, Long> {

	List<Task> findTasks(User user);

	List<Task> findUnfinishedTasks();

}
