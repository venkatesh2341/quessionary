package com.quessionary.app.Service;

import com.quessionary.app.DTO.UserDTO;
import com.quessionary.app.Models.User;
import com.quessionary.app.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class UserService {

    private  UserRepository userRepository;

    UserService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    public User registerUser(UserDTO userDTO)
    {
        if(userRepository.findByEmail(userDTO.getEmail()).isPresent())
        {
            System.out.println("User already existed");
            return null;
        }
        User user = User.builder()
                .userName(userDTO.getUserName())
                .email(userDTO.getEmail())
                .build();

        return userRepository.save(user);
    }

    public User getUser(UUID userId)
    {
        Optional<User> user= userRepository.findById(userId);
        if(user.isPresent())
        {
            return user.get();
        }
        return null;
    }

}
