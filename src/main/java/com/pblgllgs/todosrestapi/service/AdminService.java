package com.pblgllgs.todosrestapi.service;

import com.pblgllgs.todosrestapi.response.UserResponse;

import java.util.List;

public interface AdminService {
    List<UserResponse> getAllUsers();
    UserResponse promoToAdmin(long id);
    void deleteNonAdminUser(long id);
}
