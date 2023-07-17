package com.example.task.Service;
import com.example.task.Model.Teacher;
import com.example.task.Repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public void addTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    public void removeTeacher(Long id) {
        teacherRepository.deleteById(id);
    }

    public void modifyTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    public List<Teacher> findAllTeachers() {
        return teacherRepository.findAll();
    }

    public List<Teacher> getTeachersByGroupAndCourse(String groupName, String courseName) {
        return teacherRepository.findByGroupAndCourses_Name(groupName, courseName);
    }
}
