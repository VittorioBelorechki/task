package com.example.task.Repository;
import com.example.task.Model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;


@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    List<Teacher> findByGroupAndCourses_Name(String groupName, String courseName);

    Collection<Object> findAll();

    void save(Teacher teacher);

    void deleteById(Long id);
}