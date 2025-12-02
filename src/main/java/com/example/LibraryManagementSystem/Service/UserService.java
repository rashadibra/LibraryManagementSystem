package com.example.LibraryManagementSystem.Service;

import com.example.LibraryManagementSystem.Dto.User.UserCreateRequest;
import com.example.LibraryManagementSystem.Dto.User.UserCreateResponse;
import com.example.LibraryManagementSystem.Entity.UserEntity;
import com.example.LibraryManagementSystem.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //    Create user
    public UserCreateResponse createUserService(UserCreateRequest user) {
        UserEntity savedUser = userRepository.save(UserEntity.builder().userName(user.getUserName()).userSurname(user.getUserSurname()).build());
        return new UserCreateResponse(savedUser.getId(), savedUser.getUserName(), savedUser.getUserSurname());
    }

    //    Find All User
    public List<UserCreateResponse> findAllUser_Service() {
        return userRepository.findAll()
                .stream()
                .map(user -> {
                    return new UserCreateResponse(user.getId(),
                            user.getUserName(),
                            user.getUserSurname());
                })
                .collect(Collectors.toList());
    }

    //    Find user by id
    public Optional<UserCreateResponse> findUserById_Service(int id) {
        return userRepository.findById(id).map(user -> {
            return new UserCreateResponse(user.getId(), user.getUserName(), user.getUserSurname());
        });
    }

    //    Find User by name and surname
    public List<UserCreateResponse> findUserByNameAndSurname_Service(String userName, String userSurname) {
        return userRepository.findByUserNameAndUserSurname(userName, userSurname).stream().map(user -> {
            return new UserCreateResponse(user.getId(), user.getUserName(), user.getUserSurname());
        }).collect(Collectors.toList());
    }

    //    Find User By Id and Name and Surname
    public Optional<UserCreateResponse> findUserByIdandNameAndSurname_Service(int id, String userName, String userSurname) {
        return userRepository.findByIdAndUserNameAndUserSurname(id, userName, userSurname).map(user -> {
            return new UserCreateResponse(user.getId(), user.getUserName(), user.getUserSurname());
        });
    }

    //    Delete user by id
    public boolean deleteUserById_Service(int id) {
        if (!userRepository.existsById(id)) {
            return false;
        }
        userRepository.deleteById(id);
        return true;
    }

}