package com.learning.redditclone.service;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import com.learning.redditclone.model.User;
import com.learning.redditclone.repository.UserRepository;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(username);

        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("No user found with Username" + username));

        return new org.springframework.security.core.userdetails
            .User(
                user.getUsername(), 
                user.getPassword(), 
                user.isEnabled(),
                true,
                true,
                true,
                getAuthorities("User")
            );
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String role){
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }
    
}