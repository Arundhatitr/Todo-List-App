package com.arundhatitr.TodoListapp.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "USER")
public class User {

	@Id
	@Column(name = "user_id", unique = true, nullable = false)
	public String userId;
	@Column
	public String name;
	@Column
	public String password;
	@Column
	public String role;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	public List<Todo> todolist;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Todo> getTodolist() {
		return todolist;
	}

	public void setTodolist(List<Todo> todolist) {
		this.todolist = todolist;
	}

}
