package com.bpanalytics.tasksmanager.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpanalytics.tasksmanager.business.TasksBusiness;
import com.bpanalytics.tasksmanager.dao.TasksDao;
import com.bpanalytics.tasksmanager.persistence.model.Task;
import com.bpanalytics.tasksmanager.persistence.model.User;

@Service
public class TasksBusinessImpl implements TasksBusiness {
	
	@Autowired
	private TasksDao tasksDao;
	
	@Override
	public List<Task> getTasks(User user) {
		return tasksDao.findTasks(user);
	}
	
	@Override
	public Task newTask(Task task) {
		return tasksDao.save(task);
	}
	
	@Override
	public void deleteTask(Task task) {
		tasksDao.delete(task.getId());
	}
	
	@Override
	public void updateTask(Task task) {
		tasksDao.update(task);
	}
	
	@Override
	public List<Task> getUnfinishedTasks() {
		return tasksDao.findUnfinishedTasks();
	}

	public void setTasksDao(TasksDao tasksDao) {
		this.tasksDao = tasksDao;
	}
}
