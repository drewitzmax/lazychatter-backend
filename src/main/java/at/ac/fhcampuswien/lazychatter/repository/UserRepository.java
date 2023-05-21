package at.ac.fhcampuswien.lazychatter.repository;


import at.ac.fhcampuswien.lazychatter.model.jpa.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User getUserById(String id);
    User getUserByUsername(String username);
}
