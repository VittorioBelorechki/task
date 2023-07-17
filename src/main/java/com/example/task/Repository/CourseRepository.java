package com.example.task.Repository;
import com.example.task.Model.Course;
import com.example.task.Model.CourseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findAllByType(CourseType courseType);
}