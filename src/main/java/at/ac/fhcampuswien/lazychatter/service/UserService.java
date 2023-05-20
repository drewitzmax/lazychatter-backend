package at.ac.fhcampuswien.lazychatter.service;

import at.ac.fhcampuswien.lazychatter.model.dto.UserDto;

public interface UserService {
    void createUser(UserDto user);
}
