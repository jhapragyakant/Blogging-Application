package com.pragyakantjha.blogging.services;

import com.pragyakantjha.blogging.entities.User;
import com.pragyakantjha.blogging.payload.UserDto;

import java.util.List;

public interface UserService {

    UserDto registerUser(UserDto userDto);
    UserDto createUser(UserDto userDto);
    UserDto updateUser(UserDto userDto, Integer userId);
    UserDto getUserById(Integer userId);
    List<UserDto> getAllUsers();
    void deleteUser(Integer userId);
    Boolean doesUserExist(String email);
}
