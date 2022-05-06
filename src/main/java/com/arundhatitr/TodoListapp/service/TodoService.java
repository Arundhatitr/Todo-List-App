package com.arundhatitr.TodoListapp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.arundhatitr.TodoListapp.beans.TodoDTO;
import com.arundhatitr.TodoListapp.model.Todo;
import com.arundhatitr.TodoListapp.model.User;
import com.arundhatitr.TodoListapp.repository.UserRepository;
import com.arundhatitr.TodoListapp.util.Configuration;
import com.arundhatitr.TodoListapp.util.EntityMapper;

@Service
public class TodoService implements TodoInterface {
	@Resource
	Configuration utility;

	@Resource
	UserRepository userRepository;

	@Resource
	EntityMapper mapper;

	@Override
	public List<TodoDTO> retrieveTask() {
		List<TodoDTO> todoDtoList = new ArrayList<>();
		Optional<User> user = userRepository.findById(utility.getLoggedinUserName());
		List<Todo> todoList = user.get().getTodolist();
		for (Todo todo : todoList) {
			todoDtoList.add(mapper.userTaskMapper(todo));
		}
		return todoDtoList;

	}

	@Override
	public TodoDTO retrieveTaskById(String taskId) {

		Optional<User> user = userRepository.findById(utility.getLoggedinUserName());
		List<Todo> todoList = user.get().getTodolist().stream().filter((p -> p.getId() == Integer.valueOf(taskId)))
				.collect(Collectors.toList());
		Todo todo = new Todo();
		if (todoList.size() > 0) {
			todo = todoList.get(0);
		}
		return mapper.userTaskMapper(todo);
	}

	@Override
	public void addTask(TodoDTO todoDto) {
		Todo todo = mapper.userTaskConverter(todoDto);
		Optional<User> user = userRepository.findById(utility.getLoggedinUserName());
		List<Todo> todoList = user.get().getTodolist();
		todo.setCreatedDate(new Date());
		todo.setUpdatedDate(new Date());
		todoList.add(todo);
		if (user.isPresent()) {
			User user1 = user.get();
			user1.setTodolist(todoList);
			userRepository.save(user1);
		}
	}

	public void updateTask(TodoDTO todoDto) {
		Todo todo = mapper.userTaskConverter(todoDto);
		Optional<User> user = userRepository.findById(utility.getLoggedinUserName());
		List<Todo> todoList = user.get().getTodolist();
		todoList.forEach(p -> {
			if (p.getId() == todo.getId()) {
				p.setDesc(todo.getDesc());
				p.setDone(todo.isDone());
				p.setName(todo.getName());
				p.setTargetCompletion(todo.getTargetCompletion());
				p.setTaskCategory(todo.getTaskCategory());
				p.setUpdatedDate(new Date());

			}
		});

	}

	@Override
	public void deleteTask(String taskId) {
		Optional<User> user = userRepository.findById(utility.getLoggedinUserName());
		user.get().getTodolist().removeIf(p -> p.getId() == Integer.valueOf(taskId));

	}

}
