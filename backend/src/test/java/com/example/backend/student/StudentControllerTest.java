package com.example.backend.student;

import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;

import com.example.backend.course.service.CourseService;
import com.example.backend.student.service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {StudentController.class})
@ExtendWith(SpringExtension.class)
class StudentControllerTest {
    @MockBean
    private CourseService courseService;

    @Autowired
    private StudentController studentController;

    @MockBean
    private StudentService studentService;

    @Test
    void testRequestPromotion() throws Exception {

        when(studentService.requestPromotion()).thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/student/requestPromotion");

        MockMvcBuilders.standaloneSetup(studentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testGetEnrolledCourses() throws Exception {

        when(studentService.getEnrolledCourses()).thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/student/enrolledCourses");

        MockMvcBuilders.standaloneSetup(studentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testGetEnrolledCourses2() throws Exception {

        when(studentService.getEnrolledCourses()).thenReturn(null);
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders.formLogin();

        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(studentController)
                .build()
                .perform(requestBuilder);

        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testGetRecommendedCourses() throws Exception {

        when(studentService.getRecommendedCourses()).thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/student/recommendedCourses");

        MockMvcBuilders.standaloneSetup(studentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testEnrollCourse() throws Exception {

        when(studentService.enrollCourse(anyInt())).thenReturn(null);
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/student/enrollCourse");
        MockHttpServletRequestBuilder requestBuilder = postResult.param("courseId", String.valueOf(1));

        MockMvcBuilders.standaloneSetup(studentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testGetCourseModules() throws Exception {

        when(courseService.getCourseModules(anyInt())).thenReturn(null);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/student/courseModules");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("courseId", String.valueOf(1));

        MockMvcBuilders.standaloneSetup(studentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testRateCourse() throws Exception {
        when(courseService.changeRate(anyInt(), anyInt())).thenReturn(null);
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/student/rateCourse");
        MockHttpServletRequestBuilder paramResult = postResult.param("courseId", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("rating", String.valueOf(1));

        MockMvcBuilders.standaloneSetup(studentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
