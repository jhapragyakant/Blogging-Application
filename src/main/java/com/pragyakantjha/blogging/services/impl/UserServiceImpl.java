package com.pragyakantjha.blogging.services.impl;

import com.pragyakantjha.blogging.entities.User;
import com.pragyakantjha.blogging.payload.UserDto;
import com.pragyakantjha.blogging.repositories.UserRepository;
import com.pragyakantjha.blogging.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = convertToUser(userDto);
        User savedUser = userRepository.save(user);
        return convertToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        return null;
    }

    @Override
    public UserDto getUserById(Integer userId) {
        return null;
    }

    @Override
    public List<UserDto> getAllUsers() {
        return null;
    }

    @Override
    public void deleteUser(Integer userId) {

    }

    public UserDto convertToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    public User convertToUser(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

}
