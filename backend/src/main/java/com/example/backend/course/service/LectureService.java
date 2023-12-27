package com.example.backend.course.service;

import com.example.backend.course.dto.LectureDTO;
import com.example.backend.course.model.Lecture;
import com.example.backend.course.repository.LectureRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LectureService {
    private final LectureRepository lectureRepository;

    public Lecture createLecture(LectureDTO lectureDTO) {
        Lecture lecture = Lecture.convert(lectureDTO);
        return lectureRepository.save(lecture);
    }
}
