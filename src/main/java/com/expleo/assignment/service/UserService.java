package com.expleo.assignment.service;

import com.expleo.assignment.dto.UserDTO;
import com.expleo.assignment.dto.PasswordChangeDTO;
import com.expleo.assignment.model.User;

import java.util.List;

public interface UserService {
    User createUser(UserDTO userDTO);
    List<User> getAllUsers();
    User getUserById(Long id);
    User updateUser(Long id, UserDTO userDTO);
    void changePassword(Long id, PasswordChangeDTO passwordChangeDTO);
    void deleteUser(Long id);
}
