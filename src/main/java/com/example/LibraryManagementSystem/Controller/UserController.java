package com.example.LibraryManagementSystem.Controller;

import com.example.LibraryManagementSystem.Entity.UserEntity;
import com.example.LibraryManagementSystem.Service.UserService;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> findUserById(@PathVariable("id") int id){
     Optional<UserEntity> findedUser=userService.findUserById_Service(id);
if(findedUser.isEmpty()){
    return ResponseEntity.notFound().build();
}else{
    return ResponseEntity.ok().body(findedUser.get());
}
    }
// Delete User
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteUserById(@PathVariable("id") int id){
         boolean deletedUser=userService.deleteUserById_Service(id);
         if(deletedUser){
            return ResponseEntity.ok(true);
         }
         else{
             return ResponseEntity.status(404).body(false);
         }
    }


}
