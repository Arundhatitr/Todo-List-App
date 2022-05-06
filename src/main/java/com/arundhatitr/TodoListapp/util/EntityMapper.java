package com.arundhatitr.TodoListapp.util;

import org.springframework.stereotype.Component;

import com.arundhatitr.TodoListapp.beans.TodoDTO;
import com.arundhatitr.TodoListapp.constants.Constants;
import com.arundhatitr.TodoListapp.model.Todo;

@Component
public class EntityMapper {
	public TodoDTO userTaskMapper(Todo todo) {
		TodoDTO todoDto = new TodoDTO();
		todoDto.setTaskId(todo.getId());
		todoDto.setDesc(todo.getDesc());
		if (todo.isDone()) {
			todoDto.setIsDone(Constants.TASK_STATUS_OPEN);
		} else {
			todoDto.setIsDone(Constants.TASK_STATUS_CLOSED);
		}
		todoDto.setName(todo.getName());
		todoDto.setCreatedDate(todo.getCreatedDate());
		todoDto.setUpdatedDate(todo.getUpdatedDate());
		todoDto.setTargetCompletion(todo.getTargetCompletion());
		todoDto.setTaskCategory(todo.getTaskCategory());
		return todoDto;

	}

	public Todo userTaskConverter(TodoDTO todoDto) {
		Todo todo = new Todo();
		todo.setId(todoDto.getTaskId());
		todo.setDesc(todoDto.getDesc());
		if (Constants.TASK_STATUS_OPEN.equalsIgnoreCase(todoDto.getIsDone())) {
			todo.setDone(true);
			;
		} else {
			todo.setDone(false);
		}
		todo.setName(todoDto.getName());
		todo.setCreatedDate(todoDto.getCreatedDate());
		todo.setUpdatedDate(todoDto.getUpdatedDate());
		todo.setTargetCompletion(todoDto.getTargetCompletion());
		todo.setTaskCategory(todoDto.getTaskCategory());
		return todo;

	}

}
