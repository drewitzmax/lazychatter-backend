package at.ac.fhcampuswien.lazychatter.model.jpa;

import at.ac.fhcampuswien.lazychatter.model.dto.MessageDTO;
import jakarta.persistence.*;

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
    @ManyToOne(optional = false)
    @JoinColumn(name="SENDER_ID", nullable = false, updatable = false)
    private User sender;
    @Column(name = "AI_OPTION")
    @Enumerated(EnumType.STRING)
    private AiMessageOption aiOption;

    public Message(MessageDTO message, User sender, Chat chat){
        textMessage = message.getMessageText();
        aiOption = AiMessageOption.valueOf(message.getAiOptions());
        this.chat = chat;
        this.sender = sender;
    }
}
