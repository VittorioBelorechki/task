package com.example.task;

import com.example.task.Controller.ApiController;
import com.example.task.Model.Student;
import com.example.task.Model.Teacher;
import com.example.task.Service.CourseService;
import com.example.task.Service.ReportService;
import com.example.task.Service.StudentService;
import com.example.task.Service.TeacherService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ApiController.class)
public class ApiControllerTest {
    @Mock
    private StudentService studentService;

    @Mock
    private TeacherService teacherService;

    @Mock
    private CourseService courseService;

    @Mock
    private ReportService reportService;

    @InjectMocks
    private ApiController apiController;

    private MockMvc mockMvc;

    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(apiController).build();
    }

    @Test
    public void testGetStudentCount() throws Exception {
        when(reportService.getStudentCount()).thenReturn(5);

        mockMvc.perform(get("/api/reports/student-count"))
                .andExpect(status().isOk())
                .andExpect(content().string("5"));

        verify(reportService, times(1)).getStudentCount();
    }

    @Test
    public void testGetTeacherCount() throws Exception {
        when(reportService.getTeacherCount()).thenReturn(3);

        mockMvc.perform(get("/api/reports/teacher-count"))
                .andExpect(status().isOk())
                .andExpect(content().string("3"));

        verify(reportService, times(1)).getTeacherCount();
    }

    @Test
    public void testGetStudentsByCourse() throws Exception {
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

        when(studentService.getStudentsByCourse(courseName)).thenReturn(students);

        mockMvc.perform(get("/api/students").param("course", courseName))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("John Doe"))
                .andExpect(jsonPath("$[0].age").value(20))
                .andExpect(jsonPath("$[0].group").value("A"))
                .andExpect(jsonPath("$[1].name").value("Jane Smith"))
                .andExpect(jsonPath("$[1].age").value(22))
                .andExpect(jsonPath("$[1].group").value("B"));

        verify(studentService, times(1)).getStudentsByCourse(courseName);
    }

    @Test
    public void testGetStudentsByGroup() throws Exception {
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

        when(studentService.getStudentsByGroup(groupName)).thenReturn(students);

        mockMvc.perform(get("/api/students").param("group", groupName))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("John Doe"))
                .andExpect(jsonPath("$[0].age").value(20))
                .andExpect(jsonPath("$[0].group").value("A"))
                .andExpect(jsonPath("$[1].name").value("Jane Smith"))
                .andExpect(jsonPath("$[1].age").value(22))
                .andExpect(jsonPath("$[1].group").value("A"));

        verify(studentService, times(1)).getStudentsByGroup(groupName);
    }

    @Test
    public void testGetStudentsByGroupAndCourse() throws Exception {
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

        mockMvc.perform(get("/api/students")
                        .param("group", groupName)
                        .param("course", courseName))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("John Doe"))
                .andExpect(jsonPath("$[0].age").value(20))
                .andExpect(jsonPath("$[0].group").value("A"))
                .andExpect(jsonPath("$[1].name").value("Jane Smith"))
                .andExpect(jsonPath("$[1].age").value(22))
                .andExpect(jsonPath("$[1].group").value("A"));

        verify(studentService, times(1)).getStudentsByGroupAndCourse(groupName, courseName);
    }

    @Test
    public void testGetTeachersAndStudentsByGroupAndCourse() throws Exception {
        String groupName = "A";
        String courseName = "Math";

        Teacher teacher1 = new Teacher();
        teacher1.setId(1L);
        teacher1.setName("Jane Smith");
        teacher1.setAge(30);

        Teacher teacher2 = new Teacher();
        teacher2.setId(2L);
        teacher2.setName("John Doe");
        teacher2.setAge(35);

        List<Teacher> teachers = Arrays.asList(teacher1, teacher2);

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
        
        when(teacherService.getTeachersByGroupAndCourse(groupName, courseName)).thenReturn(teachers);

        mockMvc.perform(get("/api/teachers-students")
                        .param("group", groupName)
                        .param("course", courseName))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.teachers[0].name").value("Jane Smith"))
                .andExpect(jsonPath("$.teachers[0].age").value(30))
                .andExpect(jsonPath("$.teachers[1].name").value("John Doe"))
                .andExpect(jsonPath("$.teachers[1].age").value(35))
                .andExpect(jsonPath("$.students[0].name").value("John Doe"))
                .andExpect(jsonPath("$.students[0].age").value(20))
                .andExpect(jsonPath("$.students[0].group").value("A"))
                .andExpect(jsonPath("$.students[1].name").value("Jane Smith"))
                .andExpect(jsonPath("$.students[1].age").value(22))
                .andExpect(jsonPath("$.students[1].group").value("A"));

        verify(teacherService, times(1)).getTeachersByGroupAndCourse(groupName, courseName);
        verify(studentService, times(1)).getStudentsByGroupAndCourse(groupName, courseName);
    }

    @Test
    public void testGetStudentsOlderThanAgeInCourse() throws Exception {
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

        when(studentService.getStudentsOlderThanAgeInCourse(age, courseName)).thenReturn(students);

        mockMvc.perform(get("/api/students/older-than")
                        .param("age", String.valueOf(age))
                        .param("course", courseName))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("John Doe"))
                .andExpect(jsonPath("$[0].age").value(21))
                .andExpect(jsonPath("$[0].group").value("A"))
                .andExpect(jsonPath("$[1].name").value("Jane Smith"))
                .andExpect(jsonPath("$[1].age").value(22))
                .andExpect(jsonPath("$[1].group").value("B"));

        verify(studentService, times(1)).getStudentsOlderThanAgeInCourse(age, courseName);
    }
}
