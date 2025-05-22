package com.pblgllgs.todosrestapi.controller;
/*
 *
 * @author pblgl
 * Created on 20-05-2025
 *
 */

import com.pblgllgs.todosrestapi.request.TodoRequest;
import com.pblgllgs.todosrestapi.response.TodoResponse;
import com.pblgllgs.todosrestapi.service.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
@Tag(name = "Todo REST API endpoints", description = "Operations for managing user todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @Operation(summary = "Create todo for user", description = "Create todo into database")
    @PostMapping
    public ResponseEntity<TodoResponse> createTodo(@RequestBody @Valid TodoRequest todoRequest) {
        return new ResponseEntity<>(todoService.createTodo(todoRequest), HttpStatus.CREATED);
    }

    @Operation(summary = "Get all todos for user", description = "Get all todos from database")
    @GetMapping
    public ResponseEntity<List<TodoResponse>> findAll(){
        return new ResponseEntity<>(todoService.getAllTodos(), HttpStatus.OK);
    }

    @Operation(summary = "Update todo for user", description = "Update todos from database")
    @PutMapping("/{id}")
    public ResponseEntity<TodoResponse> toggleTodoCompletion(@PathVariable("id") @Min(1) long id) {
        return new ResponseEntity<>(todoService.toggleTodoCompletion(id), HttpStatus.OK);
    }

    @Operation(summary = "Delete todo for user", description = "Delete todos from database")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable("id") @Min(1) long id) {
        todoService.deleteTodo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
