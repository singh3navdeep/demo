package com.example.demo.controllers;

import com.example.demo.client_models.request.SignupForm;
import com.example.demo.client_models.request.UpdatedUserDetails;
import com.example.demo.entities.User;
import com.example.demo.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(UserEndpoint.BASE_URL)
public class UserEndpoint {

    static final String BASE_URL = "api/v1/user";

    private final UserService userService;

    @Autowired
    public UserEndpoint(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok().body(userService.getUserById(id));
    }

    @PostMapping
    ResponseEntity<String> addUser(@RequestBody SignupForm signupForm) {
        userService.createUser(signupForm);
        return ResponseEntity.ok().body("User created successfully");
    }

    @PutMapping()
    ResponseEntity<String> updateUser(@RequestBody UpdatedUserDetails updatedUserDetails) {
        userService.updateUser(updatedUserDetails);
        return ResponseEntity.ok().body("User details updated");
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().body("User deleted successfully");
    }
}
