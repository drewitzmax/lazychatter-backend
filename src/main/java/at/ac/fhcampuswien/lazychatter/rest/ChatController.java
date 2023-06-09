package at.ac.fhcampuswien.lazychatter.rest;

import at.ac.fhcampuswien.lazychatter.model.dto.ChatDTO;
import at.ac.fhcampuswien.lazychatter.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("chat")
public class ChatController {
    @Autowired
    private ChatService chatService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ChatDTO startNewChat(Authentication auth){
        return new ChatDTO(this.chatService.createNewChat(auth.getName()));
    }
}
