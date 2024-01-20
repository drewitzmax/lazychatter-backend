package at.ac.fhcampuswien.lazychatter.service;

import at.ac.fhcampuswien.lazychatter.model.dto.AddSessionRequest;
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

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService{
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
}
