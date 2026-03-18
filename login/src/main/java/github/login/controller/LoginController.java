package github.login.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import github.login.model.User;
import github.login.service.LoginService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api-v1/login")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping
    public ResponseEntity<String> login(@RequestBody User user) {
        if (loginService.login(user)) {
            return ResponseEntity.status(HttpStatus.OK).body("Sesión iniciada con éxito.");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email o contraseña inválidos.");
    }

}
