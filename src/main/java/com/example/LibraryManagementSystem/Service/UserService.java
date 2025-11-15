package com.example.LibraryManagementSystem.Service;

import com.example.LibraryManagementSystem.Entity.UserEntity;
import com.example.LibraryManagementSystem.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

//    Find user by id
    public Optional<UserEntity> findUserById_Service(int id){
        return userRepository.findById(id);
    }

//    Delete user by id
    public boolean deleteUserById_Service(int id){
        try {
            userRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
