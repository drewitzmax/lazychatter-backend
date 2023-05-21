package at.ac.fhcampuswien.lazychatter.model.jpa;

import at.ac.fhcampuswien.lazychatter.model.dto.UserDto;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity(name="user")
@Table(name="USERS")
public class User implements UserDetails {
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public String getPassword() {
        return this.passwordHash;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public List<Chat> getChatList() {
        return chatList;
    }

    private String hashPassword(String password){
        return new BCryptPasswordEncoder().encode(password);
    }
}
