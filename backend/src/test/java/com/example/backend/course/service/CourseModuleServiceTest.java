package com.example.backend.course.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.backend.course.dto.CourseModuleDTO;
import com.example.backend.course.model.Assignment;
import com.example.backend.course.model.CourseModule;
import com.example.backend.course.repository.CourseModuleRepository;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CourseModuleService.class})
@ExtendWith(SpringExtension.class)
class CourseModuleServiceTest {
    @MockBean
    private CourseModuleRepository courseModuleRepository;

    @Autowired
    private CourseModuleService courseModuleService;

    @Test
    void testCreateCourseModule() {
        CourseModule courseModule = new CourseModule();
        courseModule.setAssignments(new ArrayList<>());
        courseModule.setLectures(new ArrayList<>());
        courseModule.setName("Name");
        courseModule.setQuizzes(new ArrayList<>());
        courseModule.setSlidesSets(new ArrayList<>());
        when(courseModuleRepository.save(Mockito.<CourseModule>any())).thenReturn(courseModule);

        CourseModuleDTO courseModuleDTO = new CourseModuleDTO();
        courseModuleDTO.setAssignments(new ArrayList<>());
        courseModuleDTO.setCourseId(1);
        courseModuleDTO.setLectures(new ArrayList<>());
        courseModuleDTO.setName("Name");
        courseModuleDTO.setQuizzes(new ArrayList<>());
        courseModuleDTO.setSlidesSets(new ArrayList<>());
        courseModuleDTO.setWeekNumber(10);

        CourseModule actualCreateCourseModuleResult = courseModuleService.createCourseModule(courseModuleDTO);

        verify(courseModuleRepository).save(Mockito.<CourseModule>any());
        assertSame(courseModule, actualCreateCourseModuleResult);
    }

    @Test
    void testRemoveModule() {

        doNothing().when(courseModuleRepository).delete(Mockito.<CourseModule>any());

        CourseModule courseModule = new CourseModule();
        courseModule.setAssignments(new ArrayList<>());
        courseModule.setLectures(new ArrayList<>());
        courseModule.setName("Name");
        courseModule.setQuizzes(new ArrayList<>());
        courseModule.setSlidesSets(new ArrayList<>());

        courseModuleService.removeModule(courseModule);

        verify(courseModuleRepository).delete(Mockito.<CourseModule>any());
        assertEquals("Name", courseModule.getName());
        assertEquals(0, courseModule.getWeekNumber());
        List<Assignment> assignments = courseModule.getAssignments();
        assertTrue(assignments.isEmpty());
        assertEquals(assignments, courseModule.getLectures());
        assertEquals(assignments, courseModule.getQuizzes());
        assertEquals(assignments, courseModule.getSlidesSets());
    }
}
