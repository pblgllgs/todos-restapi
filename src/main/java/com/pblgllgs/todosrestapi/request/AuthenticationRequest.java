package com.pblgllgs.todosrestapi.request;
/*
 *
 * @author pblgl
 * Created on 14-05-2025
 *
 */

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class AuthenticationRequest {

    @NotEmpty(message = "Email is mandatory")
    @Email(message = "Invalid email format")
    @Schema(example = "test@gmail.com")
    private String email;
    @NotEmpty(message = "Email is mandatory")
    @Size(min = 3, max = 30, message = "Password must be at lest 5 characters long")
    @Schema(example = "pass123")
    private String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
