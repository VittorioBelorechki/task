package com.example.task.Repository;

import com.example.task.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByCourses_Name(String courseName);

    List<Student> findByGroup(String groupName);

    List<Student> findByGroupAndCourses_Name(String groupName, String courseName);

    List<Student> findByAgeGreaterThanAndCourses_Name(int age, String courseName);
}