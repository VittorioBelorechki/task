package com.example.task;

import com.example.task.Model.Teacher;
import com.example.task.Repository.TeacherRepository;
import com.example.task.Service.TeacherService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TeacherServiceTest {
    @Mock
    private TeacherRepository teacherRepository;

    @InjectMocks
    private TeacherService teacherService;

    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRemoveTeacher() {
        Long teacherId = 1L;

        teacherService.removeTeacher(teacherId);

        verify(teacherRepository, times(1)).deleteById(teacherId);
    }

    @Test
    public void testModifyTeacher() {
        Teacher teacher = new Teacher();
        teacher.setId(1L);
        teacher.setName("Jane Smith");
        teacher.setAge(30);

        teacherService.modifyTeacher(teacher);

        verify(teacherRepository, times(1)).save(teacher);
    }

    @Test
    public void testGetTeachersByGroupAndCourse() {
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

        when(teacherRepository.findByGroupAndCourses_Name(groupName, courseName)).thenReturn(teachers);

        List<Teacher> result = teacherService.getTeachersByGroupAndCourse(groupName, courseName);

        assertEquals(2, result.size());
        assertEquals("Jane Smith", result.get(0).getName());
        assertEquals("John Doe", result.get(1).getName());

        verify(teacherRepository, times(1)).findByGroupAndCourses_Name(groupName, courseName);
    }
}
