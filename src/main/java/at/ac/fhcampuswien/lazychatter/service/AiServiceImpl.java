package at.ac.fhcampuswien.lazychatter.service;

import at.ac.fhcampuswien.lazychatter.model.dto.MessageDTO;
import at.ac.fhcampuswien.lazychatter.model.jpa.AiMessageOption;
import at.ac.fhcampuswien.lazychatter.model.jpa.Chat;
import at.ac.fhcampuswien.lazychatter.model.openai.ChatCompletionRequest;
import at.ac.fhcampuswien.lazychatter.model.openai.ChatCompletionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class AiServiceImpl implements AiService{
    private static String OPEN_AI_API_URL = "https://api.openai.com/v1/";
    @Value("${open.ai.api.key}")
    private String openAiKey;
    private static long API_CALL_TIMEOUT = 15000;
    private String errorMessage = null;

    @Autowired
    ChatService chatService;

    @Override
    public MessageDTO enrichWithAi(MessageDTO dto, Authentication auth) {
        switch(AiMessageOption.valueOf(dto.getAiOptions())){
            case GPT -> answerViaGPT(dto, auth);
        }
        return null;
    }

    private void answerViaGPT(MessageDTO messageDTO, Authentication auth){
        this.errorMessage = null;
        Chat chat = chatService.obtainChatById(auth, messageDTO.getChatID());
        WebClient client = WebClient.builder()
                .baseUrl(OPEN_AI_API_URL + "chat/completions")
                .defaultHeader("Authorization", "Bearer " + openAiKey)
                .build();
       ChatCompletionResponse repsonse =
               client.post().bodyValue(new ChatCompletionRequest(chat, auth.getName())).retrieve()
                       .bodyToMono(ChatCompletionResponse.class).block();
       messageDTO.setMessageText(repsonse.getChoices().get(0).getMessage().getContent());
    }
}
