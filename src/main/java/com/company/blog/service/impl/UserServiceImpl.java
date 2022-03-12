package com.company.blog.service.impl;

import com.company.blog.config.ModelMapperConfiguration;
import com.company.blog.data.dto.request.LoginRequestDto;
import com.company.blog.data.dto.request.RegisterRequestDto;
import com.company.blog.data.entity.Role;
import com.company.blog.data.entity.User;
import com.company.blog.data.repository.RoleRepository;
import com.company.blog.data.repository.UserRepository;
import com.company.blog.enums.ErrorCase;
import com.company.blog.resource.JWTAuthResponse;
import com.company.blog.security.JwtTokenProvider;
import com.company.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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

    private static final Logger LOG = LogManager.getLogger(JwtTokenProvider.class);

    @Value("${spring.mail.username}")
    private String owningEmail;

    @Value("${my.message.subject}")
    private String messageSubject;

    @Value("${my.message.body}")
    private String messageBody;

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final JavaMailSender javaMailSender;

    @Override
    public JWTAuthResponse login(LoginRequestDto loginRequestDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.getUsernameOrEmail(), loginRequestDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenProvider.generateToken(authentication);
        return new JWTAuthResponse(token);
    }

    @Transactional
    @Override
    public ResponseEntity<String> register(RegisterRequestDto registerRequestDto) {
        if (userRepository.existsByUsername(registerRequestDto.getUsername())) {
            return new ResponseEntity<>(ErrorCase.USERNAME_ALREADY_TAKEN.getMessage(), HttpStatus.BAD_REQUEST);
        } else if (userRepository.existsByEmail(registerRequestDto.getEmail())) {
            return new ResponseEntity<>(ErrorCase.EMAIL_ALREADY_TAKEN.getMessage(), HttpStatus.BAD_REQUEST);
        }
        Role role = roleRepository.findRoleById(1L);
        User user = ModelMapperConfiguration.map(registerRequestDto, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Collections.singleton(role));
        User saveUser = userRepository.save(user);
        sendEmail(saveUser.getEmail(), messageSubject, messageBody);
        return new ResponseEntity<>(ErrorCase.SUCCESSFULLY_REGISTER.getMessage(), HttpStatus.OK);
    }

    private void sendEmail(String toEmail, String subject, String body) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(owningEmail);
        msg.setTo(toEmail);
        msg.setText(body);
        msg.setSubject(subject);
        javaMailSender.send(msg);
        LOG.info("Message successfully send");
    }
}
