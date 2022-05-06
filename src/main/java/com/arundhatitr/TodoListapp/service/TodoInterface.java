package com.arundhatitr.TodoListapp.service;

import java.util.List;

import com.arundhatitr.TodoListapp.beans.TodoDTO;

public interface TodoInterface {
	public TodoDTO retrieveTaskById(String taskId);

	public List<TodoDTO> retrieveTask();

	public void addTask(TodoDTO dto);

	public void updateTask(TodoDTO dto);

	public void deleteTask(String taskId);
}
