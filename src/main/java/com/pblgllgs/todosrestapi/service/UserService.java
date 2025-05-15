package com.pblgllgs.todosrestapi.service;

import com.pblgllgs.todosrestapi.response.UserResponse;

public interface UserService {
    UserResponse getUserInfo();
    void deleteUser();
}
