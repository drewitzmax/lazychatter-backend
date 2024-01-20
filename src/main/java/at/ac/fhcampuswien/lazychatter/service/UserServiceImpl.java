package at.ac.fhcampuswien.lazychatter.service;

import at.ac.fhcampuswien.lazychatter.error.UserAlreadyExistsException;
import at.ac.fhcampuswien.lazychatter.model.dto.UserDto;
import at.ac.fhcampuswien.lazychatter.model.dto.UserInput;
import at.ac.fhcampuswien.lazychatter.model.jpa.User;
import at.ac.fhcampuswien.lazychatter.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public void createUser(UserInput user){
        validateUserInput(user);
        Optional<User> existingUser = userRepository.findUserByUsername(user.username());

        if(existingUser.isPresent()) throw new UserAlreadyExistsException("Username is already taken");
        userRepository.save(new User(user));
    }

    @Override
    public User getUserByName(String name) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByUsername(name);
        if(user.isEmpty()){
            throw new UsernameNotFoundException("User could not be found");
        }
        return user.get();
    }

    @Override
    public User getMe(Authentication auth) throws UsernameNotFoundException {
        User me = getUserByName(auth.getName());
        me.getChatList().size();
        return me;
    }

    @Override
    public List<String> getUserList() {
        return this.userRepository.findAll().stream().map(user -> user.getUsername()).toList();
    }

    @Override
    public void updateMe(UserInput user, Authentication auth) {
        User me = getMe(auth);
        me.setUsername(user.username());
        me.setPassword(user.password());
        userRepository.saveAndFlush(me);
    }

    @Override
    @Transactional
    public void deleteMe(Authentication auth) {
        userRepository.deleteByUsername(auth.getName());
    }

    private void validateUserInput(UserInput user) throws IllegalArgumentException{
        validateUsername(user.username());
        validatePassword(user.password());
    }

    private void validateUsername(String username){
        if(username == null || username.length() < 3) throw new IllegalArgumentException("Username may not be less than 3 characters");
    }

    private void validatePassword(String password){
        if(password == null || password.length() < 6 || password.length() > 128){
            throw new IllegalArgumentException("Password must be between 6 and 128 characters long!");
        }
    }
}
