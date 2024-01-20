package at.ac.fhcampuswien.campattend.rest;

import at.ac.fhcampuswien.campattend.model.jpa.User;
import at.ac.fhcampuswien.campattend.security.JwtService;
import at.ac.fhcampuswien.campattend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    JwtService jwtService;
    @Autowired
    UserService userService;
    @PostMapping
    public String login(Authentication auth){
        User user = userService.getUserByName(auth.getName());
        if(user == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Userdata could not be found.");
        }
        return jwtService.generateToken(user);
    }
}
