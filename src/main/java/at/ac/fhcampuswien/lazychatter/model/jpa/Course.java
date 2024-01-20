package at.ac.fhcampuswien.lazychatter.model.jpa;

import at.ac.fhcampuswien.lazychatter.model.dto.CourseCreationRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "COURSE")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    @ManyToOne
    @JsonIgnoreProperties("myCourses")
    private User owner;
    @ManyToMany(mappedBy = "lecturingCourses")
    @JsonIgnoreProperties(value = {"lecturingCourses"})
    private List<User> lecturers;
    @ManyToMany(mappedBy = "attendingCourses")
    private List<User> attendees;
    @OneToMany
    @JsonIgnoreProperties("course")
    private List<Session> sessions;

    public Course(){
    }

    public Course(CourseCreationRequest request, User owner){
        this.owner = owner;
        this.name = request.name();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<User> getLecturers() {
        return lecturers;
    }

    public void setLecturers(List<User> lecturers) {
        this.lecturers = lecturers;
    }

    public List<User> getAttendees() {
        return attendees;
    }

    public void setAttendees(List<User> attendees) {
        this.attendees = attendees;
    }

    public void addSession(Session session){
        this.sessions.add(session);
        session.setCourse(this);
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }
}
