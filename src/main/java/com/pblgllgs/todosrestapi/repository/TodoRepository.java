package com.pblgllgs.todosrestapi.repository;

import com.pblgllgs.todosrestapi.entity.Todo;
import com.pblgllgs.todosrestapi.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/*
 *
 * @author pblgl
 * Created on 20-05-2025
 *
 */
@Repository
public interface TodoRepository extends CrudRepository<Todo, Long> {
    List<Todo> findByOwner(User owner);
    Optional<Todo> findByIdAndOwner(Long id, User owner);
}
