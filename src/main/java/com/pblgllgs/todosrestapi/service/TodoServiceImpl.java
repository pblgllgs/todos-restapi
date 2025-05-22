package com.pblgllgs.todosrestapi.service;
/*
 *
 * @author pblgl
 * Created on 20-05-2025
 *
 */

import com.pblgllgs.todosrestapi.entity.Todo;
import com.pblgllgs.todosrestapi.entity.User;
import com.pblgllgs.todosrestapi.repository.TodoRepository;
import com.pblgllgs.todosrestapi.request.TodoRequest;
import com.pblgllgs.todosrestapi.response.TodoResponse;
import com.pblgllgs.todosrestapi.util.FindAuthenticatedUser;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;
    private final FindAuthenticatedUser findAuthenticatedUser;

    public TodoServiceImpl(TodoRepository todoRepository, FindAuthenticatedUser findAuthenticatedUser) {
        this.todoRepository = todoRepository;
        this.findAuthenticatedUser = findAuthenticatedUser;
    }


    @Override
    @Transactional
    public TodoResponse createTodo(TodoRequest todoRequest) {
        User currentUser = findAuthenticatedUser.getAuthenticatedUser();
        Todo todo = todoRequestToTodo(todoRequest, currentUser);
        Todo savedTodo = todoRepository.save(todo);
        return todoToTodoResponse(savedTodo);
    }

    private TodoResponse todoToTodoResponse(Todo savedTodo) {
        return new TodoResponse(
                savedTodo.getId(),
                savedTodo.getTitle(),
                savedTodo.getDescription(),
                savedTodo.getPriority(),
                savedTodo.isComplete()
        );
    }

    private Todo todoRequestToTodo(TodoRequest todoRequest, User currentUser) {
        return new Todo(
                todoRequest.getTitle(),
                todoRequest.getDescription(),
                todoRequest.getPriority(),
                false,
                currentUser
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<TodoResponse> getAllTodos() {
        User currentUser = findAuthenticatedUser.getAuthenticatedUser();
        return todoRepository.findByOwner(currentUser)
                .stream()
                .map(this::todoToTodoResponse)
                .toList();
    }

    @Override
    @Transactional
    public TodoResponse toggleTodoCompletion(long id) {
        User currentUser = findAuthenticatedUser.getAuthenticatedUser();
        Optional<Todo> todo = todoRepository.findByIdAndOwner(id, currentUser);
        if (todo.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo not found");
        }
        todo.get().setComplete(!todo.get().isComplete());
        Todo updatedTodo = todoRepository.save(todo.get());
        return todoToTodoResponse(updatedTodo);
    }

    @Override
    @Transactional
    public void deleteTodo(long id) {
        User currentUser = findAuthenticatedUser.getAuthenticatedUser();
        Optional<Todo> todo = todoRepository.findByIdAndOwner(id, currentUser);
        if (todo.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo not found");
        }
        todoRepository.deleteById(id);
    }
}
