package at.ac.fhcampuswien.lazychatter.service;

import at.ac.fhcampuswien.lazychatter.model.dto.MessageDTO;
import at.ac.fhcampuswien.lazychatter.model.evilinstult.InsultResponse;
import at.ac.fhcampuswien.lazychatter.model.jpa.AiMessageOption;
import at.ac.fhcampuswien.lazychatter.model.jpa.Chat;
import at.ac.fhcampuswien.lazychatter.model.openai.ChatCompletionRequest;
import at.ac.fhcampuswien.lazychatter.model.openai.ChatCompletionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class AiServiceImpl implements AiService {
    private static String OPEN_AI_API_URL = "https://api.openai.com/v1/";
    @Value("${open.ai.api.key}")
    private String openAiKey;

    private static String EVIL_INSULT_API_URL = "https://evilinsult.com/generate_insult.php";

    @Autowired
    ChatService chatService;

    @Override
    public MessageDTO enrichWithAi(MessageDTO dto, Authentication auth) {
        if (dto.getAiOptions() == null || dto.getAiOptions().isEmpty()) return dto;
        switch (AiMessageOption.valueOf(dto.getAiOptions())) {
            case GPT -> answerViaGPT(dto, auth);
            case INSULT -> answerWithInsult(dto);
        }
        return dto;
    }

    private void answerWithInsult(MessageDTO dto) {
        WebClient client = WebClient.builder()
                .baseUrl(EVIL_INSULT_API_URL)
                .build();
        WebClient.RequestHeadersSpec request = client.get().uri(uriBuilder ->
                uriBuilder.queryParam("lang", "en")
                        .queryParam("type", "json").build());
        InsultResponse response = request.retrieve().bodyToMono(InsultResponse.class).block();
        dto.setMessageText(response.getInsult());
    }

    private void answerViaGPT(MessageDTO messageDTO, Authentication auth) {
        Chat chat = chatService.obtainChatById(auth, messageDTO.getChatID());
        WebClient client = WebClient.builder()
                .baseUrl(OPEN_AI_API_URL + "chat/completions")
                .defaultHeader("Authorization", "Bearer " + openAiKey)
                .build();
        WebClient.RequestHeadersSpec request =
                client.post().bodyValue(new ChatCompletionRequest(chat, auth.getName()));
        var response = request.retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response1 -> {
                    response1.bodyToMono(String.class).log();
                    return response1.bodyToMono(String.class).map(Exception::new);
                }).bodyToMono(ChatCompletionResponse.class).block();
        messageDTO.setMessageText(response.getChoices().get(0).getMessage().getContent());
    }
}
