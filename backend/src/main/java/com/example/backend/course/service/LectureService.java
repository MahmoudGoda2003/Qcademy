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

import java.util.List;

@Service
@Transactional
public class LectureService {
    private final LectureRepository lectureRepository;
    private final CourseModuleRepository courseModuleRepository;
    @Autowired
    public LectureService(LectureRepository lectureRepository,
                          CourseModuleRepository courseModuleRepository){
        this.lectureRepository = lectureRepository;
        this.courseModuleRepository = courseModuleRepository;
    }

    private Lecture saveLecture(Lecture lecture) {
        return lectureRepository.save(lecture);
    }

    public void addLectureToModule(LectureDTO lectureDTO) {
        Lecture savedLecture = saveLecture(Lecture.convert(lectureDTO));
        CourseModule module = courseModuleRepository.findCourseModuleByCourseAndWeekNumber
                (lectureDTO.getModule().getCourse(),
                        lectureDTO.getModule().getWeekNumber());
        if(module == null){
            throw new DataNotFoundException("CourseModule not found");
        }
        module.getLecture().add(savedLecture);
        courseModuleRepository.save(module);
    }
    
    public List<Lecture> getAlLectures() {
        return lectureRepository.findAll();
    }

}
