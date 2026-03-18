package github.login.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import github.login.model.User;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService {

    public final List<User> userList = new ArrayList<>();
    private final RestTemplate restTemplate;
    
    public List<User> userList() {
        return userList;
    }

    public void addUser(User user) {
        userList.add(user);
    }

     public Boolean login(User user) {
        //cambiar a localhost o register-service
        String url = "http://register-service:8081/api-v1/register/login/" + user.getEmail() + "/" + user.getPassword();
        Boolean externalAnswer = restTemplate.getForObject(url, Boolean.class);
        return externalAnswer;
    }

}
