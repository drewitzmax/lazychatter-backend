package at.ac.fhcampuswien.lazychatter.service;

import at.ac.fhcampuswien.lazychatter.error.UserAlreadyExistsException;
import at.ac.fhcampuswien.lazychatter.model.dto.UserDto;
import at.ac.fhcampuswien.lazychatter.model.dto.UserInput;
import at.ac.fhcampuswien.lazychatter.model.jpa.User;
import at.ac.fhcampuswien.lazychatter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public void createUser(UserInput user){
        if(user.getUsername() == null) throw new IllegalArgumentException("Username may not be null");
        if(user.getPassword() == null || user.getPassword().length() < 6 || user.getPassword().length() > 128){
            throw new IllegalArgumentException("Password must be between 6 and 128 characters long!");
        }
        UserDto existingUser = getUserByName(user.getUsername());

        if(existingUser != null) throw new UserAlreadyExistsException("Username is already taken");
        userRepository.save(new User(user));
    }

    @Override
    public UserDto getUserByName(String name) {
        User user = userRepository.getUserByUsername(name);
        if(user == null){
            return null;
        }
        return new UserDto(user);
    }

    public User getUserDetailsByName(String name){
        return userRepository.getUserByUsername(name);
    }
}
