package at.ac.fhcampuswien.lazychatter.model.jpa;

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
    private User owner;
    @ManyToMany(mappedBy = "lecturingCourses")
    private List<User> lecturers;
    @ManyToMany(mappedBy = "attendingCourses")
    private List<User> attendees;
}
