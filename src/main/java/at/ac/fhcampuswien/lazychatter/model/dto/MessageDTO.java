package at.ac.fhcampuswien.lazychatter.model.dto;

import at.ac.fhcampuswien.lazychatter.model.jpa.AiMessageOption;
import at.ac.fhcampuswien.lazychatter.model.jpa.Message;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MessageDTO {
    @JsonProperty
    String id;
    @JsonProperty
    String chatID;
    @JsonProperty
    String messageText;
    @JsonProperty
    String aiOptions;

    public MessageDTO(){}

    public MessageDTO (Message message){
        this.id = message.getId();
        this.chatID = message.getId();
        this.messageText = message.getTextMessage();
        if(message.getAiOption() != null)
            this.aiOptions = message.getAiOption().name();
    }


    public String getAiOptions() {
        return aiOptions;
    }

    public String getMessageText() {
        return messageText;
    }

    public String getChatID() {
        return chatID;
    }

    public void setChatID(String chatID) {
        this.chatID = chatID;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public void setAiOptions(String aiOptions) {
        this.aiOptions = aiOptions;
    }

    public String getId() {
        return id;
    }
}
