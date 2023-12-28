package com.example.backend.person.dto;

import com.example.backend.person.model.Person;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;


@Data
@NoArgsConstructor
public class PersonInfoDTO {

    private static final ModelMapper modelMapper = new ModelMapper();

    @NotBlank(message = "First name cannot be blank")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    private String lastName;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    private String email;

    private String photoLink;

    @Size(max = 255, message = "Bio should not exceed 255 characters")
    private String bio;

    @NotBlank(message = "Date of birth cannot be Empty")
    @Pattern(regexp = "^\\d{1,2}-\\d{1,2}-\\d{4}$", message = "Invalid date of birth format. Use DD-MM-YYYY")
    private String dateOfBirth;

    public static PersonInfoDTO convert(Person person) {
        return modelMapper.map(person, PersonInfoDTO.class);
    }
}