package at.ac.fhcampuswien.lazychatter.model.jpa;

import at.ac.fhcampuswien.lazychatter.model.dto.UserRegistrationRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
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
    private UserType type;
    @Column(name="PASS_HASH")
    @JsonIgnore
    private String passwordHash;
    @ManyToMany
    @JoinTable(name="USER_CHAT")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Chat> chatList;
    @OneToMany(mappedBy = "owner")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnoreProperties("owner")
    private List<Course> myCourses;
    @ManyToMany
    @JoinTable(name = "COURSES_LECTURERS")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private List<Course> lecturingCourses;
    @ManyToMany
    @JoinTable(name = "COURSES_ATTENDEES")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnoreProperties("attendees")
    private List<Course> attendingCourses;


    public User() {
        this.username = null;
        this.passwordHash= null;
    }

    public User(UserRegistrationRequest userRegistrationRequest){
        this.username = userRegistrationRequest.username();
        this.passwordHash = this.hashPassword(userRegistrationRequest.password());
        this.type = UserType.get(userRegistrationRequest.type());
    }

    public String getId() {
        return id;
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("USER"));
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return this.passwordHash;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.passwordHash = this.hashPassword(password);
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

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public List<Course> getMyCourses() {
        return myCourses;
    }

    public void setMyCourses(List<Course> myCourses) {
        this.myCourses = myCourses;
    }

    public List<Course> getLecturingCourses() {
        return lecturingCourses;
    }

    public void setLecturingCourses(List<Course> lecturingCourses) {
        this.lecturingCourses = lecturingCourses;
    }

    public List<Course> getAttendingCourses() {
        return attendingCourses;
    }

    public void setAttendingCourses(List<Course> attendingCourses) {
        this.attendingCourses = attendingCourses;
    }
}
