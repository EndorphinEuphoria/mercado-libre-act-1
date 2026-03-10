package com.mercadoLibre.register.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mercadoLibre.register.model.Rol;
import com.mercadoLibre.register.model.User;

@Service
public class registerService {
    
 public List<User> userList = new ArrayList<>();

 public void addUser(User user){
    Rol rol = new Rol(1L, "USER");
    user.setRol(rol);
    userList.add(user);
 }

 public List<User> getUserList(){
    return userList;
 }
  
}
