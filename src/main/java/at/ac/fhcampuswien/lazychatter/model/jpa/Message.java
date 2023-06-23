package at.ac.fhcampuswien.lazychatter.model.jpa;

import at.ac.fhcampuswien.lazychatter.model.dto.MessageDTO;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity(name = "message")
@Table(name = "MESSAGE")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "TEXT_MESSAGE", length = 1000)
    private String textMessage;
    @ManyToOne
    private Chat chat;
    @ManyToOne()
    @JoinColumn(name="SENDER_ID", updatable = false)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private User sender;
    @Column(name = "AI_OPTION")
    @Enumerated(EnumType.STRING)
    private AiMessageOption aiOption;

    public Message(MessageDTO message, User sender, Chat chat){
        textMessage = message.getMessageText();
        if(message.getAiOptions() != null)
            aiOption = AiMessageOption.valueOf(message.getAiOptions());
        this.chat = chat;
        this.sender = sender;
    }

    public Message() {

    }

    public String getId() {
        return id;
    }

    public String getTextMessage() {
        return textMessage;
    }

    public Chat getChat() {
        return chat;
    }

    public User getSender() {
        return sender;
    }

    public AiMessageOption getAiOption() {
        return aiOption;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }
}
