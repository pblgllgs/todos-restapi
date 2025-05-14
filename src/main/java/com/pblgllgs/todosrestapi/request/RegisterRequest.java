package com.pblgllgs.todosrestapi.request;
/*
 *
 * @author pblgl
 * Created on 13-05-2025
 *
 */

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RegisterRequest {
    @NotNull(message = "First name is mandatory")
    @Size(min = 3, max = 30, message = "First name must be at lest 3 characters long")
    private String firstName;
    @NotNull(message = "Last name is mandatory")
    @Size(min = 3, max = 30, message = "Last name must be at lest 3 characters long")
    private String lastName;
    @Email(message = "Invalid email format")
    @NotNull(message = "Email name is mandatory")
    private String email;
    @NotNull(message = "Password name is mandatory")
    @Size(min = 3, max = 30, message = "Password must be at lest 5 characters long")
    private String password;

    public RegisterRequest(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
