package github.login.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import github.login.model.User;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService {

    public final List<User> userList = new ArrayList<>();
    
    public List<User> userList() {
        return userList;
    }

    public void addUser(User user) {
        userList.add(user);
    }
    
    // obviamente encriptariamos la contraseña 
    // profe por favor perdonenos
    // por no hacerlo realista :(
    public Boolean login(String email, String password) {
        for (User user : userList) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

}
