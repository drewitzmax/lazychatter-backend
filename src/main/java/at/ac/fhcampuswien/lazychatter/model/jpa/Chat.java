package at.ac.fhcampuswien.lazychatter.model.jpa;

import jakarta.persistence.*;

import java.util.List;

@Entity(name="chat")
@Table(name="CHAT")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @ManyToMany(mappedBy = "chatList")
    private List<User> participants;
    @OneToMany(mappedBy = "chat")
    private List<Message> messages;

}
