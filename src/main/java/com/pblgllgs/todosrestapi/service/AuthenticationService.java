package com.pblgllgs.todosrestapi.service;

import com.pblgllgs.todosrestapi.request.AuthenticationRequest;
import com.pblgllgs.todosrestapi.request.RegisterRequest;
import com.pblgllgs.todosrestapi.response.AuthenticationResponse;

public interface AuthenticationService {
    void register(RegisterRequest input) throws Exception;
    AuthenticationResponse login(AuthenticationRequest request);
}
