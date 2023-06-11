package at.ac.fhcampuswien.lazychatter.service;

import at.ac.fhcampuswien.lazychatter.model.dto.MessageDTO;
import at.ac.fhcampuswien.lazychatter.model.jpa.Chat;
import at.ac.fhcampuswien.lazychatter.model.jpa.Message;
import at.ac.fhcampuswien.lazychatter.model.jpa.User;
import at.ac.fhcampuswien.lazychatter.repository.ChatRepository;
import at.ac.fhcampuswien.lazychatter.repository.MessageRepository;
import at.ac.fhcampuswien.lazychatter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;

@Controller
public class MessageServiceImpl implements MessageService {
    @Autowired
    ChatService chatService;
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    UserService userService;

    @Override
    public void sendMessage(MessageDTO msg, Authentication auth) {
        Chat chat = chatService.obtainChatById(auth, msg.getChatID());
        User user = userService.getMe(auth);
        Message message = new Message(msg, user, chat);
        messageRepository.saveAndFlush(message);
    }

    @Override
    public List<Message> getMessagesByChatId(String chatId, Authentication auth) throws ResourceAccessException {
        Chat chat = this.chatService.obtainChatById(auth, chatId);
        return chat.getMessages();
    }
}
