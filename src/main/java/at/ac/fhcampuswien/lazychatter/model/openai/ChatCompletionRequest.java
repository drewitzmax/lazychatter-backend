package at.ac.fhcampuswien.lazychatter.model.openai;

import at.ac.fhcampuswien.lazychatter.model.jpa.Chat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedList;

public class ChatCompletionRequest {
    public static String DEFAULT_MODEL = "gpt-3.5-turbo";
    @JsonProperty
    String model = DEFAULT_MODEL;
    @JsonProperty
    LinkedList<OpenAiMessage> messages;

    public ChatCompletionRequest(Chat chat, String requestingUser){
        messages = new LinkedList<>();
        for(int i=chat.getMessages().size()-1; i>= 0 && i>=(chat.getMessages().size()-4); i--){
            messages.addFirst(new OpenAiMessage(chat.getMessages().get(i)));
        }
        messages.addLast(OpenAiMessage.ApiAnswerRequest(requestingUser));
    }
}
