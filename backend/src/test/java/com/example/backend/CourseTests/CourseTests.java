package com.example.backend.CourseTests;

import com.example.backend.course.model.Course;
import com.example.backend.course.service.CourseService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class CourseTests {

    @Autowired
    private CourseService courseService;

    @Test
    public void addTest() {
        Course course = new Course(0, "Ahmed", "Hello", "okijuh", "asdlfkhl", 4.5F, (short)1, "1-1-2001", null);
        System.out.println(this.courseService.saveCourse(course).toString());
    }

}
