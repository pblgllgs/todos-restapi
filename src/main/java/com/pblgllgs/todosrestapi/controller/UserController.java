package com.pblgllgs.todosrestapi.controller;
/*
 *
 * @author pblgl
 * Created on 14-05-2025
 *
 */

import com.pblgllgs.todosrestapi.response.UserResponse;
import com.pblgllgs.todosrestapi.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User Rest Api endpoints", description = "Operations related to User")
public class UserController {

    private final UserService userService;
    private final Logger logger = Logger.getLogger(UserController.class.getName());

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/info")
    public ResponseEntity<UserResponse> getUserInfo() throws Exception {
        logger.info("Get user info");
        return new ResponseEntity<>(userService.getUserInfo(), HttpStatus.OK);
    }
}
