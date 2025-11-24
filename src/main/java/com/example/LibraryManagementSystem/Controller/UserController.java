package com.example.LibraryManagementSystem.Controller;

import com.example.LibraryManagementSystem.Dto.User.UserCreateResponse;
import com.example.LibraryManagementSystem.Dto.User.UserCreateRequest;
import com.example.LibraryManagementSystem.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //    Create User
    @PostMapping
    public ResponseEntity<UserCreateResponse> createUser(@Valid @RequestBody UserCreateRequest request) {
        UserCreateResponse createdUser = userService.createUserService(request);
        return ResponseEntity
                .status(HttpStatus.CREATED).body(createdUser);
    }

//    Find All User
    @GetMapping("/allUsers")
    public List<UserCreateResponse> FindAllUser(){
        return userService.findAllUser_Service().stream().map(user->{
            return new UserCreateResponse(user.getId(), user.getUserName(), user.getUserSurname());
        }).collect(Collectors.toList());
    }

    //    Find User By Id
    @GetMapping("/{id}")
    public ResponseEntity<UserCreateResponse> findUserById(@PathVariable int id) {
        return userService.findUserById_Service(id)
                .map(user -> ResponseEntity.ok(user))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Find Users By Name and Surname
    @GetMapping("/by-ns")
    public ResponseEntity<List<UserCreateResponse>> findUsersByNameAndSurname(
            @RequestParam String userName,
            @RequestParam String userSurname
    ) {
        List<UserCreateResponse> users = userService.findUserByNameAndSurname_Service(userName, userSurname);
        if (users.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(users);
    }

//    Find User By Id and Name and Surname
@GetMapping("/by-ins")
public ResponseEntity<UserCreateResponse> findUsersByIdandNameAndSurname(
        @RequestParam int id,
        @RequestParam String userName,
        @RequestParam String userSurname
) {
    Optional<UserCreateResponse> user = userService.findUserByIdandNameAndSurname_Service(id,userName, userSurname);
    if (user.isEmpty()) {
        return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(user.get());
}

    // Delete User
    @DeleteMapping("/{id}")
    public ResponseEntity deleteUserById(@PathVariable("id") int id) {
        boolean deleted = userService.deleteUserById_Service(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}

