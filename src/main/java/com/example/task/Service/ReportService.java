package com.example.task.Service;
import com.example.task.Model.CourseType;
import com.example.task.Model.Person;
import com.example.task.Model.Student;
import com.example.task.Model.Teacher;
import com.example.task.Repository.CourseRepository;
import com.example.task.Repository.StudentRepository;
import com.example.task.Repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReportService {
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public ReportService(StudentRepository studentRepository, TeacherRepository teacherRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
    }

    public int getStudentCount() {
        return studentRepository.findAll().size();
    }

    public int getTeacherCount() {
        return teacherRepository.findAll().size();
    }

    public int getCourseCountByType(CourseType courseType) {
        return courseRepository.findAllByType(courseType).size();
    }

    public List<Student> getStudentsByCourse(String courseName) {
        return studentRepository.findByCourses_Name(courseName);
    }

    public List<Student> getStudentsByGroup(String groupName) {
        return studentRepository.findByGroup(groupName);
    }

    public List<Person> getPersonsByGroupAndCourse(String groupName, String courseName) {
        List<Person> students = studentRepository.findByGroupAndCourses_Name(groupName, courseName);
        List<Teacher> teachers = teacherRepository.findByGroupAndCourses_Name(groupName, courseName);
        students.addAll(teachers);
        return students;
    }

    public List<Student> getStudentsOlderThanAgeInCourse(int age, String courseName) {
        return studentRepository.findByAgeGreaterThanAndCourses_Name(age, courseName);
    }
}