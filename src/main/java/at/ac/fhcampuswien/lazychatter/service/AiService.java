package at.ac.fhcampuswien.lazychatter.service;

import at.ac.fhcampuswien.lazychatter.model.dto.MessageDTO;
import at.ac.fhcampuswien.lazychatter.model.jpa.Chat;
import org.springframework.security.core.Authentication;

public interface AiService {
    MessageDTO enrichWithAi(MessageDTO dto, Authentication auth);
}
