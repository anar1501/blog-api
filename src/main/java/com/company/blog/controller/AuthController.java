package com.company.blog.controller;

import com.company.blog.data.dto.request.LoginRequestDto;
import com.company.blog.enums.ErrorCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.company.blog.enums.ErrorCase.SUCCESS_LOGIN;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;

    @PostMapping("sign-in")
    public ResponseEntity<String> signIn(@RequestBody LoginRequestDto loginRequestDto) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.getUsernameOrEmail(), loginRequestDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        return new ResponseEntity<>(SUCCESS_LOGIN.getMessage(), HttpStatus.OK);
    }

}
