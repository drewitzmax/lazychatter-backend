package at.ac.fhcampuswien.lazychatter.service;

import at.ac.fhcampuswien.lazychatter.model.dto.MessageDTO;
import at.ac.fhcampuswien.lazychatter.model.jpa.Message;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface MessageService {
    void sendMessage(MessageDTO message, Authentication auth);

    List<Message> getMessagesByChatId(String chatId, Authentication auth) throws Exception;

    void deleteMessageById(String messageId, Authentication auth);
}
