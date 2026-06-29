package com.aritra.healthbridge.controller;

import com.aritra.healthbridge.auth.AuthResponse;
import com.aritra.healthbridge.auth.RegisterRequest;
import com.aritra.healthbridge.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest request){
        return authService.register(request);
    }
    @PostMapping("/login")
    public AuthResponse login(@RequestBody RegisterRequest request){
        return authService.login(request);
    }
}
