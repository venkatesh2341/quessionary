package com.quessionary.app.Controller;

import com.quessionary.app.DTO.UserDTO;
import com.quessionary.app.Models.User;
import com.quessionary.app.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
