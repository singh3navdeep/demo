package com.example.demo.services.interfaces;

import com.example.demo.client_models.request.SignupForm;
import com.example.demo.client_models.request.UpdatedUserDetails;
import com.example.demo.entities.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(Long id);

    void createUser(SignupForm signupForm);

    void updateUser(UpdatedUserDetails updatedUserDetails);

    void deleteUser(Long id);
}
