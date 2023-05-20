package at.ac.fhcampuswien.lazychatter.repository;

import at.ac.fhcampuswien.lazychatter.model.jpa.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, String> {
}
