package at.ac.fhcampuswien.lazychatter.service;

import at.ac.fhcampuswien.lazychatter.model.jpa.Chat;
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
    public Chat createNewChat(String ownName, String[] participants) {
        Chat chat = new Chat();
        chat.addParticipant(userRepository.getUserByUsername(ownName));
        for(String participant : participants)
        addUserToChat(participant, chat);
        chat = chatRepository.saveAndFlush(chat);
        return chat;
    }

    @Override
    public Chat addUserToChat(String participant, Chat chat) {
        chat.addParticipant(userRepository.getUserByUsername(participant));
        return chat;
    }
}
