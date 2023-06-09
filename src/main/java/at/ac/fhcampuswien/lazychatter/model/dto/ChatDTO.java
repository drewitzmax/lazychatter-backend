package at.ac.fhcampuswien.lazychatter.model.dto;

import at.ac.fhcampuswien.lazychatter.model.jpa.Chat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ChatDTO {
    @JsonProperty
    private String chatId;
    @JsonProperty
    private List<String> paritcipants;
    @JsonProperty
    private List<MessageDTO> messages;

    public ChatDTO(){}

    public ChatDTO(Chat chat){
        this.chatId = chat.getId();
        this.paritcipants = chat.getParticipants().parallelStream().map(user -> user.getUsername()).toList();
        this.messages = chat.getMessages().parallelStream().map(message -> new MessageDTO(message)).toList();
    }

}
