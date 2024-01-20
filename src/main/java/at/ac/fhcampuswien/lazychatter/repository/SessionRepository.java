package at.ac.fhcampuswien.lazychatter.repository;

import at.ac.fhcampuswien.lazychatter.model.jpa.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, String> {
}
