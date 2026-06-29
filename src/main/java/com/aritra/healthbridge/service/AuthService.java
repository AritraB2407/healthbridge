package com.aritra.healthbridge.service;

import com.aritra.healthbridge.auth.AuthResponse;
import com.aritra.healthbridge.auth.RegisterRequest;
import com.aritra.healthbridge.entity.UserAccount;
import com.aritra.healthbridge.enums.Roles;
import com.aritra.healthbridge.repository.UserAccountRepository;
import com.aritra.healthbridge.util.JwtUtil;
import io.jsonwebtoken.security.Password;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class AuthService {

    private final UserAccountRepository userAccountRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwt;

    public AuthResponse register(RegisterRequest request){

        UserAccount user = new UserAccount(
                null,
                request.userName(),
                passwordEncoder.encode(request.password()),
                Roles.valueOf(request.role().toUpperCase())
        );
        userAccountRepository.save(user);
        String token = jwt.generateToken(user.getUserName());
        return new AuthResponse(token);
    }

    public AuthResponse login(RegisterRequest request){

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken( request.userName(),request.password()));

        String token = jwt.generateToken(request.userName());
        return new AuthResponse(token);
    }
}
