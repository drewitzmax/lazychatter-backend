package at.ac.fhcampuswien.lazychatter.rest;

import at.ac.fhcampuswien.lazychatter.model.dto.ChatDTO;
import at.ac.fhcampuswien.lazychatter.service.ChatService;
import at.ac.fhcampuswien.lazychatter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("chat")
public class ChatController {
    @Autowired
    private ChatService chatService;
    @Autowired
    private UserService userService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<ChatDTO> getMyChats(Authentication auth) {
        try {
            return userService.getMe(auth).getChatList().stream().map(chat -> new ChatDTO(chat)).toList();
        } catch (UsernameNotFoundException e) {
            throw new ErrorResponseException(HttpStatusCode.valueOf(404), e);
        }
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ChatDTO startNewChat(Authentication auth, @RequestBody String[] participants) {
        try {
            return new ChatDTO(this.chatService.createNewChat(auth, participants));
        } catch (UsernameNotFoundException e) {
            throw new ErrorResponseException(HttpStatusCode.valueOf(404), e);
        }
    }

    @PostMapping(path = "/{chatId}/user", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ChatDTO addUsersToChat(Authentication auth,@PathVariable String chatId, @RequestBody String[] newParticipants){
        try{
            return new ChatDTO(this.chatService.addUsersToChat(auth, newParticipants, chatId));
        } catch (UsernameNotFoundException e) {
            throw new ErrorResponseException(HttpStatusCode.valueOf(404), e);
        }
    }
}
