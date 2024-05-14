package com.example.backend.Person.DTO;

import com.example.backend.AbstractTest;
import com.example.backend.person.dto.SignUpDTO;
import com.example.backend.person.model.Person;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class SignUpTest extends AbstractTest {

    @Override
    @Before
    public void setup() throws Exception {
        super.setup();
    }

    @Test
    public void SignUpConvert() {
        SignUpDTO signUpDTO = new SignUpDTO("Yahya", "Azzam", "test1@gmail.com", "test", "1-2-1999", "13254");
        Person person = Person.convert(signUpDTO);
        assertAll(() -> assertEquals(signUpDTO.getFirstName(), person.getFirstName()), () -> assertEquals(signUpDTO.getLastName(), person.getLastName()), () -> assertEquals(signUpDTO.getEmail(), person.getEmail()), () -> assertEquals(signUpDTO.getPassword(), person.getPassword()), () -> assertEquals(signUpDTO.getDateOfBirth(), person.getDateOfBirth()), () -> assertEquals(null, person.getPhotoLink()));
    }

    @Test
    public void signUpWithNullFirstName() throws Exception {
        String uri = "/person/signup/validate";
        SignUpDTO signUpDTO = new SignUpDTO(null, "Azzam", "test1@gmail.com", "test", "1-2-1999", "201356");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post(uri)

                .contentType(MediaType.APPLICATION_JSON_VALUE).content(super.mapToJson(signUpDTO));

        MvcResult mvcResult = mvc.perform(requestBuilder).andReturn();

        assertEquals(HttpStatus.BAD_REQUEST.value(), mvcResult.getResponse().getStatus());

        String responseContent = mvcResult.getResponse().getContentAsString();
        assertTrue(responseContent.contains("First name cannot be Empty"));
    }

    @Test
    public void signUpWithNullLastName() throws Exception {
        String uri = "/person/signup/validate";
        SignUpDTO signUpDTO = new SignUpDTO("Yahya", null, "test1@gmail.com", "test", "1-2-1999", "201356");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post(uri)

                .contentType(MediaType.APPLICATION_JSON_VALUE).content(super.mapToJson(signUpDTO));
        MvcResult mvcResult = mvc.perform(requestBuilder).andReturn();
        assertEquals(HttpStatus.BAD_REQUEST.value(), mvcResult.getResponse().getStatus());
        String responseContent = mvcResult.getResponse().getContentAsString();
        assertTrue(responseContent.contains("Last name cannot be Empty"));
    }

    @Test
    public void signUpWithNullEmail() throws Exception{
        String uri = "/person/signup/validate";
        SignUpDTO signUpDTO = new SignUpDTO("Yahya", "Azzam", null, "test", "1-2-1999", "201356");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post(uri)

                .contentType(MediaType.APPLICATION_JSON_VALUE).content(super.mapToJson(signUpDTO));
        MvcResult mvcResult = mvc.perform(requestBuilder).andReturn();
        assertEquals(HttpStatus.BAD_REQUEST.value(), mvcResult.getResponse().getStatus());
        String responseContent = mvcResult.getResponse().getContentAsString();
        assertTrue(responseContent.contains("Email cannot be Empty"));
    }

    @Test
    public void signUpWithInvalidEmailFormat() throws Exception{
        String uri = "/person/signup/validate";
        SignUpDTO signUpDTO = new SignUpDTO("Yahya", "Azzam", "WrongEmail", "test", "1-2-1999", "201356");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post(uri)

                .contentType(MediaType.APPLICATION_JSON_VALUE).content(super.mapToJson(signUpDTO));
        MvcResult mvcResult = mvc.perform(requestBuilder).andReturn();
        assertEquals(HttpStatus.BAD_REQUEST.value(), mvcResult.getResponse().getStatus());
        String responseContent = mvcResult.getResponse().getContentAsString();
        assertTrue(responseContent.contains("Invalid email format"));
    }

    @Test
    public void signUpWithNullPassword() throws Exception{
        String uri = "/person/signup/validate";
        SignUpDTO signUpDTO = new SignUpDTO("Yahya", "Azzam", "WrongEmail", null, "1-2-1999", "201356");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post(uri)

                .contentType(MediaType.APPLICATION_JSON_VALUE).content(super.mapToJson(signUpDTO));
        MvcResult mvcResult = mvc.perform(requestBuilder).andReturn();
        assertEquals(HttpStatus.BAD_REQUEST.value(), mvcResult.getResponse().getStatus());
        String responseContent = mvcResult.getResponse().getContentAsString();
        assertTrue(responseContent.contains("Password cannot be Empty"));
    }

    @Test
    public void signUpWithNullDateOfBirth() throws Exception{
        String uri = "/person/signup/validate";
        SignUpDTO signUpDTO = new SignUpDTO("Yahya", "Azzam", "WrongEmail", "test", null, "201356");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post(uri)

                .contentType(MediaType.APPLICATION_JSON_VALUE).content(super.mapToJson(signUpDTO));
        MvcResult mvcResult = mvc.perform(requestBuilder).andReturn();
        assertEquals(HttpStatus.BAD_REQUEST.value(), mvcResult.getResponse().getStatus());
        String responseContent = mvcResult.getResponse().getContentAsString();
        assertTrue(responseContent.contains("Date of birth cannot be Empty"));
    }

    @Test
    public void signUpWithInvalidDateOfBirthFormat() throws Exception{
        String uri = "/person/signup/validate";
        SignUpDTO signUpDTO = new SignUpDTO("Yahya", "Azzam", "WrongEmail", "test", "199-2-33", "201356");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post(uri)

                .contentType(MediaType.APPLICATION_JSON_VALUE).content(super.mapToJson(signUpDTO));
        MvcResult mvcResult = mvc.perform(requestBuilder).andReturn();
        assertEquals(HttpStatus.BAD_REQUEST.value(), mvcResult.getResponse().getStatus());
        String responseContent = mvcResult.getResponse().getContentAsString();
        assertTrue(responseContent.contains("Invalid date of birth format. Use DD-MM-YYYY"));
    }
}
