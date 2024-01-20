package at.ac.fhcampuswien.lazychatter.repository;

import at.ac.fhcampuswien.lazychatter.model.jpa.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, String> {
}
