package at.ac.fhcampuswien.lazychatter.service;

import at.ac.fhcampuswien.lazychatter.model.jpa.Chat;
import at.ac.fhcampuswien.lazychatter.model.jpa.User;
import at.ac.fhcampuswien.lazychatter.repository.ChatRepository;
import at.ac.fhcampuswien.lazychatter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ChatServiceImpl implements ChatService{
    @Autowired
    ChatRepository chatRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public Chat createNewChat(String name) {
        Chat chat = new Chat();
        User owner = userRepository.getUserByUsername(name);
        chat.addParticipant(owner);
        chat = chatRepository.saveAndFlush(chat);
        return chat;
    }
}
