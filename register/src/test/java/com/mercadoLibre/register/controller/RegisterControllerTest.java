package com.mercadoLibre.register.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import com.mercadoLibre.register.model.Rol;
import com.mercadoLibre.register.model.User;
import com.mercadoLibre.register.service.registerService;

import tools.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(registerController.class)
public class RegisterControllerTest {   

    @MockitoBean
    private registerService registerService;

    @Autowired
    private MockMvc mockMvc;


    @Test
    void getAllUsers_returnsOkJson() throws Exception {
        Rol rol = new Rol(1L,"user");
        List<User> userTestList = Arrays.asList(new User("Test", "test@gmail.com", "123456",rol),
        new User("Test2", "test2@gmail.com", "123456",rol));

        when(registerService.getUserList()).thenReturn(userTestList);
        
        mockMvc.perform(get("/api-v1/register/users")).andExpect(status().isOk()).andExpect(jsonPath("$").isArray());
    }


    @Test
    void saveUser_returnsCreated() throws Exception{
        Rol rol = new Rol(1L,"user");
        User userTest = new User("Test", "test@gmail.com", "123456",rol);

        doNothing().when(registerService).addUser(userTest);


        ObjectMapper objectMapper = new ObjectMapper();
        String JsonUser = objectMapper.writeValueAsString(userTest);

        mockMvc.perform(post("/api-v1/register").contentType(MediaType.APPLICATION_JSON).content(JsonUser)).andExpect(status().isCreated())
        .andExpect(content().string("usuario creado con exito"));
    } 


    @Test
    void saveUser_returnsBadRequest() throws Exception{
        User userTest = new User();

        ObjectMapper objectMapper = new ObjectMapper();
        String JsonUser = objectMapper.writeValueAsString(userTest);

        mockMvc.perform(post("/api-v1/register").contentType(MediaType.APPLICATION_JSON).content(JsonUser)).andExpect(status().isBadRequest())
        .andExpect(content().string("el nombre del usuario es requerido"));
    }

    @Test
    void getAllUsers_returnsNoContent() throws Exception {
        
        when(registerService.getUserList()).thenReturn(Arrays.asList());

        mockMvc.perform(get("/api-v1/register/users")).andExpect(status().isNoContent())
        .andExpect(content().string("no hay usuarios registrados"));
    }




    

}
