package com.pblgllgs.todosrestapi.repository;

import com.pblgllgs.todosrestapi.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String username);
    @Query("SELECT COUNT(u) FROM User u JOIN u.authorities a WHERE a.authority = 'ROLE_ADMIN'")
    long countAdminUsers();
}
