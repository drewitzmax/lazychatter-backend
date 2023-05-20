package at.ac.fhcampuswien.lazychatter.repository;


import at.ac.fhcampuswien.lazychatter.model.jpa.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    public User getUserById(String id);
    public User getUserByUsername(String username);
}
