package github.login.config;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import github.login.model.Rol;
import github.login.model.User;
import github.login.service.LoginService;

@Configuration
public class LoadDatabase {
    @Bean
    CommandLineRunner initDatabase(LoginService loginService) {
        return args -> {

            List<User> lista = loginService.userList();

            if (lista.isEmpty()) {

                loginService.addUser(new User("admin", "admin@gmail.com","1234", Rol.ADMIN));
                loginService.addUser(new User("user", "user@gmail.com","1234", Rol.USER));
                loginService.addUser(new User("diego", "diego@gmail.com","1234", Rol.ADMIN));

                System.out.println("Usuarios cargados correctamente.");

            } else {
                System.out.println("Datos ya existen. No se cargaron.");
            }

        };
    }
}
