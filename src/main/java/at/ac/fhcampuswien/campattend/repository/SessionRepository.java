package at.ac.fhcampuswien.campattend.repository;

import at.ac.fhcampuswien.campattend.model.jpa.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, String> {
    Session findSessionByCurrentSessionPassword(String sessionPassword);
}
