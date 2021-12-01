package com.example.softunifinalproject.service.impl;

import com.example.softunifinalproject.model.binding.UserRegisterBindingModel;
import com.example.softunifinalproject.model.entity.UserEntity;
import com.example.softunifinalproject.model.entity.UserRoleEntity;
import com.example.softunifinalproject.model.entity.enums.UserRolesEnum;
import com.example.softunifinalproject.repository.UserRepository;
import com.example.softunifinalproject.repository.UserRoleRepository;
import com.example.softunifinalproject.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;


    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }


    @Override
    public void initializeUserRoles() {
        if (userRoleRepository.count() == 0){

            UserRoleEntity adminRole = new UserRoleEntity();
            adminRole.setRole(UserRolesEnum.ADMIN);

            UserRoleEntity userRole = new UserRoleEntity();
            userRole.setRole(UserRolesEnum.USER);

            userRoleRepository.saveAll(List.of(adminRole, userRole));
        }

    }

    @Override
    public void registerUser(UserRegisterBindingModel userModel) {

        UserEntity newUser = new UserEntity();

        newUser.setRoles(Set.of(userRoleRepository.getById(2L)))
                .setUsername(userModel.getUsername())
                .setPassword(passwordEncoder.encode(userModel.getPassword()))
                .setRating(0.00);

        userRepository.save(newUser);
    }

    @Override
    public UserEntity findByUsername(String name) {
        return userRepository.findByUsername(name).orElse(null);


    }

    @Override
    public boolean isUsernameFree(String username) {
        boolean empty = userRepository.findByUsernameIgnoreCase(username).isEmpty();

        return empty;

    }


    @Override
    @Transactional
    public void initializeUser() {
        if (userRepository.count() == 0){
            UserEntity admin = new UserEntity();

            admin.setUsername("admin")
                    .setPassword(passwordEncoder.encode("admin"))
                    .setRoles(Set.of(userRoleRepository.getById(1L), userRoleRepository.getById(2L)));

            userRepository.save(admin);
        }
    }


}
