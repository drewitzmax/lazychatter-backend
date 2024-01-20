package at.ac.fhcampuswien.lazychatter.service;

import at.ac.fhcampuswien.lazychatter.model.dto.UserRegistrationRequest;
import at.ac.fhcampuswien.lazychatter.model.jpa.User;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface UserService {
    void createUser(UserRegistrationRequest user);

    User getUserByName(String name);
    User getMe(Authentication auth);

    List<String> getUserList();

    void updateMe(UserRegistrationRequest user, Authentication auth);

    void deleteMe(Authentication auth);
}
