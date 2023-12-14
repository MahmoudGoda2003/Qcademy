package com.example.backend.course.service;

import com.example.backend.course.courseModule.model.CourseModule;
import com.example.backend.course.dto.CourseMainInfoDTO;
import com.example.backend.course.dto.CourseModuleDTO;
import com.example.backend.course.dto.LectureDTO;
import com.example.backend.course.lecture.model.Lecture;
import com.example.backend.course.lecture.repository.LectureRepository;
import com.example.backend.course.model.Course;
import com.example.backend.course.repository.CourseModuleRepository;
import com.example.backend.exceptions.exception.DataNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class LectureService {
    private final LectureRepository lectureRepository;
    @Autowired
    public LectureService(LectureRepository lectureRepository){
        this.lectureRepository = lectureRepository;
    }
    public Lecture createLecture(LectureDTO lectureDTO, CourseModule courseModule) {
        Lecture lecture = Lecture.convert(lectureDTO);
        lecture.setModule(courseModule);
        return lectureRepository.save(lecture);
    }
}
