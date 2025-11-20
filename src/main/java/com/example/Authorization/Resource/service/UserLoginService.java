package com.example.Authorization.Resource.service;

import com.example.Authorization.Resource.model.UserLogin;
import com.example.Authorization.Resource.repository.UserLoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserLoginService {

    private final PasswordEncoder encoder;
    private final UserLoginRepository repository;

    public void save(UserLogin user) {
        user.setPassword(encoder.encode(user.getPassword()));
        repository.save(user);
    }

    public List<UserLogin> listAll() {
        return repository.findAll();
    }

    public UserLogin findByUsername(String username) {
        return repository.findByUsername(username);
    }

}
