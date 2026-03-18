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
import org.springframework.web.bind.annotation.PathVariable;


@RequestMapping("/api-v1/register")
@RestController
@AllArgsConstructor
public class registerController {

    private final registerService registerService;

    @PostMapping
    public ResponseEntity<?> addUserEntity (@RequestBody User user) {
          try {
             registerService.addUser(user);
             return ResponseEntity.status(HttpStatus.CREATED).body("usuario creado con exito");
          } catch (IllegalAccessError e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
          }
    }

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers () {
        if(registerService.getUserList().isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("no hay usuarios registrados");
        }
        return ResponseEntity.ok().body(registerService.getUserList());
    }
    
    @GetMapping("/users/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(registerService.findUserByEmail(email));
        } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.NO_CONTENT).body("no hay usuario con ese email");
        }
    }

    @GetMapping("/login/{email}/{password}")
    public ResponseEntity<?> loginReturnsBoolean(@PathVariable String email,@PathVariable String password) {

    return ResponseEntity.status(HttpStatus.ACCEPTED).body(registerService.loginReturnsB(email, password));

    }

}
