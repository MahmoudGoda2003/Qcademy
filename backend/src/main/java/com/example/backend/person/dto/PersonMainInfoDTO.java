package com.example.backend.person.dto;

import com.example.backend.person.model.Person;
import lombok.*;
import org.modelmapper.ModelMapper;


@Getter
@Setter
@NoArgsConstructor
public class PersonMainInfoDTO {
    private String firstName;
    private String lastName;

    private static final ModelMapper modelMapper = new ModelMapper();


    public static PersonMainInfoDTO convert(Person person) {
        return modelMapper.map(person, PersonMainInfoDTO.class);
    }
}
