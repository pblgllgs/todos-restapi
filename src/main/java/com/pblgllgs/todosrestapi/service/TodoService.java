package com.pblgllgs.todosrestapi.service;

import com.pblgllgs.todosrestapi.request.TodoRequest;
import com.pblgllgs.todosrestapi.response.TodoResponse;

import java.util.List;

public interface TodoService {
    TodoResponse createTodo(TodoRequest todoRequest);
    List<TodoResponse> getAllTodos();
    TodoResponse toggleTodoCompletion(long id);
    void deleteTodo(long id);
}
