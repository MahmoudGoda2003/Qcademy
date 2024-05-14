package com.example.backend.course.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.anyShort;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.backend.course.dto.AssignmentDTO;
import com.example.backend.course.dto.CourseMainInfoDTO;
import com.example.backend.course.dto.CourseModuleDTO;
import com.example.backend.course.dto.LectureDTO;
import com.example.backend.course.model.Assignment;
import com.example.backend.course.model.Course;
import com.example.backend.course.model.CourseModule;
import com.example.backend.course.repository.CourseRepository;
import com.example.backend.student.model.Student;
import com.example.backend.teacher.model.Teacher;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CourseService.class})
@ExtendWith(SpringExtension.class)
class CourseServiceTest {
    @MockBean
    private CourseModuleService courseModuleService;

    @MockBean
    private CourseRepository courseRepository;

    @Autowired
    private CourseService courseService;

    @Test
    void testCreateCourse() {
        Teacher teacher = new Teacher();
        teacher.setCourses(new ArrayList<>());
        teacher.setUserId(1L);

        Course course = new Course();
        course.setDescription("The characteristics of someone or something");
        course.setEstimatedTime((short) 1);
        ArrayList<CourseModule> modules = new ArrayList<>();
        course.setModules(modules);
        course.setName("Name");
        course.setNumberOfRates(10);
        course.setStartDate("2020-03-01");
        course.setStudents(new ArrayList<>());
        course.setTags(new ArrayList<>());
        course.setTeacher(teacher);
        course.setTeacherName("Teacher Name");
        course.setTotalRate(1);
        when(courseRepository.save(Mockito.<Course>any())).thenReturn(course);

        CourseMainInfoDTO courseMainInfoDTO = new CourseMainInfoDTO();
        courseMainInfoDTO.setCourseId(1);
        courseMainInfoDTO.setDescription("The characteristics of someone or something");
        courseMainInfoDTO.setEstimatedTime((short) 1);
        courseMainInfoDTO.setName("Name");
        courseMainInfoDTO.setRating(10.0f);
        courseMainInfoDTO.setStartDate("2020-03-01");
        courseMainInfoDTO.setTags(new ArrayList<>());
        courseMainInfoDTO.setTeacherName("Teacher Name");

        Teacher teacher2 = new Teacher();
        teacher2.setCourses(new ArrayList<>());
        teacher2.setUserId(1L);

        Course actualCreateCourseResult = courseService.createCourse(courseMainInfoDTO, teacher2);

        verify(courseRepository).save(Mockito.<Course>any());
        assertEquals("2020-03-01", actualCreateCourseResult.getStartDate());
        assertEquals("Name", actualCreateCourseResult.getName());
        assertEquals("Teacher Name", actualCreateCourseResult.getTeacherName());
        assertEquals("The characteristics of someone or something", actualCreateCourseResult.getDescription());
        assertNull(actualCreateCourseResult.getPhotoLink());
        assertEquals(0, actualCreateCourseResult.getNumberOfRates());
        assertEquals(0, actualCreateCourseResult.getTotalRate());
        assertEquals((short) 1, actualCreateCourseResult.getEstimatedTime());
        assertEquals(teacher, actualCreateCourseResult.getTeacher());
        assertEquals(modules, actualCreateCourseResult.getTags());
    }

    @Test
    void testEnrollInCourse() {

        Teacher teacher = new Teacher();
        teacher.setCourses(new ArrayList<>());
        teacher.setUserId(1L);

        Course course = new Course();
        course.setDescription("The characteristics of someone or something");
        course.setEstimatedTime((short) 1);
        course.setModules(new ArrayList<>());
        course.setName("Name");
        course.setNumberOfRates(10);
        course.setStartDate("2020-03-01");
        course.setStudents(new ArrayList<>());
        course.setTags(new ArrayList<>());
        course.setTeacher(teacher);
        course.setTeacherName("Teacher Name");
        course.setTotalRate(1);

        Teacher teacher2 = new Teacher();
        teacher2.setCourses(new ArrayList<>());
        teacher2.setUserId(1L);

        Course course2 = new Course();
        course2.setDescription("The characteristics of someone or something");
        course2.setEstimatedTime((short) 1);
        course2.setModules(new ArrayList<>());
        course2.setName("Name");
        course2.setNumberOfRates(10);
        course2.setStartDate("2020-03-01");
        course2.setStudents(new ArrayList<>());
        course2.setTags(new ArrayList<>());
        course2.setTeacher(teacher2);
        course2.setTeacherName("Teacher Name");
        course2.setTotalRate(1);
        when(courseRepository.save(Mockito.<Course>any())).thenReturn(course2);
        when(courseRepository.getByCourseId(anyInt())).thenReturn(course);

        Student student = new Student();
        student.setCourses(new ArrayList<>());
        student.setUserId(1L);

        Course actualEnrollInCourseResult = courseService.enrollInCourse(1, student);

        verify(courseRepository).getByCourseId(anyInt());
        verify(courseRepository).save(Mockito.<Course>any());
        assertSame(course, actualEnrollInCourseResult);
    }

    @Test
    void testAddModule() {
        Teacher teacher = new Teacher();
        teacher.setCourses(new ArrayList<>());
        teacher.setUserId(1L);

        Course course = new Course();
        course.setDescription("The characteristics of someone or something");
        course.setEstimatedTime((short) 1);
        course.setModules(new ArrayList<>());
        course.setName("Name");
        course.setNumberOfRates(10);
        course.setStartDate("2020-03-01");
        course.setStudents(new ArrayList<>());
        course.setTags(new ArrayList<>());
        course.setTeacher(teacher);
        course.setTeacherName("Teacher Name");
        course.setTotalRate(1);

        Teacher teacher2 = new Teacher();
        teacher2.setCourses(new ArrayList<>());
        teacher2.setUserId(1L);

        Course course2 = new Course();
        course2.setDescription("The characteristics of someone or something");
        course2.setEstimatedTime((short) 1);
        course2.setModules(new ArrayList<>());
        course2.setName("Name");
        course2.setNumberOfRates(10);
        course2.setStartDate("2020-03-01");
        course2.setStudents(new ArrayList<>());
        course2.setTags(new ArrayList<>());
        course2.setTeacher(teacher2);
        course2.setTeacherName("Teacher Name");
        course2.setTotalRate(1);
        when(courseRepository.save(Mockito.<Course>any())).thenReturn(course2);
        when(courseRepository.getByCourseId(anyInt())).thenReturn(course);

        CourseModule courseModule = new CourseModule();
        courseModule.setAssignments(new ArrayList<>());
        courseModule.setLectures(new ArrayList<>());
        courseModule.setName("Name");
        courseModule.setQuizzes(new ArrayList<>());
        courseModule.setSlidesSets(new ArrayList<>());
        when(courseModuleService.createCourseModule(Mockito.<CourseModuleDTO>any())).thenReturn(courseModule);

        CourseModuleDTO courseModuleDTO = new CourseModuleDTO();
        courseModuleDTO.setAssignments(new ArrayList<>());
        courseModuleDTO.setCourseId(1);
        courseModuleDTO.setLectures(new ArrayList<>());
        courseModuleDTO.setName("Name");
        courseModuleDTO.setQuizzes(new ArrayList<>());
        courseModuleDTO.setSlidesSets(new ArrayList<>());
        courseModuleDTO.setWeekNumber(10);

        CourseModule actualAddModuleResult = courseService.addModule(courseModuleDTO);

        verify(courseRepository).getByCourseId(anyInt());
        verify(courseModuleService).createCourseModule(Mockito.<CourseModuleDTO>any());
        verify(courseRepository).save(Mockito.<Course>any());
        assertSame(courseModule, actualAddModuleResult);
    }

    @Test
    void testGetCourseModules() {
        Teacher teacher = new Teacher();
        teacher.setCourses(new ArrayList<>());
        teacher.setUserId(1L);

        Course course = new Course();
        course.setDescription("The characteristics of someone or something");
        course.setEstimatedTime((short) 1);
        ArrayList<CourseModule> modules = new ArrayList<>();
        course.setModules(modules);
        course.setName("Name");
        course.setNumberOfRates(10);
        course.setStartDate("2020-03-01");
        course.setStudents(new ArrayList<>());
        course.setTags(new ArrayList<>());
        course.setTeacher(teacher);
        course.setTeacherName("Teacher Name");
        course.setTotalRate(1);
        when(courseRepository.getByCourseId(anyInt())).thenReturn(course);

        ResponseEntity<List<CourseModuleDTO>> actualCourseModules = courseService.getCourseModules(1);

        verify(courseRepository).getByCourseId(anyInt());
        assertEquals(200, actualCourseModules.getStatusCodeValue());
        assertTrue(actualCourseModules.getHeaders().isEmpty());
        assertEquals(modules, actualCourseModules.getBody());
    }

    @Test
    void testRemoveCourse() {

        Teacher teacher = new Teacher();
        teacher.setCourses(new ArrayList<>());
        teacher.setUserId(1L);

        Course course = new Course();
        course.setDescription("The characteristics of someone or something");
        course.setEstimatedTime((short) 1);
        course.setModules(new ArrayList<>());
        course.setName("Name");
        course.setNumberOfRates(10);
        course.setStartDate("2020-03-01");
        course.setStudents(new ArrayList<>());
        course.setTags(new ArrayList<>());
        course.setTeacher(teacher);
        course.setTeacherName("Teacher Name");
        course.setTotalRate(1);
        when(courseRepository.deleteById(anyInt())).thenReturn(course);

        ResponseEntity<String> actualRemoveCourseResult = courseService.removeCourse(1);

        verify(courseRepository).deleteById(anyInt());
        assertEquals("Course deleted successfully", actualRemoveCourseResult.getBody());
        assertEquals(200, actualRemoveCourseResult.getStatusCodeValue());
        assertTrue(actualRemoveCourseResult.getHeaders().isEmpty());
    }

    @Test
    void testRemoveModule() {
        Teacher teacher = new Teacher();
        teacher.setCourses(new ArrayList<>());
        teacher.setUserId(1L);

        Course course = new Course();
        course.setDescription("The characteristics of someone or something");
        course.setEstimatedTime((short) 1);
        course.setModules(new ArrayList<>());
        course.setName("Name");
        course.setNumberOfRates(10);
        course.setStartDate("2020-03-01");
        course.setStudents(new ArrayList<>());
        course.setTags(new ArrayList<>());
        course.setTeacher(teacher);
        course.setTeacherName("Teacher Name");
        course.setTotalRate(1);

        Teacher teacher2 = new Teacher();
        teacher2.setCourses(new ArrayList<>());
        teacher2.setUserId(1L);

        Course course2 = new Course();
        course2.setDescription("The characteristics of someone or something");
        course2.setEstimatedTime((short) 1);
        course2.setModules(new ArrayList<>());
        course2.setName("Name");
        course2.setNumberOfRates(10);
        course2.setStartDate("2020-03-01");
        course2.setStudents(new ArrayList<>());
        course2.setTags(new ArrayList<>());
        course2.setTeacher(teacher2);
        course2.setTeacherName("Teacher Name");
        course2.setTotalRate(1);
        when(courseRepository.save(Mockito.<Course>any())).thenReturn(course2);
        when(courseRepository.getById(Mockito.<Integer>any())).thenReturn(course);
        doNothing().when(courseModuleService).removeModule(Mockito.<CourseModule>any());

        ResponseEntity<String> actualRemoveModuleResult = courseService.removeModule(1, 10);

        verify(courseModuleService).removeModule(Mockito.<CourseModule>any());
        verify(courseRepository).getById(Mockito.<Integer>any());
        verify(courseRepository).save(Mockito.<Course>any());
        assertEquals("Module deleted successfully", actualRemoveModuleResult.getBody());
        assertEquals(200, actualRemoveModuleResult.getStatusCodeValue());
        assertTrue(actualRemoveModuleResult.getHeaders().isEmpty());
    }

    @Test
    void testChangeRate() {

        Teacher teacher = new Teacher();
        teacher.setCourses(new ArrayList<>());
        teacher.setUserId(1L);

        Course course = new Course();
        course.setDescription("The characteristics of someone or something");
        course.setEstimatedTime((short) 1);
        course.setModules(new ArrayList<>());
        course.setName("Name");
        course.setNumberOfRates(10);
        course.setStartDate("2020-03-01");
        course.setStudents(new ArrayList<>());
        course.setTags(new ArrayList<>());
        course.setTeacher(teacher);
        course.setTeacherName("Teacher Name");
        course.setTotalRate(1);

        Teacher teacher2 = new Teacher();
        teacher2.setCourses(new ArrayList<>());
        teacher2.setUserId(1L);

        Course course2 = new Course();
        course2.setDescription("The characteristics of someone or something");
        course2.setEstimatedTime((short) 1);
        course2.setModules(new ArrayList<>());
        course2.setName("Name");
        course2.setNumberOfRates(10);
        course2.setStartDate("2020-03-01");
        course2.setStudents(new ArrayList<>());
        course2.setTags(new ArrayList<>());
        course2.setTeacher(teacher2);
        course2.setTeacherName("Teacher Name");
        course2.setTotalRate(1);
        when(courseRepository.save(Mockito.<Course>any())).thenReturn(course2);
        when(courseRepository.getByCourseId(anyInt())).thenReturn(course);

        ResponseEntity<String> actualChangeRateResult = courseService.changeRate(1, 1);

        verify(courseRepository).getByCourseId(anyInt());
        verify(courseRepository).save(Mockito.<Course>any());
        assertEquals("Rate changed successfully", actualChangeRateResult.getBody());
        assertEquals(200, actualChangeRateResult.getStatusCodeValue());
        assertTrue(actualChangeRateResult.getHeaders().isEmpty());
    }

}
