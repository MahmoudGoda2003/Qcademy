package com.example.backend.course.dto;

import com.example.backend.course.model.Lecture;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
public class LectureDTO {

    private static final ModelMapper modelMapper = new ModelMapper();
    private short number;
    @NotBlank(message = "Lecture Name is mandatory")
    private String name;
    @NotBlank(message = "Lecture URL is mandatory")
    private String videoURL;

    public static LectureDTO convert(Lecture lecture) {
        return modelMapper.map(lecture, LectureDTO.class);
    }

}
