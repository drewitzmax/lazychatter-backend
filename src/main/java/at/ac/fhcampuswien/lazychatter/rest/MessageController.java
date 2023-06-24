package at.ac.fhcampuswien.lazychatter.rest;

import at.ac.fhcampuswien.lazychatter.model.dto.MessageDTO;
import at.ac.fhcampuswien.lazychatter.service.AiService;
import at.ac.fhcampuswien.lazychatter.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("message")
public class MessageController {
    @Autowired
    MessageService messageService;
    @Autowired
    AiService aiService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void sendMessage(@RequestBody MessageDTO message, Authentication auth) {
        aiService.enrichWithAi(message, auth);
        messageService.sendMessage(message, auth);
    }

    @GetMapping(path = "/chat/{chatId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<MessageDTO> getMessagesByChatId(@PathVariable String chatId, Authentication auth) throws Exception {
        List<MessageDTO> messages = messageService.getMessagesByChatId(chatId, auth).stream().map(message -> new MessageDTO(message)).toList();
        return messages;
    }

    @PatchMapping
    public void updateMessage(@RequestBody MessageDTO message, Authentication auth){
        messageService.updateMessage(message, auth);
    }

    @DeleteMapping(path="/{messageId}")
    public void deleteMessageById(@PathVariable String messageId, Authentication auth){
        messageService.deleteMessageById(messageId, auth);
    }
}
