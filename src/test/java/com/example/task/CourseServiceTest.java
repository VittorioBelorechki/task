package com.example.task;

import com.example.task.Model.Course;
import com.example.task.Model.CourseType;
import com.example.task.Repository.CourseRepository;
import com.example.task.Service.CourseService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CourseServiceTest {
    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private CourseService courseService;

    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRemoveCourse() {
        Long courseId = 1L;

        courseService.removeCourse(courseId);

        verify(courseRepository, times(1)).deleteById(courseId);
    }

    @Test
    public void testModifyCourse() {
        Course course = new Course();
        course.setId(1L);
        course.setName("Math");
        course.setType(CourseType.MAIN);

        courseService.modifyCourse(course);

        verify(courseRepository, times(1)).save(course);
    }

    @Test
    public void testFindAllCourses() {
        Course course1 = new Course();
        course1.setId(1L);
        course1.setName("Math");
        course1.setType(CourseType.MAIN);

        Course course2 = new Course();
        course2.setId(2L);
        course2.setName("English");
        course2.setType(CourseType.SECONDARY);

        List<Course> courses = Arrays.asList(course1, course2);

        when(courseRepository.findAll()).thenReturn(courses);

        List<Course> result = courseService.findAllCourses();

        assertEquals(2, result.size());
        assertEquals("Math", result.get(0).getName());
        assertEquals("English", result.get(1).getName());

        verify(courseRepository, times(1)).findAll();
    }

    @Test
    public void testGetCoursesByType() {
        Course course1 = new Course();
        course1.setId(1L);
        course1.setName("Math");
        course1.setType(CourseType.MAIN);

        Course course2 = new Course();
        course2.setId(2L);
        course2.setName("English");
        course2.setType(CourseType.MAIN);

        List<Course> courses = Arrays.asList(course1, course2);

        when(courseRepository.findAllByType(CourseType.MAIN)).thenReturn(courses);

        List<Course> result = courseService.getCoursesByType(CourseType.MAIN);

        assertEquals(2, result.size());
        assertEquals("Math", result.get(0).getName());
        assertEquals("English", result.get(1).getName());

        verify(courseRepository, times(1)).findAllByType(CourseType.MAIN);
    }
}
