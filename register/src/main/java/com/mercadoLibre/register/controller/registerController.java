package com.mercadoLibre.register.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mercadoLibre.register.model.User;
import com.mercadoLibre.register.service.registerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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

        if(user.getName() == null||user.getName().trim().isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("el nombre del usuario es requerido");
        }
         if(user.getEmail() == null||user.getEmail().trim().isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("el email del usuario es requerido");
         }
          if(user.getPassword() == null||user.getPassword().trim().isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("la contraseña del usuario es requerida");
          }
        registerService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("usuario creado con exito");
    }

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers () {
        if(registerService.getUserList().isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("no hay usuarios registrados");
        }
        return ResponseEntity.ok().body(registerService.getUserList());
    }
    


}
