package com.example.Authorization.Resource.repository;

import com.example.Authorization.Resource.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByClientId(String clientId);

}
