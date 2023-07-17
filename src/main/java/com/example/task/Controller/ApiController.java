package com.example.task.Controller;
import com.example.task.Model.CourseType;
import com.example.task.Model.Person;
import com.example.task.Model.Student;
import com.example.task.Service.CourseService;
import com.example.task.Service.ReportService;
import com.example.task.Service.StudentService;
import com.example.task.Service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {
    private final StudentService studentService;
    private final TeacherService teacherService;
    private final CourseService courseService;
    private final ReportService reportService;

    @Autowired
    public ApiController(StudentService studentService, TeacherService teacherService, CourseService courseService, ReportService reportService) {
        this.studentService = studentService;
        this.teacherService = teacherService;
        this.courseService = courseService;
        this.reportService = reportService;
    }

    // Existing CRUD endpoints omitted for brevity...

    @GetMapping("/reports/student-count")
    public int getStudentCount() {
        return reportService.getStudentCount();
    }

    @GetMapping("/reports/teacher-count")
    public int getTeacherCount() {
        return reportService.getTeacherCount();
    }

    @GetMapping("/reports/course-count/{courseType}")
    public int getCourseCountByType(@PathVariable CourseType courseType) {
        return reportService.getCourseCountByType(courseType);
    }

    @GetMapping("/reports/students-by-course/{courseName}")
    public List<Student> getStudentsByCourse(@PathVariable String courseName) {
        return reportService.getStudentsByCourse(courseName);
    }

    @GetMapping("/reports/students-by-group/{groupName}")
    public List<Student> getStudentsByGroup(@PathVariable String groupName) {
        return reportService.getStudentsByGroup(groupName);
    }

    @GetMapping("/reports/persons-by-group-and-course/{groupName}/{courseName}")
    public List<Person> getPersonsByGroupAndCourse(@PathVariable String groupName, @PathVariable String courseName) {
        return reportService.getPersonsByGroupAndCourse(groupName, courseName);
    }

    @GetMapping("/reports/students-older-than-age-in-course/{age}/{courseName}")
    public List<Student> getStudentsOlderThanAgeInCourse(@PathVariable int age, @PathVariable String courseName) {
        return reportService.getStudentsOlderThanAgeInCourse(age, courseName);
    }
}
