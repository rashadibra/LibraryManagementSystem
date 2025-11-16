package com.example.LibraryManagementSystem.Controller;

import com.example.LibraryManagementSystem.Entity.UserEntity;
import com.example.LibraryManagementSystem.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //    Create User
    @PostMapping
    public ResponseEntity createUser(@RequestBody UserEntity userEntity) {
        UserEntity createdUser = userService.createUserService(userEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    //    Find User By Id
    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> findUserById(@PathVariable int id) {
        return userService.findUserById_Service(id)
                .map(user -> ResponseEntity.ok(user))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Delete User
    @DeleteMapping("/{id}")
    public ResponseEntity deleteUserById(@PathVariable("id") int id) {
        boolean deleted = userService.deleteUserById_Service(id);
        if (deleted) {
            return ResponseEntity.noContent().build(); // 204
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}

