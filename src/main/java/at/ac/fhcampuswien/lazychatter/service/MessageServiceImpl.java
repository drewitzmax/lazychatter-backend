package at.ac.fhcampuswien.lazychatter.service;

import at.ac.fhcampuswien.lazychatter.model.dto.MessageDTO;
import at.ac.fhcampuswien.lazychatter.model.jpa.Chat;
import at.ac.fhcampuswien.lazychatter.model.jpa.Message;
import at.ac.fhcampuswien.lazychatter.model.jpa.User;
import at.ac.fhcampuswien.lazychatter.repository.MessageRepository;
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

    @Override
    public void deleteMessageById(String messageId, Authentication auth) {
        Message message = messageRepository.getReferenceById(messageId);
        if(message.getSender().equals(auth.getName())){
            messageRepository.delete(message);
        } else {
            throw new RuntimeException("You're not authorized to manipulate this object");
        }
    }

    @Override
    public void updateMessage(MessageDTO message, Authentication auth) {
        Message localMessage = messageRepository.getReferenceById(message.getId());
        if(localMessage.getSender().equals(auth.getName())){
            throw new RuntimeException("User not authorized to modify this recource");
        }
        localMessage.setTextMessage(message.getMessageText());

        messageRepository.save(localMessage);
    }
}
