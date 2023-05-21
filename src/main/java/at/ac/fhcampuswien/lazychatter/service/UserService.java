package at.ac.fhcampuswien.lazychatter.service;

import at.ac.fhcampuswien.lazychatter.model.dto.UserDto;
import at.ac.fhcampuswien.lazychatter.model.dto.UserInput;

public interface UserService {
    void createUser(UserInput user);

    UserDto getUserByName(String name);
}
