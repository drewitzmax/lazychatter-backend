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

import java.util.List;

@Controller
public class MessageServiceImpl implements MessageService {
    @Autowired
    ChatRepository chatRepository;
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public void sendMessage(MessageDTO msg, Authentication auth) {
        Chat chat = chatRepository.getReferenceById(msg.getChatID());
        User user = userRepository.getUserByUsername(auth.getName());
        Message message = new Message(msg, user, chat);
        messageRepository.saveAndFlush(message);
    }

    @Override
    public List<MessageDTO> getMessagesByChatId(String chatId, Authentication auth) throws Exception {
        Chat chat = this.chatRepository.getReferenceById(chatId);
        boolean isParticipant = false;
        for(User user: chat.getParticipants()){
            if(user.getUsername().equals(auth.getName())){
                isParticipant = true;
                break;
            };
        }
        if(!isParticipant) throw new Exception("Not authorized for this Resource");
        List<Message> messages = messageRepository.getMessagesByChatId(chatId);
        return null;
    }
}
