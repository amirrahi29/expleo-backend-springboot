package com.expleo.assignment.service;

import com.expleo.assignment.dto.UserDTO;
import com.expleo.assignment.dto.PasswordChangeDTO;
import com.expleo.assignment.exception.BadRequestException;
import com.expleo.assignment.exception.ResourceNotFoundException;
import com.expleo.assignment.model.User;
import com.expleo.assignment.repository.UserRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User createUser(UserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new BadRequestException("Email already exists.");
        }
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));
    }

    public User updateUser(Long id, UserDTO userDTO) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));

        if (!existingUser.getEmail().equals(userDTO.getEmail()) && userRepository.existsByEmail(userDTO.getEmail())) {
            throw new BadRequestException("Email already exists.");
        }

        existingUser.setName(userDTO.getName());
        existingUser.setEmail(userDTO.getEmail());
        existingUser.setUsername(userDTO.getUsername()); 
        return userRepository.save(existingUser);
    }

    @Transactional
    public void changePassword(Long id, PasswordChangeDTO passwordChangeDTO) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));

        if (!passwordEncoder.matches(passwordChangeDTO.getOldPassword(), existingUser.getPassword())) {
            throw new BadRequestException("Incorrect current password.");
        }

        existingUser.setPassword(passwordEncoder.encode(passwordChangeDTO.getNewPassword()));
        userRepository.save(existingUser);
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with ID: " + id);
        }
        userRepository.deleteById(id);
    }
}
