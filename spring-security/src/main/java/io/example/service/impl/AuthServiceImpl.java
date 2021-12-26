package io.example.service.impl;

import io.example.mapper.UserMapper;
import io.example.model.dto.UserDTO;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.example.model.domain.User;
import io.example.service.AuthService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final UserMapper userMapper;
    private final AuthenticationManager authManager;

    @Override
    public UserDTO login(String username, String password) {
        final Authentication authenticate = authManager
            .authenticate(new UsernamePasswordAuthenticationToken(username, password));
        final User user = (User) authenticate.getPrincipal();
        return userMapper.build(user);
    }    
    
}