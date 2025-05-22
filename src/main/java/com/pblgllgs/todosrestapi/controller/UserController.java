package com.pblgllgs.todosrestapi.controller;
/*
 *
 * @author pblgl
 * Created on 14-05-2025
 *
 */

import com.pblgllgs.todosrestapi.exception.ExceptionResponses;
import com.pblgllgs.todosrestapi.request.PasswordUpdateRequest;
import com.pblgllgs.todosrestapi.response.UserResponse;
import com.pblgllgs.todosrestapi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @Operation(summary = "User information", description = "Get current user info")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ExceptionResponses.class)
                    )
            )
    }
    )
    @GetMapping("/info")
    public ResponseEntity<UserResponse> getUserInfo() {
        logger.info("Get user info");
        return new ResponseEntity<>(userService.getUserInfo(), HttpStatus.OK);
    }

    @Operation(summary = "Delete User", description = "Delete user from db")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "HTTP FORBIDDEN",
                    content = @Content(
                            schema = @Schema(implementation = ExceptionResponses.class)
                    )
            )
    }
    )
    @DeleteMapping
    public ResponseEntity<Void> deleteUser(){
        userService.deleteUser();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Update User", description = "Update user from db")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "HTTP FORBIDDEN",
                    content = @Content(
                            schema = @Schema(implementation = ExceptionResponses.class)
                    )
            )
    }
    )
    @PutMapping("/password")
    public ResponseEntity<Void> updatePassword(@RequestBody @Valid PasswordUpdateRequest passwordUpdateRequest) {
        userService.updatePassword(passwordUpdateRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
