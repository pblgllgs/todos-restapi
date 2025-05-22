package com.pblgllgs.todosrestapi.service.impl;
/*
 *
 * @author pblgl
 * Created on 14-05-2025
 *
 */

import com.pblgllgs.todosrestapi.entity.Authority;
import com.pblgllgs.todosrestapi.entity.User;
import com.pblgllgs.todosrestapi.repository.UserRepository;
import com.pblgllgs.todosrestapi.request.PasswordUpdateRequest;
import com.pblgllgs.todosrestapi.response.UserResponse;
import com.pblgllgs.todosrestapi.service.UserService;
import com.pblgllgs.todosrestapi.util.FindAuthenticatedUser;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final FindAuthenticatedUser findAuthenticatedUser;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, FindAuthenticatedUser findAuthenticatedUser, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.findAuthenticatedUser = findAuthenticatedUser;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse getUserInfo() {
        User user = findAuthenticatedUser.getAuthenticatedUser();
        return new UserResponse(
                user.getId(),
                user.getFirstName() + " " + user.getLastName(),
                user.getEmail(),
                user.getAuthorities().stream().map(auth -> (Authority) auth).toList()
        );

    }

    @Override
    @Transactional
    public void deleteUser() {
        User user = findAuthenticatedUser.getAuthenticatedUser();
        if (isLastAdmin(user)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Admin cant delete itself");
        }
        userRepository.delete(user);
    }

    @Transactional
    @Override
    public void updatePassword(PasswordUpdateRequest passwordUpdateRequest) {
        User user = findAuthenticatedUser.getAuthenticatedUser();

        if(!isOldPasswordCurrent(user.getPassword(), passwordUpdateRequest.getOldPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Current password does not match");
        }
        if (!isNewPasswordConfirmed(passwordUpdateRequest.getNewPassword(),
                passwordUpdateRequest.getNewPassword2())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "New passwords does not match");
        }
        if (!isNewPasswordDifferent(passwordUpdateRequest.getOldPassword(), passwordUpdateRequest.getNewPassword())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "old and new passwords must be different");
        }
        user.setPassword(passwordEncoder.encode(passwordUpdateRequest.getNewPassword()));
        userRepository.save(user);
    }

    private boolean isOldPasswordCurrent(String currentPassword, String oldPassword) {
        return passwordEncoder.matches(oldPassword, currentPassword);
    }

    private boolean isNewPasswordConfirmed(String newPassword, String newPasswordConfirmation) {
        return newPassword.equals(newPasswordConfirmation);
    }

    private boolean isNewPasswordDifferent(String oldPassword, String newPassword) {
        return !oldPassword.equals(newPassword);
    }

    private boolean isLastAdmin(User user) {
        boolean isAdmin = user.getAuthorities().stream().anyMatch(authority -> "ROLE_ADMIN".equals(authority.getAuthority()));
        if (isAdmin) {
            long adminCount = userRepository.countAdminUsers();
            return adminCount <= 1;
        }
        return false;
    }
}
