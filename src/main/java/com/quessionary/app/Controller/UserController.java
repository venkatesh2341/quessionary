package com.quessionary.app.Controller;

import com.quessionary.app.DTO.UserDTO;
import com.quessionary.app.Models.User;
import com.quessionary.app.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Repeatable;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    UserController(UserService userService)
    {
        this.userService = userService;
    }

    @PostMapping("/register")
    ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO)
    {
            User user=  userService.registerUser(userDTO);
            if(user == null)
                return new ResponseEntity<String>("User already existed", HttpStatus.CONFLICT);
            return new ResponseEntity<User>(user,HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    ResponseEntity<?> getUser(@PathVariable UUID userId)
    {
        User user = userService.getUser(userId);
        if(user == null)
            return new ResponseEntity<String>("User not found",HttpStatus.NOT_FOUND);
        else return new  ResponseEntity<User>(user,HttpStatus.OK);
    }

    @GetMapping("/")
    ResponseEntity<?> getUsers()
    {
        List<User> users= userService.getUsers();
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    ResponseEntity<?> updateUser(@PathVariable UUID userId, @RequestBody UserDTO userDTO)
    {
        User user= userService.updateUser(userId, userDTO);
        if(user != null)
            return new ResponseEntity<User>(user,HttpStatus.OK);
        else return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    }
}
