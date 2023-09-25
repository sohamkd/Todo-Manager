package com.lcwd.todo.dao;

import com.lcwd.todo.models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo,Integer> {


}
