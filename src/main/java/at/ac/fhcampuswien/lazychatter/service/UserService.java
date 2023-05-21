package at.ac.fhcampuswien.lazychatter.service;

import at.ac.fhcampuswien.lazychatter.model.dto.UserDto;
import at.ac.fhcampuswien.lazychatter.model.dto.UserInput;
import at.ac.fhcampuswien.lazychatter.model.jpa.User;

public interface UserService {
    void createUser(UserInput user);

    UserDto getUserByName(String name);

    User getUserDetailsByName(String name);
}
