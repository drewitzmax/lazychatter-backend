package at.ac.fhcampuswien.lazychatter.repository;

import at.ac.fhcampuswien.lazychatter.model.jpa.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, String> {
}
