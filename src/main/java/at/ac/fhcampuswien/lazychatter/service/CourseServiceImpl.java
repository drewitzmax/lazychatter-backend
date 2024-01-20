package at.ac.fhcampuswien.lazychatter.service;

import at.ac.fhcampuswien.lazychatter.model.dto.AddSessionRequest;
import at.ac.fhcampuswien.lazychatter.model.dto.AttendanceEntry;
import at.ac.fhcampuswien.lazychatter.model.dto.CourseCreationRequest;
import at.ac.fhcampuswien.lazychatter.model.jpa.Course;
import at.ac.fhcampuswien.lazychatter.model.jpa.Session;
import at.ac.fhcampuswien.lazychatter.model.jpa.User;
import at.ac.fhcampuswien.lazychatter.repository.CourseRepository;
import at.ac.fhcampuswien.lazychatter.repository.SessionRepository;
import at.ac.fhcampuswien.lazychatter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    SessionRepository sessionRepository;

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public List<Course> getMyAttendingCourses(User me) {
        return List.of(new Course[0]);
    }

    @Override
    public List<Course> getMyLecturingCourses(User me) {
        return List.of(new Course[0]);
    }

    @Override
    public Course addNewCourse(CourseCreationRequest request, Authentication auth) {
        var owner = userRepository.getUserByUsername(auth.getName());
        var newCourse = new Course(request, owner);
        return courseRepository.saveAndFlush(newCourse);
    }

    @Override
    public Session addSessionToCourse(AddSessionRequest request, Authentication auth) {
        var course = courseRepository.findById(request.id()).orElseThrow(() -> new IllegalArgumentException("Course With ID could not be found"));
        var session = new Session();
        session.setDate(request.date());
        course.addSession(session);
        return sessionRepository.save(session);
    }

    @Override
    public String getSessionPassword(String id, Authentication auth) throws IllegalAccessException {
        var session = this.sessionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Session does not exist"));
        if (!(session.getCourse().getOwner().getUsername().equals(auth.getName()) || session.getLecturer().getUsername().equals(auth.getName()))) {
            throw new IllegalAccessException("User is neither owner nor lecturer");
        }
        var pass = UUID.randomUUID().toString().substring(0, 5);
        session.setCurrentSessionPassword(pass);
        session.setPasswordExpirationTime(LocalDateTime.now().plusSeconds(30));
        sessionRepository.save(session);
        return pass;
    }

    @Override
    public Session attendOnSession(String password, Authentication auth) throws IllegalAccessException {
        var session = this.sessionRepository.findSessionByCurrentSessionPassword(password);
        if (session == null) throw new IllegalAccessException("Invalid Password");
        if (session.getPasswordExpirationTime().isAfter(LocalDateTime.now())) {
            var me = userRepository.findUserByUsername(auth.getName()).orElseThrow(() -> new IllegalAccessException("Unknown user"));
            session.getAttendees().add(me);
            if (session.getCourse().getAttendees().stream().filter(e -> e.getUsername().equals(me.getUsername())).findAny().isEmpty()) {
                session.getCourse().getAttendees().add(me);
                me.getAttendingCourses().add(session.getCourse());
            }
            return sessionRepository.save(session);
        }
        throw new IllegalAccessException("Password has expired");
    }

    @Override
    public List<AttendanceEntry> getAttendanceReport(String courseId) {
        var course = this.courseRepository.findById(courseId).orElseThrow();
        var result = new ArrayList<AttendanceEntry>();
        course.getAttendees().forEach(attendee -> {
            float totalSessions = course.getSessions().size();
            float attendedSessions = course.getSessions().stream()
                    .filter(e -> e.getAttendees().stream().anyMatch(att -> att.getUsername().equals(attendee.getUsername()))).count();
            float percentage = (attendedSessions / totalSessions) * 100.0f;
            result.add(new AttendanceEntry(attendee.getUsername(), percentage));
        });

        return result;
    }
}
