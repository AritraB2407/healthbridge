package com.aritra.healthbridge.service;

import com.aritra.healthbridge.entity.UserAccount;
import com.aritra.healthbridge.exception.ResourceNotFoundException;
import com.aritra.healthbridge.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserAccountRepository userAccountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount user =  userAccountRepository.findByUserName(username).orElseThrow(()->new ResourceNotFoundException("User not found "+username));

        return User.builder()
                .username(user.getUserName())
                .password(user.getPassword())
                .authorities(new SimpleGrantedAuthority("ROLE_"+user.getRole().name()))
                .build();
    }
}
