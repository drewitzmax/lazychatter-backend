package at.ac.fhcampuswien.lazychatter.service;

import at.ac.fhcampuswien.lazychatter.model.jpa.Chat;

public interface ChatService {
    Chat createNewChat(String name);
}
