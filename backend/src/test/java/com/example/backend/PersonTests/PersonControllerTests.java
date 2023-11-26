package com.example.backend.PersonTests;

import com.example.backend.AbstractTest;
import com.example.backend.Person.DTO.SignUpDTO;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PersonControllerTests extends AbstractTest {

    @Override
    @Before
    public void setup() {
        super.setup();
    }

    @Test
    public void signUpNormal() throws Exception {
        String uri = "/signup", email = "yahya912azzam@gmail.com";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.TEXT_PLAIN).content(email)).andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());
        assertEquals("Email accepted", mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void validateWithoutSignUp() throws Exception {
        String uri = "/signup/validate";
        SignUpDTO signUpDTO = new SignUpDTO("Yahya", "Azzam", "test@test.com", "test", "1-2-1999");
        signUpDTO.setCode("201356");
        String input = super.mapToJson(signUpDTO);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(input)).andReturn();
        assertEquals(404, mvcResult.getResponse().getStatus());
        assertEquals("{\"error message\":\"Try to sign up again\"}", mvcResult.getResponse().getContentAsString());
    }
}
