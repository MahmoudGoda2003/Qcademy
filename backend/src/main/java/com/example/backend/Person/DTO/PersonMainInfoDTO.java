package com.example.backend.Person.DTO;

import com.example.backend.Person.model.Person;
import lombok.*;
import org.modelmapper.ModelMapper;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class PersonMainInfoDTO {

    private String firstName;
    private String lastName;

    private static final ModelMapper modelMapper = new ModelMapper();


    public static PersonMainInfoDTO convert(Person person) {
        return modelMapper.map(person, PersonMainInfoDTO.class);
    }
}
