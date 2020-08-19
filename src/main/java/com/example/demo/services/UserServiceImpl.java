package com.example.demo.services;

import com.example.demo.DTOs.request.SignupDTO;
import com.example.demo.DTOs.request.UpdatedDetailsDTO;
import com.example.demo.entities.User;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.interfaces.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

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
    public User createUser(SignupDTO signupDTO) {
        if (userRepository.existsByEmail(signupDTO.getEmail())) {
            throw new BadRequestException("This email is already taken");
        }
        return userRepository.save(User.builder()
                .name(signupDTO.getName())
                .email(signupDTO.getEmail())
                .password(signupDTO.getPassword())
                .build());
    }

    @Override
    public User updateUser(UpdatedDetailsDTO updatedDetailsDTO) {
        User user = userRepository.getOne(updatedDetailsDTO.getId());
        if (updatedDetailsDTO.getName() != null) {
            user.setName(updatedDetailsDTO.getName());
        }
        if (updatedDetailsDTO.getPassword() != null) {
            user.setPassword(updatedDetailsDTO.getPassword());
        }
        return userRepository.save(user);
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
