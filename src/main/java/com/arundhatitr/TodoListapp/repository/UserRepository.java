package com.arundhatitr.TodoListapp.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arundhatitr.TodoListapp.model.User;

@Transactional
public interface UserRepository extends JpaRepository<User, String> {

}
