package com.pblgllgs.todosrestapi.controller;
/*
 *
 * @author pblgl
 * Created on 22-05-2025
 *
 */

import com.pblgllgs.todosrestapi.response.UserResponse;
import com.pblgllgs.todosrestapi.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Admin REST API endpoints", description = "Operations related to an Admin")
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @Operation(summary = "Get all users", description = "Get all users from database")
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return new ResponseEntity<>(adminService.getAllUsers(), HttpStatus.OK);
    }

    @Operation(summary = "Promote user to admin", description = "Promote user to admin into database")
    @PutMapping("/{userId}/role")
    public ResponseEntity<UserResponse> promoteToAdmin(@PathVariable @Min(1) Long userId) {
        return new ResponseEntity<>(adminService.promoToAdmin(userId), HttpStatus.OK);
    }

    @Operation(summary = "Delete user", description = "Delete user from database")
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable @Min(1) Long userId) {
        adminService.deleteNonAdminUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
