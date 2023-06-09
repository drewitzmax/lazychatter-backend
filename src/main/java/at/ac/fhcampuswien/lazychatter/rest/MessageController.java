package at.ac.fhcampuswien.lazychatter.rest;

import at.ac.fhcampuswien.lazychatter.model.dto.MessageDTO;
import at.ac.fhcampuswien.lazychatter.model.jpa.Message;
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

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void sendMessage(@RequestBody MessageDTO message, Authentication auth) {
        messageService.sendMessage(message, auth);
        return;
    }

    @GetMapping(path = "chat/{chatId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MessageDTO> getMessagesByChatId(@PathVariable String chatId, Authentication auth) throws Exception {
        List<MessageDTO> messages = messageService.getMessagesByChatId(chatId, auth);
        return messages;
    }
}
