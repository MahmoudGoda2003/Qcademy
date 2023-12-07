package com.example.backend.person.dto;

import com.example.backend.person.model.Person;
import lombok.*;
import org.modelmapper.ModelMapper;

@Getter
@Setter
@NoArgsConstructor
public class PersonInfoDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String photoLink;
    private String bio;
    private String dateOfBirth;


    private static final ModelMapper modelMapper = new ModelMapper();

    public static PersonInfoDTO convert(Person person){
        return modelMapper.map(person, PersonInfoDTO.class);
    }
}
