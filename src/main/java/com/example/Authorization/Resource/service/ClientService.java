package com.example.Authorization.Resource.service;

import com.example.Authorization.Resource.model.Client;
import com.example.Authorization.Resource.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final PasswordEncoder encoder;
    private final ClientRepository repository;

    public void save(Client client) {
        client.setClientSecret(encoder.encode(client.getClientSecret()));
        repository.save(client);
    }

    public List<Client> listAll() {
        return repository.findAll();
    }

    public Client findByClientId(String clientId) {
        return repository.findByClientId(clientId);
    }


}
