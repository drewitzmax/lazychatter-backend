package at.ac.fhcampuswien.lazychatter.service;

import at.ac.fhcampuswien.lazychatter.model.dto.AddSessionRequest;
import at.ac.fhcampuswien.lazychatter.model.dto.CourseCreationRequest;
import at.ac.fhcampuswien.lazychatter.model.jpa.Course;
import at.ac.fhcampuswien.lazychatter.model.jpa.Session;
import at.ac.fhcampuswien.lazychatter.model.jpa.User;
import org.springframework.security.core.Authentication;

import java.util.List;


public interface CourseService {
    List<Course> getAllCourses();
    List<Course> getMyAttendingCourses(User me);
    List<Course> getMyLecturingCourses(User me);
    Course addNewCourse(CourseCreationRequest request, Authentication auth);
    Session addSessionToCourse(AddSessionRequest request, Authentication auth);
    String getSessionPassword(String id, Authentication auth) throws IllegalAccessException;
    Session attendOnSession(String password, Authentication auth) throws IllegalAccessException;
}
