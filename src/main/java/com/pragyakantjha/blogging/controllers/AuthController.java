package com.pragyakantjha.blogging.controllers;

import com.pragyakantjha.blogging.exceptions.ApiException;
import com.pragyakantjha.blogging.payload.JwtAuthRequest;
import com.pragyakantjha.blogging.payload.JwtAuthResponse;
import com.pragyakantjha.blogging.payload.UserDto;
import com.pragyakantjha.blogging.security.JwtTokenHelper;
import com.pragyakantjha.blogging.services.UserService;
import com.pragyakantjha.blogging.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> createToken(
            @RequestBody JwtAuthRequest request
    ) throws Exception {
        this.authenticate(request.getUsername(), request.getPassword());
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
        String token = this.jwtTokenHelper.generateToken(userDetails);
        return new ResponseEntity<>(new JwtAuthResponse(token), HttpStatus.OK);
    }

    private void authenticate(String username, String password) throws Exception {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        try{
            authenticationManager.authenticate(authenticationToken);
        }catch(BadCredentialsException e){
            System.out.println("Invalid Details!!");
            throw new ApiException("Invalid username or password");
        }

    }

    @PostMapping("/register")
    public ResponseEntity<?> registerNewUser(@Valid @RequestBody UserDto userDto){
        if(userService.doesUserExist(userDto.getEmail())){
            return new ResponseEntity<>(new ApiResponse(false,"Email already exists!"), HttpStatus.CONFLICT);
        }
        UserDto registeredUser = userService.registerUser(userDto);
        return new ResponseEntity<>(registeredUser,HttpStatus.CREATED);
    }
}
