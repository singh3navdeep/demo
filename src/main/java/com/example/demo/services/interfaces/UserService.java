package com.example.demo.services.interfaces;

import com.example.demo.DTOs.request.SignupDTO;
import com.example.demo.DTOs.request.UpdatedDetailsDTO;
import com.example.demo.entities.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(Long id);

    User createUser(SignupDTO signupDTO);

    User updateUser(UpdatedDetailsDTO updatedDetailsDTO);

    void deleteUser(Long id);
}
