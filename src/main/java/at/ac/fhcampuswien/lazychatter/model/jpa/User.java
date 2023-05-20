package at.ac.fhcampuswien.lazychatter.model.jpa;

import at.ac.fhcampuswien.lazychatter.model.dto.UserDto;
import jakarta.persistence.*;

import java.util.List;

@Entity(name="user")
@Table(name="USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name="USERNAME", unique = true, length = 50)
    private String username;
    @Column(name="PASS_HASH")
    private String passwordHash;
    @ManyToMany
    @JoinTable(name="USER_CHAT")
    private List<Chat> chatList;

    public User() {
        this.username = null;
        this.passwordHash= null;
    }

    public User(UserDto userDto){
        this.username = userDto.getUsername();
        this.passwordHash = this.hashPassword(userDto.getPassword());
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public List<Chat> getChatList() {
        return chatList;
    }

    private String hashPassword(String password){
        //TODO: Include Password hashing
        return password;
    }
}
