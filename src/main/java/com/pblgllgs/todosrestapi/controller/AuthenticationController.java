package com.pblgllgs.todosrestapi.controller;
/*
 *
 * @author pblgl
 * Created on 13-05-2025
 *
 */

import com.pblgllgs.todosrestapi.request.AuthenticationRequest;
import com.pblgllgs.todosrestapi.request.RegisterRequest;
import com.pblgllgs.todosrestapi.response.AuthenticationResponse;
import com.pblgllgs.todosrestapi.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication Rest Api endpoints", description = "Operations related to register and login")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Operation(summary = "Register a user", description = "Create a new user in db")
    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterRequest registerRequest) throws Exception {
        authenticationService.register(registerRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Login a user", description = "Login user")
    @PostMapping(value = "/login" ,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody @Valid AuthenticationRequest authRequest) {
        return new ResponseEntity<>(authenticationService.login(authRequest), HttpStatus.OK);
    }
}
