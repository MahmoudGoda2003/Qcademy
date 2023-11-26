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
public class PersonMainInfoDTO {

    private Long id;
    private String firstName;
    private String lastName;

    @Autowired
    private static final ModelMapper modelMapper = new ModelMapper();


    public static PersonMainInfoDTO convert(Person person) {
        return modelMapper.map(person, PersonMainInfoDTO.class);
    }
}
