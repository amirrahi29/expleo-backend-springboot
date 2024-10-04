package com.expleo.assignment.controller;

import com.expleo.assignment.dto.BaseResponseDTO;
import com.expleo.assignment.dto.UserDTO;
import com.expleo.assignment.dto.PasswordChangeDTO;
import com.expleo.assignment.dto.ResponseBuilder;
import com.expleo.assignment.model.User;
import com.expleo.assignment.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Create a new user
    @PostMapping
    public ResponseEntity<BaseResponseDTO<User>> createUser(@Valid @RequestBody UserDTO userDTO) {
        User createdUser = userService.createUser(userDTO);
        return ResponseBuilder.buildResponse(true, "User created successfully", createdUser);
    }

    // Get all users
    @GetMapping
    public ResponseEntity<BaseResponseDTO<List<User>>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseBuilder.buildResponse(true, "User list fetched successfully", users);
    }

    // Get user by ID
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponseDTO<User>> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return user != null 
            ? ResponseBuilder.buildResponse(true, "User found", user) 
            : ResponseBuilder.buildResponse(false, "User not found", null, 404);
    }

    // Update a user
    @PutMapping("/{id}")
    public ResponseEntity<BaseResponseDTO<User>> updateUser(@PathVariable Long id, @Valid @RequestBody UserDTO userDTO) {
        User updatedUser = userService.updateUser(id, userDTO);
        return ResponseBuilder.buildResponse(true, "User updated successfully", updatedUser);
    }

    // Change user password
    @PatchMapping("/{id}")
    public ResponseEntity<BaseResponseDTO<Void>> changePassword(@PathVariable Long id, @Valid @RequestBody PasswordChangeDTO passwordChangeDTO) {
        userService.changePassword(id, passwordChangeDTO);
        return ResponseBuilder.buildResponse(true, "Password changed successfully", null);
    }

    // Delete a user
    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponseDTO<Void>> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseBuilder.buildResponse(true, "User deleted successfully", null);
    }
}
