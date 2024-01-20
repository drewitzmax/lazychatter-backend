package at.ac.fhcampuswien.campattend.repository;

import at.ac.fhcampuswien.campattend.model.jpa.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, String> {
}
