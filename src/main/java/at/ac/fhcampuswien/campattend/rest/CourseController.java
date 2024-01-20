package at.ac.fhcampuswien.campattend.rest;

import at.ac.fhcampuswien.campattend.model.dto.AddSessionRequest;
import at.ac.fhcampuswien.campattend.model.dto.AttendanceEntry;
import at.ac.fhcampuswien.campattend.model.dto.CourseCreationRequest;
import at.ac.fhcampuswien.campattend.model.jpa.Course;
import at.ac.fhcampuswien.campattend.model.jpa.Session;
import at.ac.fhcampuswien.campattend.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Course> getAllCourses(){
        return this.courseService.getAllCourses();
    }

    @PostMapping
    public Course addNewCourse(@RequestBody CourseCreationRequest request, Authentication auth){
        var course = this.courseService.addNewCourse(request, auth);
        return course;
    }

    @PostMapping(path = "/session")
    public Session addNewSession(@RequestBody AddSessionRequest request, Authentication auth){
        return this.courseService.addSessionToCourse(request, auth);
    }

    @GetMapping(path="/session/password/{id}")
    public String getSessionPassword(@PathVariable String id, Authentication auth) throws IllegalAccessException {
        return this.courseService.getSessionPassword(id, auth);
    }

    @PostMapping(path = "/attend/{password}")
    public Session attendOnSession(@PathVariable String password, Authentication auth) throws IllegalAccessException {
        return this.courseService.attendOnSession(password, auth);
    }

    @GetMapping(path = "/report/{courseId}")
    public List<AttendanceEntry> getAttendanceReport(@PathVariable String courseId){
        return this.courseService.getAttendanceReport(courseId);
    }
}
