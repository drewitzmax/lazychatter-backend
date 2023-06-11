package at.ac.fhcampuswien.lazychatter.service;

import at.ac.fhcampuswien.lazychatter.model.jpa.Chat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.client.ResourceAccessException;

public interface ChatService {
    Chat createNewChat(Authentication ownName, String[] participants) throws UsernameNotFoundException;

    Chat addUsersToChat(Authentication auth, String[] participants, String chatId) throws UsernameNotFoundException;
    Chat obtainChatById(Authentication auth, String chatId) throws ResourceAccessException;
}
