package com.example.task;
import com.example.task.Model.Person;
import com.example.task.Model.Student;
import com.example.task.Repository.StudentRepository;
import com.example.task.Service.StudentService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StudentServiceTest {
    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRemoveStudent() {
        Long studentId = 1L;

        studentService.removeStudent(studentId);

        verify(studentRepository, times(1)).deleteById(studentId);
    }

    @Test
    public void testModifyStudent() {
        Student student = new Student();
        student.setId(1L);
        student.setName("John Doe");
        student.setAge(20);
        student.setGroup("A");

        studentService.modifyStudent(student);

        verify(studentRepository, times(1)).save(student);
    }

    @Test
    public void testFindAllStudents() {
        Student student1 = new Student();
        student1.setId(1L);
        student1.setName("John Doe");
        student1.setAge(20);
        student1.setGroup("A");

        Student student2 = new Student();
        student2.setId(2L);
        student2.setName("Jane Smith");
        student2.setAge(22);
        student2.setGroup("B");

        List<Student> students = Arrays.asList(student1, student2);

        when(studentRepository.findAll()).thenReturn(Collections.singleton(students));

        verify(studentRepository, times(1)).findAll();
    }

    @Test
    public void testGetStudentsByCourse() {
        String courseName = "Math";

        Student student1 = new Student();
        student1.setId(1L);
        student1.setName("John Doe");
        student1.setAge(20);
        student1.setGroup("A");

        Student student2 = new Student();
        student2.setId(2L);
        student2.setName("Jane Smith");
        student2.setAge(22);
        student2.setGroup("B");

        List<Student> students = Arrays.asList(student1, student2);

        when(studentRepository.findByCourses_Name(courseName)).thenReturn(students);

        List<Student> result = studentService.getStudentsByCourse(courseName);

        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).getName());
        assertEquals("Jane Smith", result.get(1).getName());

        verify(studentRepository, times(1)).findByCourses_Name(courseName);
    }

    @Test
    public void testGetStudentsByGroup() {
        String groupName = "A";

        Student student1 = new Student();
        student1.setId(1L);
        student1.setName("John Doe");
        student1.setAge(20);
        student1.setGroup("A");

        Student student2 = new Student();
        student2.setId(2L);
        student2.setName("Jane Smith");
        student2.setAge(22);
        student2.setGroup("A");

        List<Student> students = Arrays.asList(student1, student2);

        when(studentRepository.findByGroup(groupName)).thenReturn(students);

        List<Student> result = studentService.getStudentsByGroup(groupName);

        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).getName());
        assertEquals("Jane Smith", result.get(1).getName());

        verify(studentRepository, times(1)).findByGroup(groupName);
    }

    @Test
    public void testGetStudentsByGroupAndCourse() {
        String groupName = "A";
        String courseName = "Math";

        Student student1 = new Student();
        student1.setId(1L);
        student1.setName("John Doe");
        student1.setAge(20);
        student1.setGroup("A");

        Student student2 = new Student();
        student2.setId(2L);
        student2.setName("Jane Smith");
        student2.setAge(22);
        student2.setGroup("A");


        List<Person> result = studentService.getStudentsByGroupAndCourse(groupName, courseName);

        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).getName());
        assertEquals("Jane Smith", result.get(1).getName());

        verify(studentRepository, times(1)).findByGroupAndCourses_Name(groupName, courseName);
    }

    @Test
    public void testGetStudentsOlderThanAgeInCourse() {
        int age = 20;
        String courseName = "Math";

        Student student1 = new Student();
        student1.setId(1L);
        student1.setName("John Doe");
        student1.setAge(21);
        student1.setGroup("A");

        Student student2 = new Student();
        student2.setId(2L);
        student2.setName("Jane Smith");
        student2.setAge(22);
        student2.setGroup("B");

        List<Student> students = Arrays.asList(student1, student2);

        when(studentRepository.findByAgeGreaterThanAndCourses_Name(age, courseName)).thenReturn(students);

        List<Student> result = studentService.getStudentsOlderThanAgeInCourse(age, courseName);

        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).getName());
        assertEquals("Jane Smith", result.get(1).getName());

        verify(studentRepository, times(1)).findByAgeGreaterThanAndCourses_Name(age, courseName);
    }
}
