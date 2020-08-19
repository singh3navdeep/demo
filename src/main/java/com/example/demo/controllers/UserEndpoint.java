package com.example.demo.controllers;

import com.example.demo.DTOs.request.SignupDTO;
import com.example.demo.DTOs.request.UpdatedDetailsDTO;
import com.example.demo.entities.User;
import com.example.demo.services.interfaces.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping(UserEndpoint.BASE_URL)
public class UserEndpoint {

    static final String BASE_URL = "api/v1/user";

    private UserService userService;


    @GetMapping
    ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok().body(userService.getUserById(id));
    }

    @PostMapping
    ResponseEntity<User> addUser(@RequestBody @Valid SignupDTO signupDTO) {
        return ResponseEntity.ok().body(userService.createUser(signupDTO));
    }

    @PutMapping()
    ResponseEntity<User> updateUser(@RequestBody @Valid UpdatedDetailsDTO updatedDetailsDTO) {
        return ResponseEntity.ok().body(userService.updateUser(updatedDetailsDTO));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().body("User deleted successfully");
    }
}
