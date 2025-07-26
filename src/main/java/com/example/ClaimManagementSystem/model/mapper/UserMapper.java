package com.example.ClaimManagementSystem.model.mapper;

import com.example.ClaimManagementSystem.model.User;
import com.example.ClaimManagementSystem.model.dto.request.UserUpdateDTO;
import com.example.ClaimManagementSystem.model.dto.response.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDTO ToDtoMapper(User user) {
        return new UserDTO(user.getUuid(), user.getUserFirstName(), user.getUserLastName(), user.getUserName(), user.getEmail(), user.getPassword());
    }
    public User ToEntity(UserUpdateDTO userDTO, User user) {
        if(userDTO.userFirstName().isPresent()){
            user.setUserFirstName(userDTO.userFirstName().get());
        }
        if(userDTO.userLastName().isPresent()){
            user.setUserLastName(userDTO.userLastName().get());
        }
        if(userDTO.userName().isPresent()){
            user.setUserName(userDTO.userName().get());
        }
        if(userDTO.email().isPresent()){
            user.setEmail(userDTO.email().get());
        }
        if(userDTO.password().isPresent()){
            user.setPassword(userDTO.password().get());
        }
        return user;
    }
}
