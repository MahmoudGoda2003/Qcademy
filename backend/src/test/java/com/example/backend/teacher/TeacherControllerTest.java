package com.example.backend.teacher;

import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;

import com.example.backend.course.dto.CourseMainInfoDTO;
import com.example.backend.course.dto.CourseModuleDTO;
import com.example.backend.course.service.CourseService;
import com.example.backend.teacher.service.TeacherService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {TeacherController.class})
@ExtendWith(SpringExtension.class)
class TeacherControllerTest {
    @MockBean
    private CourseService courseService;

    @Autowired
    private TeacherController teacherController;

    @MockBean
    private TeacherService teacherService;

    @Test
    void testCreateCourse() throws Exception {
        when(teacherService.createCourse(Mockito.<CourseMainInfoDTO>any())).thenReturn(null);

        CourseMainInfoDTO courseMainInfoDTO = new CourseMainInfoDTO();
        courseMainInfoDTO.setCourseId(1);
        courseMainInfoDTO.setDescription("The characteristics of someone or something");
        courseMainInfoDTO.setEstimatedTime((short) 1);
        courseMainInfoDTO.setName("Name");
        courseMainInfoDTO.setRating(10.0f);
        courseMainInfoDTO.setStartDate("2020-03-01");
        courseMainInfoDTO.setTags(new ArrayList<>());
        courseMainInfoDTO.setTeacherName("Teacher Name");
        String content = (new ObjectMapper()).writeValueAsString(courseMainInfoDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/teacher/createCourse")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        MockMvcBuilders.standaloneSetup(teacherController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testCreateModule() throws Exception {
        when(teacherService.createModule(Mockito.<CourseModuleDTO>any())).thenReturn(null);

        CourseModuleDTO courseModuleDTO = new CourseModuleDTO();
        courseModuleDTO.setAssignments(new ArrayList<>());
        courseModuleDTO.setCourseId(1);
        courseModuleDTO.setLectures(new ArrayList<>());
        courseModuleDTO.setName("Name");
        courseModuleDTO.setQuizzes(new ArrayList<>());
        courseModuleDTO.setSlidesSets(new ArrayList<>());
        courseModuleDTO.setWeekNumber(10);
        String content = (new ObjectMapper()).writeValueAsString(courseModuleDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/teacher/createModule")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        MockMvcBuilders.standaloneSetup(teacherController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testRequestPromotion() throws Exception {

        when(teacherService.requestPromotion()).thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/teacher/requestPromotion");

        MockMvcBuilders.standaloneSetup(teacherController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    void testGetCreatedCourses() throws Exception {

        when(teacherService.getCreatedCourses()).thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/teacher/createdCourses");

        MockMvcBuilders.standaloneSetup(teacherController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testGetCreatedCourses2() throws Exception {
        when(teacherService.getCreatedCourses()).thenReturn(null);
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders.formLogin();

        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(teacherController)
                .build()
                .perform(requestBuilder);

        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testGetCourseModules() throws Exception {
        when(courseService.getCourseModules(anyInt())).thenReturn(null);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/teacher/courseModules");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("courseId", String.valueOf(1));

        MockMvcBuilders.standaloneSetup(teacherController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testRemoveCourse() throws Exception {
        when(courseService.removeCourse(anyInt())).thenReturn(null);
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/teacher/removeCourse");
        MockHttpServletRequestBuilder requestBuilder = deleteResult.param("courseId", String.valueOf(1));

        MockMvcBuilders.standaloneSetup(teacherController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testRemoveModule() throws Exception {
        when(courseService.removeModule(anyInt(), anyInt())).thenReturn(null);
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/teacher/removeModule");
        MockHttpServletRequestBuilder paramResult = deleteResult.param("courseId", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("weekNumber", String.valueOf(1));

        MockMvcBuilders.standaloneSetup(teacherController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
