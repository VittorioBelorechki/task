package com.example.task.Service;
import com.example.task.Model.Student;
import com.example.task.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    public void removeStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public void modifyStudent(Student student) {
        studentRepository.save(student);
    }

    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    public List<Student> getStudentsByCourse(String courseName) {
        return studentRepository.findByCourses_Name(courseName);
    }

    public List<Student> getStudentsByGroup(String groupName) {
        return studentRepository.findByGroup(groupName);
    }

    public List<Student> getStudentsByGroupAndCourse(String groupName, String courseName) {
        return studentRepository.findByGroupAndCourses_Name(groupName, courseName);
    }

    public List<Student> getStudentsOlderThanAgeInCourse(int age, String courseName) {
        return studentRepository.findByAgeGreaterThanAndCourses_Name(age, courseName);
    }
}
