package com.example.backend.course.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.example.backend.course.model.Course;
import com.example.backend.course.model.CourseModule;
import com.example.backend.student.model.Student;
import com.example.backend.teacher.model.Teacher;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class CourseMainInfoDTOTest {

    @Test
    void testConvert() {

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

        CourseMainInfoDTO actualConvertResult = CourseMainInfoDTO.convert(course);

        assertEquals("2020-03-01", actualConvertResult.getStartDate());
        assertEquals("Name", actualConvertResult.getName());
        assertEquals("Teacher Name", actualConvertResult.getTeacherName());
        assertEquals("The characteristics of someone or something", actualConvertResult.getDescription());
        assertNull(actualConvertResult.getPhotoLink());
        assertEquals(0, actualConvertResult.getCourseId());
        assertEquals(0.1f, actualConvertResult.getRating());
        assertEquals((short) 1, actualConvertResult.getEstimatedTime());
        assertEquals(modules, actualConvertResult.getTags());
    }

    @Test
    void testConvert2() {

        Teacher teacher = new Teacher();
        teacher.setCourses(new ArrayList<>());
        teacher.setUserId(1L);
        ArrayList<String> tags = new ArrayList<>();
        ArrayList<CourseModule> modules = new ArrayList<>();
        ArrayList<Student> students = new ArrayList<>();

        Course course = new Course(1, "Name", "The characteristics of someone or something", tags, "Photo Link", 1, 10,
                (short) 1, "2020-03-01", "Teacher Name", modules, students, new Teacher());
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

        CourseMainInfoDTO actualConvertResult = CourseMainInfoDTO.convert(course);

        assertEquals("2020-03-01", actualConvertResult.getStartDate());
        assertEquals("Name", actualConvertResult.getName());
        assertEquals("Photo Link", actualConvertResult.getPhotoLink());
        assertEquals("Teacher Name", actualConvertResult.getTeacherName());
        assertEquals("The characteristics of someone or something", actualConvertResult.getDescription());
        assertEquals(0.1f, actualConvertResult.getRating());
        assertEquals(1, actualConvertResult.getCourseId());
        assertEquals((short) 1, actualConvertResult.getEstimatedTime());
        assertEquals(tags, actualConvertResult.getTags());
    }

    @Test
    void testConvert3() {

        ArrayList<String> tags = new ArrayList<>();
        tags.add("foo");

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
        course.setTags(tags);
        course.setTeacher(teacher);
        course.setTeacherName("Teacher Name");
        course.setTotalRate(1);

        CourseMainInfoDTO actualConvertResult = CourseMainInfoDTO.convert(course);

        assertEquals("2020-03-01", actualConvertResult.getStartDate());
        assertEquals("Name", actualConvertResult.getName());
        assertEquals("Teacher Name", actualConvertResult.getTeacherName());
        assertEquals("The characteristics of someone or something", actualConvertResult.getDescription());
        List<String> tags2 = actualConvertResult.getTags();
        assertEquals(1, tags2.size());
        assertEquals("foo", tags2.get(0));
        assertNull(actualConvertResult.getPhotoLink());
        assertEquals(0, actualConvertResult.getCourseId());
        assertEquals(0.1f, actualConvertResult.getRating());
        assertEquals((short) 1, actualConvertResult.getEstimatedTime());
        assertEquals(tags, tags2);
    }

    @Test
    void testConvert4() {

        ArrayList<String> tags = new ArrayList<>();
        tags.add("foo");
        tags.add("foo");

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
        course.setTags(tags);
        course.setTeacher(teacher);
        course.setTeacherName("Teacher Name");
        course.setTotalRate(1);

        CourseMainInfoDTO actualConvertResult = CourseMainInfoDTO.convert(course);

        assertEquals("2020-03-01", actualConvertResult.getStartDate());
        assertEquals("Name", actualConvertResult.getName());
        assertEquals("Teacher Name", actualConvertResult.getTeacherName());
        assertEquals("The characteristics of someone or something", actualConvertResult.getDescription());
        List<String> tags2 = actualConvertResult.getTags();
        assertEquals(2, tags2.size());
        assertEquals("foo", tags2.get(0));
        assertEquals("foo", tags2.get(1));
        assertNull(actualConvertResult.getPhotoLink());
        assertEquals(0, actualConvertResult.getCourseId());
        assertEquals(0.1f, actualConvertResult.getRating());
        assertEquals((short) 1, actualConvertResult.getEstimatedTime());
        assertEquals(tags, tags2);
    }
}
