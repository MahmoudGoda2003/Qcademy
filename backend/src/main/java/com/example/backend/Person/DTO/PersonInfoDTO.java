package com.example.backend.Person.DTO;

import com.example.backend.Person.model.Person;
import lombok.*;
import org.modelmapper.ModelMapper;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class PersonInfoDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String photoLink;
    private String dateOfBirth;


    private static final ModelMapper modelMapper = new ModelMapper();

    public static PersonInfoDTO convert(Person person){
        return modelMapper.map(person, PersonInfoDTO.class);
    }
}
