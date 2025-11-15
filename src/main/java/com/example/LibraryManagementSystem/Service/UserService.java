package com.example.LibraryManagementSystem.Service;

import com.example.LibraryManagementSystem.Entity.UserEntity;
import com.example.LibraryManagementSystem.Repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //    Create user
    public UserEntity createUserService(UserEntity user) {
        return userRepository.save(user);
    }

}
