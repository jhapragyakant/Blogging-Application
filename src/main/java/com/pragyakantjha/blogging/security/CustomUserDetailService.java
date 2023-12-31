package com.pragyakantjha.blogging.security;

import com.pragyakantjha.blogging.entities.User;
import com.pragyakantjha.blogging.exceptions.ResourceNotFoundException;
import com.pragyakantjha.blogging.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // loading user from database from username
        User user = this.userRepository.findByEmail(username).orElseThrow(()->new ResourceNotFoundException("User","email",username));
        return user;
    }
}
