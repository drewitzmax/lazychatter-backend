package at.ac.fhcampuswien.lazychatter.model.jpa;

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
}
