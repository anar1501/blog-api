package com.company.blog.service.impl;

import com.company.blog.config.ModelMapperConfiguration;
import com.company.blog.data.dto.request.LoginRequestDto;
import com.company.blog.data.dto.request.RegisterRequestDto;
import com.company.blog.data.entity.Role;
import com.company.blog.data.entity.User;
import com.company.blog.data.repository.RoleRepository;
import com.company.blog.data.repository.UserRepository;
import com.company.blog.enums.ErrorCase;
import com.company.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    @Override
    public void login(LoginRequestDto loginRequestDto) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.getUsernameOrEmail(), loginRequestDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
    }

    @Transactional
    @Override
    public ResponseEntity<String> register(RegisterRequestDto registerRequestDto) {
        if (userRepository.existsByUsername(registerRequestDto.getUsername())) {
            return new ResponseEntity<>(ErrorCase.USERNAME_ALREADY_TAKEN.getMessage(), HttpStatus.BAD_REQUEST);
        } else if (userRepository.existsByEmail(registerRequestDto.getEmail())) {
            return new ResponseEntity<>(ErrorCase.EMAIL_ALREADY_TAKEN.getMessage(), HttpStatus.BAD_REQUEST);
        }
        Role role = roleRepository.findById(registerRequestDto.getRoleId()).get();
        User user = ModelMapperConfiguration.map(registerRequestDto, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Collections.singleton(role));
        userRepository.save(user);
        return new ResponseEntity<>(ErrorCase.SUCCESSFULLY_REGISTER.getMessage(), HttpStatus.OK);
    }
}
