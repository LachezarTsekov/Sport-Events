package com.example.softunifinalproject.service.impl;

import com.example.softunifinalproject.model.entity.UserEntity;
import com.example.softunifinalproject.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicationUserServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public ApplicationUserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Username " + username +" not found"));
        return mapToUserDetails(userEntity);
    }


    private static UserDetails mapToUserDetails(UserEntity userEntity){

        List<GrantedAuthority> authorities =
                userEntity.getRoles()
                .stream()
                .map(r -> new SimpleGrantedAuthority("ROLE_" + r.getRole().name()))
                .collect(Collectors.toList());

        return new User(
                userEntity.getUsername(),
                userEntity.getPassword(),
                authorities
        );

    }
}
