package com.pragyakantjha.blogging.controllers;

import com.pragyakantjha.blogging.payload.UserDto;
import com.pragyakantjha.blogging.services.UserService;
import com.pragyakantjha.blogging.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;


    //POST - create user
    @PostMapping("/create")
    public ResponseEntity<?> createUser( @Valid @RequestBody UserDto userDto){
        if(userService.doesUserExist(userDto.getEmail())){
            return new ResponseEntity<>(new ApiResponse(false,"Email already exists!"), HttpStatus.CONFLICT);
        }
        UserDto createdUserDto = userService.createUser(userDto);
        return new ResponseEntity<>(createdUserDto, HttpStatus.CREATED);
    }

    //PUT - update user
    @PutMapping("/update/{userId}")
    public ResponseEntity<UserDto> updateUser( @Valid @RequestBody UserDto userDto, @PathVariable("userId") Integer userId){
        UserDto updatedUserDto = userService.updateUser(userDto,userId);
        return new ResponseEntity<>(updatedUserDto,HttpStatus.OK);
    }

    //DELETE - delete user
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer id){
        userService.deleteUser(id);
        return new ResponseEntity<>(new ApiResponse(true, "user deleted successfully"), HttpStatus.OK);
    }
    //GET - get user
    @GetMapping("/get_all")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId){
        return ResponseEntity.ok(userService.getUserById(userId));
    }
}
