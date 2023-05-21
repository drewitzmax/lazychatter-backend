package at.ac.fhcampuswien.lazychatter.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MessageDTO {
    @JsonProperty
    String chatID;
    @JsonProperty
    String messageText;
    @JsonProperty
    String aiOptions;


    public String getAiOptions() {
        return aiOptions;
    }

    public String getMessageText() {
        return messageText;
    }

    public String getChatID() {
        return chatID;
    }
}
