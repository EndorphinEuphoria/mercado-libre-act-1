package github.login;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import github.login.controller.LoginController;
import github.login.model.Rol;
import github.login.model.User;
import github.login.service.LoginService;
import lombok.RequiredArgsConstructor;
import tools.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(LoginController.class)
@RequiredArgsConstructor
public class LoginControllerTest {

    @MockitoBean
    private LoginService loginService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void login_returnsOk_whenCredentialsAreValid() throws Exception {

        User userTest = new User("Test", "test@gmail.com", "123456", Rol.USER);

        when(loginService.login(userTest.getEmail(), userTest.getPassword())).thenReturn(true);

        ObjectMapper objectMapper = new ObjectMapper();
        String JsonUser = objectMapper.writeValueAsString(userTest);

        mockMvc.perform(post("/api-v1/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUser))
                .andExpect(status().isOk())
                .andExpect(content().string("Sesión iniciada con éxito."));
    }

    @Test
    void login_returnsBadRequest_whenCredentialsAreInvalid() throws Exception {

        User userTest = new User("Test", "test@gmail.com", "wrongpass", Rol.USER);

        when(loginService.login(userTest.getEmail(), userTest.getPassword())).thenReturn(false);

        ObjectMapper objectMapper = new ObjectMapper();
        String JsonUser = objectMapper.writeValueAsString(userTest);

        mockMvc.perform(post("/api-v1/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUser))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Email o contraseña inválidos."));
    }
}


