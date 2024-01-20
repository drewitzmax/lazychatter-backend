package at.ac.fhcampuswien.lazychatter.model.jpa;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "COURSESESSION")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @ManyToOne
    private User lecturer;
    private LocalDateTime date;
    boolean isOpen;
    private String currentSessionPassword;
    private LocalDateTime passwordExpirationTime;
    @ManyToMany
    @JoinTable(name = "SESSION_ATTENDEES")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private List<User> attendees;
}
