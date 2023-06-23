package at.ac.fhcampuswien.lazychatter.model.jpa;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "chat")
@Table(name = "CHAT")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @ManyToMany(mappedBy = "chatList")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<User> participants;
    @OneToMany(mappedBy = "chat")
    private List<Message> messages;

    public Chat(){
        this.participants = new ArrayList<>();
        this.messages = new ArrayList<>();
    }

    public void setId(String id) {
        this.id = id;
    }

    public void addParticipant(User participant){
        this.participants.add(participant);
        participant.getChatList().add(this);
    }

    public void addMessage(Message message){
        this.messages.add(message);
    }

    public String getId() {
        return id;
    }

    public List<User> getParticipants() {
        return participants;
    }

    public List<Message> getMessages() {
        return messages;
    }
}
