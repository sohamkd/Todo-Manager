package com.lcwd.todo.controllers;

import com.lcwd.todo.models.Todo;
import com.lcwd.todo.services.TodoService;
import com.lcwd.todo.services.impl.TodoServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Random;


@RestController
@RequestMapping("/todos")
public class TodoController {
    Logger logger = LoggerFactory.getLogger(TodoController.class);
    @Autowired
    private TodoService todoService;
    Random random = new Random();

    //create
    @PostMapping
    public ResponseEntity<Todo> createTodoHandler(@RequestBody Todo todo) {
//
//        String str = null;
//        logger.info("{}", str.length());
        //create todo
        int id = random.nextInt(9999999);
        todo.setId(id);
        //create date with system default current date
        Date currentDate = new Date();
        logger.info("current date: {}", currentDate);
        logger.info("dodo date {}", todo.getToDoDate());
        todo.setAddedDate(currentDate);
        logger.info("Create Todo");
        //call service to create todo
        Todo todo1 = todoService.createTodo(todo);
        return new ResponseEntity<>(todo1, HttpStatus.CREATED);

    }

    //get all todo method
    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodoHandler() {
        List<Todo> allTodos = todoService.getAllTodos();
        return new ResponseEntity<>(allTodos, HttpStatus.OK);
    }

    //get single todo
    @GetMapping("/{todoId}")
    public ResponseEntity<Todo> getSingleTodoHandler(@PathVariable int todoId) throws ParseException {
        Todo todo = todoService.getTodo(todoId);
        return ResponseEntity.ok(todo);
    }

    //update todo
    @PutMapping("/{todoId}")
    public ResponseEntity<Todo> updateTodoHandler(@RequestBody Todo todoWithNewDetails, @PathVariable int todoId) {
        Todo todo = todoService.updateTodo(todoId, todoWithNewDetails);
        return ResponseEntity.ok(todo);
    }

    //
    @DeleteMapping("/{todoId}")
    public ResponseEntity<String> deleteTodo(@PathVariable int todoId) {
        todoService.deleteTodo(todoId);
        return ResponseEntity.ok("Todo Successfully deleted");

    }

    //exception handler
//    @ExceptionHandler(NullPointerException.class)
//    public ResponseEntity<String> nullPointerExceptionHandler(NullPointerException ex) {
//        System.out.println(ex.getMessage());
//        System.out.println("Null pointer exception generated");
//        return new ResponseEntity<>("Null pointer exception generated " + ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
//    }

//    //exception handler
//    @ExceptionHandler(NumberFormatException.class)
//    public ResponseEntity<String> numberFormatExceptionHandler(NumberFormatException ex) {
//        System.out.println(ex.getMessage());
//        System.out.println("Null pointer exception generated");
//        return new ResponseEntity<>("Null pointer exception generated " + ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
//    }


}
