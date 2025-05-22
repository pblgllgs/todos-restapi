package com.pblgllgs.todosrestapi.service.impl;
/*
 *
 * @author pblgl
 * Created on 22-05-2025
 *
 */

import com.pblgllgs.todosrestapi.entity.Authority;
import com.pblgllgs.todosrestapi.entity.User;
import com.pblgllgs.todosrestapi.repository.UserRepository;
import com.pblgllgs.todosrestapi.response.UserResponse;
import com.pblgllgs.todosrestapi.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;

    public AdminServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> getAllUsers() {
        return StreamSupport.stream(userRepository.findAll().spliterator(),false).map(this::userToUserResponse).toList();
    }

    private UserResponse userToUserResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getFirstName()+ " " + user.getLastName(),
                user.getEmail(),
                user.getAuthorities().stream().map(auth ->(Authority) auth).toList()
        );
    }

    @Override
    @Transactional
    public UserResponse promoToAdmin(long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty() || user.get().getAuthorities().stream().anyMatch(authority-> "ROLE_ADMIN".equals(authority.getAuthority()))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User does not exist or already an admin");
        }
        List<Authority> authorities = new ArrayList<>();
        authorities.add(new Authority("ROLE_ADMIN"));
        authorities.add(new Authority("ROLE_EMPLOYEE"));
        user.get().setAuthorities(authorities);

        User savedUser = userRepository.save(user.get());
        return userToUserResponse(savedUser);
    }

    @Override
    public void deleteNonAdminUser(long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty() || user.get().getAuthorities().stream().anyMatch(authority-> "ROLE_ADMIN".equals(authority.getAuthority()))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User does not exist or already an admin");
        }
        userRepository.delete(user.get());

    }
}
