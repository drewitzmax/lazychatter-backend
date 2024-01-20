package at.ac.fhcampuswien.lazychatter.rest;

import at.ac.fhcampuswien.lazychatter.model.dto.AddSessionRequest;
import at.ac.fhcampuswien.lazychatter.model.dto.CourseCreationRequest;
import at.ac.fhcampuswien.lazychatter.model.jpa.Course;
import at.ac.fhcampuswien.lazychatter.model.jpa.Session;
import at.ac.fhcampuswien.lazychatter.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.enterprise.inject.Produces;
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

    @PostMapping(path = "/{id}/session")
    public Session addNewSession(@RequestBody AddSessionRequest request, Authentication auth){
        return this.courseService.addSessionToCourse(request, auth);
    }
}
