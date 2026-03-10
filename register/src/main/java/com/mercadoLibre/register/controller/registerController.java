package com.mercadoLibre.register.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercadoLibre.register.model.User;
import com.mercadoLibre.register.service.registerService;

import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;



@RequestMapping("/api-v1/register")
@RestController
@AllArgsConstructor
public class registerController {

    private final registerService registerService;

    @PostMapping
    public ResponseEntity<?> addUserEntity (@RequestBody User user) {
        registerService.addUser(user);
        ResponseEntity<?> entity = ResponseEntity.ok().body("Usuario registrado ");
        return entity;
    }

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers () {
        return ResponseEntity.ok().body(registerService.getUserList());
    }
    


}
