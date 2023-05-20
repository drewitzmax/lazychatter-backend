package at.ac.fhcampuswien.lazychatter.rest;

import at.ac.fhcampuswien.lazychatter.error.UserAlreadyExistsException;
import at.ac.fhcampuswien.lazychatter.model.dto.UserDto;
import at.ac.fhcampuswien.lazychatter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(path = "/register",
            consumes= MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> register(@RequestBody UserDto user){
        try{
            userService.createUser(user);
            return ResponseEntity.ok("User successfully created!");
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (UserAlreadyExistsException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
