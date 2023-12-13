package com.example.backend.person.dto;

import com.example.backend.person.model.Person;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;


@Getter
@Setter
@NoArgsConstructor
public class PersonMainInfoDTO {

    private static final ModelMapper modelMapper = new ModelMapper();
    @NotBlank(message = "First name cannot be blank")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    private String lastName;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    private String email;

    private String photoLink;

    public static PersonMainInfoDTO convert(Person person) {
        return modelMapper.map(person, PersonMainInfoDTO.class);
    }
}
