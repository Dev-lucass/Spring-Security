package com.example.Authorization.Resource.repository;

import com.example.Authorization.Resource.model.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLoginRepository extends JpaRepository<UserLogin, Long> {

    UserLogin findByUsername(String username);
}
