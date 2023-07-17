package com.example.task.Service;
import com.example.task.Model.Course;
import com.example.task.Model.CourseType;
import com.example.task.Repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public void addCourse(Course course) {
        courseRepository.save(course);
    }

    public void removeCourse(Long id) {
        courseRepository.deleteById(id);
    }

    public void modifyCourse(Course course) {
        courseRepository.save(course);
    }

    public List<Course> findAllCourses() {
        return courseRepository.findAll();
    }

    public List<Course> getCoursesByType(CourseType courseType) {
        return courseRepository.findAllByType(courseType);
    }
}
