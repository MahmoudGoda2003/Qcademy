package com.example.backend.Person.DTO;

import com.example.backend.Person.model.Person;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PersonInfoDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String photoLink;
    private String bio;
    private String DOB;


    @Autowired
    private static final ModelMapper modelMapper = new ModelMapper();

    public static PersonInfoDTO convert(Person person){
        return modelMapper.map(person, PersonInfoDTO.class);
    }
}
