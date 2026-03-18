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

   if(containsUser(user.getEmail())){
      throw new IllegalAccessError("El email ya esta registrado");
   }
   if(user.getEmail() == null || user.getEmail().trim().isBlank()){
      throw new IllegalAccessError("El email no puede estar vacío");
   } 
    if(user.getPassword()== null || user.getPassword().trim().isBlank()){
      throw new IllegalAccessError("El password no puede estar vacío");
   } 
    if(user.getName() == null || user.getName().trim().isBlank()){
      throw new IllegalAccessError("El nombre no puede estar vacío");
   }  

    Rol rol = new Rol(1L, "USER");
    user.setRol(rol);
    userList.add(user);
 }

 public List<User> getUserList(){
    return userList;
 }

 public User findUserByEmail(String email){
   for(User us : userList){
         if(email == us.getEmail()){
         return us;
      }
   }
   return null;
}

public boolean containsUser (String email){
   for(User us: userList){
      if(email.equals(us.getEmail())){
         return true;
      }
   }
   return false;
}

public boolean loginReturnsB(String email , String password){
   for(User us: userList){
      if(email.equals(us.getEmail()) && password.equals(us.getPassword())){
         return true;
      }
   }
   return false;

}

}
