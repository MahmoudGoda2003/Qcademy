package com.example.backend.course.service;

import com.example.backend.course.dto.LectureDTO;
import com.example.backend.course.lecture.model.Lecture;
import com.example.backend.course.lecture.repository.LectureRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class LectureService {
    private final LectureRepository lectureRepository;
    @Autowired
    public LectureService(LectureRepository lectureRepository){
        this.lectureRepository = lectureRepository;
    }
    public Lecture createLecture(LectureDTO lectureDTO) {
        Lecture lecture = Lecture.convert(lectureDTO);
        return lectureRepository.save(lecture);
    }
}
