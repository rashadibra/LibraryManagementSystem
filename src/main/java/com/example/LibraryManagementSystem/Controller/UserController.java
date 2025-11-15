package com.example.LibraryManagementSystem.Controller;

import com.example.LibraryManagementSystem.Entity.UserEntity;
import com.example.LibraryManagementSystem.Service.UserService;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //    Create User
    @PostMapping("/create_user")
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity userEntity) {
        try {
            UserEntity createdUser = userService.createUserService(userEntity);
            return ResponseEntity.ok(createdUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

//
}
