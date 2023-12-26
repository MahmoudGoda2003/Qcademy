package com.example.backend.course.dto;

import com.example.backend.course.model.Lecture;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter
@Setter
@NoArgsConstructor
public class LectureDTO {

    private short number;

    @NotBlank(message = "Lecture Name is mandatory")
    private String name;

    @NotBlank(message = "Lecture URL is mandatory")
    private String videoURL;

    private static final ModelMapper modelMapper = new ModelMapper();
    public static LectureDTO convert(Lecture lecture) {
        return modelMapper.map(lecture, LectureDTO.class);
    }

}
