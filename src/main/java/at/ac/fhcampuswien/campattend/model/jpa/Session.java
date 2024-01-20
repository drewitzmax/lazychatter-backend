package at.ac.fhcampuswien.campattend.model.jpa;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @ManyToOne
    @JsonIgnoreProperties({"sessions"})
    @JsonIgnore
    private Course course;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;
    boolean isOpen;
    private String currentSessionPassword;
    private LocalDateTime passwordExpirationTime;
    @ManyToMany
    @JoinTable(name = "SESSION_ATTENDEES")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnoreProperties({"myCourses", "attendingCourses", "lecturingCourses"})
    @JsonIgnore
    private List<User> attendees;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getLecturer() {
        return lecturer;
    }

    public void setLecturer(User lecturer) {
        this.lecturer = lecturer;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public String getCurrentSessionPassword() {
        return currentSessionPassword;
    }

    public void setCurrentSessionPassword(String currentSessionPassword) {
        this.currentSessionPassword = currentSessionPassword;
    }

    public LocalDateTime getPasswordExpirationTime() {
        return passwordExpirationTime;
    }

    public void setPasswordExpirationTime(LocalDateTime passwordExpirationTime) {
        this.passwordExpirationTime = passwordExpirationTime;
    }

    public List<User> getAttendees() {
        return attendees;
    }

    public void setAttendees(List<User> attendees) {
        this.attendees = attendees;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
