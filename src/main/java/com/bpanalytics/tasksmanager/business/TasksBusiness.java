package com.bpanalytics.tasksmanager.business;

import java.util.List;

import com.bpanalytics.tasksmanager.persistence.model.Task;
import com.bpanalytics.tasksmanager.persistence.model.User;


public interface TasksBusiness {

	List<Task> getTasks(User user);

	Task newTask(Task task);

	void deleteTask(Task task);

	void updateTask(Task task);

	List<Task> getUnfinishedTasks();
}
