package com.arundhatitr.TodoListapp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arundhatitr.TodoListapp.beans.TodoDTO;
import com.arundhatitr.TodoListapp.constants.Constants;
import com.arundhatitr.TodoListapp.service.TodoInterface;
import com.arundhatitr.TodoListapp.util.Configuration;

@RestController
public class TodoController {
	@Resource
	TodoInterface todoInterface;
	@Resource
	Configuration utility;

	@InitBinder
	public void initBinder(WebDataBinder binder) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	@GetMapping(value = "/list-task")
	public String showTodos(ModelMap model) {
		model.put(Constants.TASK_OBJ, todoInterface.retrieveTask());
		return Constants.LIST_TASK;
	}

	@GetMapping(value = "/add-task")
	public String showAddTodoPage(ModelMap model) {
		TodoDTO todoDto = new TodoDTO();
		todoDto.setTargetCompletion(new Date());
		model.addAttribute("task", todoDto);
		return Constants.ADD_TASK;
	}

	@PostMapping(value = "/add-task")
	public String addTask(ModelMap model, @Validated TodoDTO todoDto, BindingResult result) {
		if (result.hasErrors()) {
			return Constants.ADD_TASK;// TODO It has to be handled as per requirement
		}

		todoInterface.addTask(todoDto);
		return Constants.REDIRECT + Constants.COLON + Constants.FORWARD_SLASH + Constants.LIST_TASK;
	}

	@GetMapping(value = "/delete-task/{taskId}")
	public String deleteTask(@PathVariable("taskId") String taskId) {
		todoInterface.deleteTask(taskId);
		return Constants.REDIRECT + Constants.COLON + Constants.FORWARD_SLASH + Constants.LIST_TASK;
	}

	@GetMapping(value = "/update-task")
	public String showUpdateTodoPage(@RequestParam("id") String taskId, ModelMap model) {
		TodoDTO todoDto = todoInterface.retrieveTaskById(taskId);
		model.put("task", todoDto);
		return Constants.ADD_TASK;
	}

	@PostMapping(value = "/update-task")
	public String updateTodo(ModelMap model, @Validated TodoDTO todoDto, BindingResult result) {

		if (result.hasErrors()) {
			return Constants.ADD_TASK;// TODO It has to be handled as per requirement
		}

		todoInterface.updateTask(todoDto);

		return Constants.REDIRECT + Constants.COLON + Constants.FORWARD_SLASH + Constants.LIST_TASK;
	}

}