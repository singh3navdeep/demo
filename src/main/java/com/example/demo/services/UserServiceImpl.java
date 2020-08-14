package com.example.demo.services;

import com.example.demo.client_models.request.SignupForm;
import com.example.demo.client_models.request.UpdatedUserDetails;
import com.example.demo.entities.User;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            throw new ResourceNotFoundException("Invalid user id");
        }
        return optionalUser.get();
    }

    @Override
    public void createUser(SignupForm signupForm) {
        if (userRepository.existsByEmail(signupForm.getEmail())) {
            throw new BadRequestException("This email is already taken");
        }
        userRepository.save(User.builder()
                .name(signupForm.getName())
                .email(signupForm.getEmail())
                .password(signupForm.getPassword())
                .build());
    }

    @Override
    public void updateUser(UpdatedUserDetails updatedUserDetails) {
        Optional<User> optionalUser = userRepository.findById(updatedUserDetails.getId());
        if (!optionalUser.isPresent()) {
            throw new ResourceNotFoundException("Invalid user id");
        }
        User user = optionalUser.get();
        if (updatedUserDetails.getName() != null) {
            user.setName(updatedUserDetails.getName());
        }
        if (updatedUserDetails.getPassword() != null) {
            user.setPassword(updatedUserDetails.getPassword());
        }
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            throw new ResourceNotFoundException("Invalid user id");
        }
        userRepository.deleteById(id);
    }
}
