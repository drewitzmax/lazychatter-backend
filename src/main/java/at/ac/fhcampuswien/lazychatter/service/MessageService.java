package at.ac.fhcampuswien.lazychatter.service;

import at.ac.fhcampuswien.lazychatter.model.dto.MessageDTO;
import org.springframework.security.core.Authentication;

public interface MessageService {
    void sendMassage(MessageDTO message, Authentication auth);
}
