package at.ac.fhcampuswien.lazychatter.service;

import at.ac.fhcampuswien.lazychatter.model.jpa.Chat;
import at.ac.fhcampuswien.lazychatter.model.jpa.User;
import at.ac.fhcampuswien.lazychatter.repository.ChatRepository;
import at.ac.fhcampuswien.lazychatter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.ResourceAccessException;

import java.util.Optional;

@Controller
public class ChatServiceImpl implements ChatService{
    @Autowired
    ChatRepository chatRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public Chat createNewChat(Authentication auth, String[] participants) throws UsernameNotFoundException{
        Chat chat = new Chat();
        chat.addParticipant(userRepository.getUserByUsername(auth.getName()));
        for(String participant : participants)
        addUserToChat(participant, chat);
        chat = chatRepository.saveAndFlush(chat);
        return chat;
    }

    public Chat addUsersToChat(Authentication auth, String[] participants, String chatId) throws UsernameNotFoundException{
        Optional<Chat> chatOptional = chatRepository.findById(chatId);
        if(chatOptional.isEmpty()){
            throw new ResourceAccessException("Resource could not be found");
        }
        Chat chat = chatOptional.get();
        if(!isParticipant(auth, chat)){
           throw new AccessDeniedException("Accessing user is not authorized on this resource");
        }
        for(String participant: participants){
            addUserToChat(participant, chat);
        }
        chatRepository.saveAndFlush(chat);
        return chat;
    }

    private Chat addUserToChat(String participant, Chat chat) throws UsernameNotFoundException{
        Optional<User> userOptional = userRepository.findById(participant);
        if(userOptional.isEmpty()){
            throw new UsernameNotFoundException("User does not exist and can not be added");
        }
        chat.addParticipant(userOptional.get());
        return chat;
    }

    private boolean isParticipant(Authentication auth, Chat chat){
        for(User participant: chat.getParticipants()){
            if(participant.getUsername().equals(auth.getName())){
                return true;
            }
        }
        return false;
    }
}
