package at.ac.fhcampuswien.campattend.repository;


import at.ac.fhcampuswien.campattend.model.jpa.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    User getUserById(String id);
    User getUserByUsername(String username);
    Optional<User> findUserByUsername(String username);
    int deleteByUsername(String username);
}
