package ch.zli.m223.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.jwt.Claims;

import ch.zli.m223.model.User;
import io.smallrye.jwt.build.Jwt;

@ApplicationScoped
public class AuthService {
    @Inject
    private UserService userService;
    
    public String generateToken() {
        String token =
           Jwt.issuer("https://example.com/issuer") 
             .upn("jdoe@quarkus.io") 
             .groups(new HashSet<>(Arrays.asList("User", "Admin"))) 
             .claim(Claims.birthdate.name(), "2001-07-13") 
           .sign();
        return token;
    }

    public boolean userExists(String username, String password) {
        boolean doesExist = false;
        List<User> users = userService.findAll();
        for(User user : users) {
            if(user.getUsername().equals(username) && user.getPassword().equals(password)) {
                doesExist = true;
            }
        }
        return doesExist;
    }
}
