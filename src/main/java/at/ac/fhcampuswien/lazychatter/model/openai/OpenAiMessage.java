package at.ac.fhcampuswien.lazychatter.model.openai;

import at.ac.fhcampuswien.lazychatter.model.jpa.Message;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OpenAiMessage {
    @JsonProperty
    String content;
    @JsonProperty
    OpenAiRole role;

    OpenAiMessage(){}
    OpenAiMessage(Message message){
        this.content = message.getSender().getUsername() + ": " + message.getTextMessage();
        this.role = OpenAiRole.user;
    }

    public static OpenAiMessage ApiAnswerRequest(String requestingUser) {
        OpenAiMessage message = new OpenAiMessage();
        message.role = OpenAiRole.system;
        message.content = "Answer as if you were " + requestingUser;
        return message;
    }

    public String getContent() {
        return content;
    }

    public OpenAiRole getRole() {
        return role;
    }
}
