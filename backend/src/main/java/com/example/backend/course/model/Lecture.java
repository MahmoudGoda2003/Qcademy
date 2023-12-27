package com.example.backend.course.model;


import com.example.backend.course.dto.LectureDTO;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Entity
@Data
@Table(name = "Lecture_data")
public class Lecture {

    private static final ModelMapper modelMapper = new ModelMapper();
    @Id
    @Column(name = "lecture_number")
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short number;
    @Column(name = "lecture_url", nullable = false)
    private String videoURL;
    @Column(name = "lecture_name", nullable = false)
    private String name;

    public static Lecture convert(LectureDTO lectureDTO) {
        return modelMapper.map(lectureDTO, Lecture.class);
    }
}
