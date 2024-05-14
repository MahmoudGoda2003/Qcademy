package com.example.backend.course.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.backend.course.model.Lecture;
import org.junit.jupiter.api.Test;

class LectureDTOTest {

    @Test
    void testConvert() {

        Lecture lecture = new Lecture();
        lecture.setName("Name");
        lecture.setVideoURL("https://example.org/example");

        LectureDTO actualConvertResult = LectureDTO.convert(lecture);

        assertEquals("Name", actualConvertResult.getName());
        assertEquals("https://example.org/example", actualConvertResult.getVideoURL());
        assertEquals((short) 0, actualConvertResult.getNumber());
    }

    @Test
    void testConvert2() {

        Lecture lecture = new Lecture();
        lecture.setName("com.example.backend.course.model.Lecture");
        lecture.setVideoURL("https://example.org/example");

        LectureDTO actualConvertResult = LectureDTO.convert(lecture);

        assertEquals("com.example.backend.course.model.Lecture", actualConvertResult.getName());
        assertEquals("https://example.org/example", actualConvertResult.getVideoURL());
        assertEquals((short) 0, actualConvertResult.getNumber());
    }

    @Test
    void testConvert3() {

        Lecture lecture = new Lecture();
        lecture.setName("com.example.backend.course.model.Lecture");
        lecture.setVideoURL("com.example.backend.course.model.Lecture");

        LectureDTO actualConvertResult = LectureDTO.convert(lecture);

        assertEquals("com.example.backend.course.model.Lecture", actualConvertResult.getName());
        assertEquals("com.example.backend.course.model.Lecture", actualConvertResult.getVideoURL());
        assertEquals((short) 0, actualConvertResult.getNumber());
    }
}
