package at.ac.fhcampuswien.lazychatter.service;

import at.ac.fhcampuswien.lazychatter.model.jpa.Chat;

public interface ChatService {
    Chat createNewChat(String ownName, String[] participants);

    Chat addUserToChat(String participant, String chatId);
}
