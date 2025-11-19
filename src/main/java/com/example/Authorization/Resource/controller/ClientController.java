package com.example.Authorization.Resource.controller;

import com.example.Authorization.Resource.model.Client;
import com.example.Authorization.Resource.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService service;

    @PostMapping("/client")
    public ResponseEntity<?> saveClient(@RequestBody Client client) {
        try {
            service.save(client);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/authenticated")
    public ResponseEntity<?> endpointSecured() {
        return ResponseEntity.ok().build();
    }


}
