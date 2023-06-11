package at.ac.fhcampuswien.lazychatter.rest;

import at.ac.fhcampuswien.lazychatter.model.dto.ChatDTO;
import at.ac.fhcampuswien.lazychatter.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("chat")
public class ChatController {
    @Autowired
    private ChatService chatService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ChatDTO startNewChat(Authentication auth, @RequestBody String[] participants) {
        try {
            return new ChatDTO(this.chatService.createNewChat(auth, participants));
        } catch (UsernameNotFoundException e) {
            throw new ErrorResponseException(HttpStatusCode.valueOf(404), e);
        }
    }

    @PostMapping(path = "/{chatId}/user", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ChatDTO addUsersToChat(Authentication auth,@PathVariable String chatId, @RequestBody String[] newParticipants){
        try{
            return new ChatDTO(this.chatService.addUsersToChat(auth, newParticipants, chatId));
        } catch (UsernameNotFoundException e) {
            throw new ErrorResponseException(HttpStatusCode.valueOf(404), e);
        }
    }
}
