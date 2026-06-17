package com.aritra.healthbridge.controller;

import com.aritra.healthbridge.service.CustomUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final CustomUserDetailsService customUserDetailsService;



}
