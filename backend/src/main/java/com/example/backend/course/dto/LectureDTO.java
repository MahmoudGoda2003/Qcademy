package com.example.backend.course.dto;

import com.example.backend.course.courseModule.model.CourseModule;
import com.example.backend.course.lecture.model.Lecture;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter
@Setter
public class LectureDTO {
    @NotBlank(message = "Lecture number is mandatory")
    private short number;

    @NotBlank(message = "Lecture Name is mandatory")
    private String name;

    @NotBlank(message = "Lecture URL is mandatory")
    private String lectureURL;

    @NotBlank(message = "Module is mandatory")
    private CourseModule module;

    private static final ModelMapper modelMapper = new ModelMapper();
    public static LectureDTO convert(Lecture lecture) {
        return modelMapper.map(lecture, LectureDTO.class);
    }

}
