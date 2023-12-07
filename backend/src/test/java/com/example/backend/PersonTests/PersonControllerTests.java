package com.example.backend.PersonTests;

import com.example.backend.AbstractTest;
import com.example.backend.exceptions.exception.LoginDataNotValidException;
import com.example.backend.exceptions.exception.WrongDataEnteredException;
import com.example.backend.person.dto.SignUpDTO;
import com.example.backend.person.PersonController;
import jakarta.mail.MessagingException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class PersonControllerTests extends AbstractTest {

    @Autowired
    private PersonController pc;

    @Override
    @Before
    public void setup() {
        super.setup();
    }

    @Test
    public void signUpNormal() throws Exception {
        String uri = "/person/signup", email = "yahya912azzam@gmail.com";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.TEXT_PLAIN).content(email)).andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());
        assertEquals("Email accepted", mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void validateWithoutSignUp() throws Exception {
        String uri = "/person/signup/validate";
        SignUpDTO signUpDTO = new SignUpDTO("Yahya", "Azzam", "test@test.com", "test", "1-2-1999");
        signUpDTO.setCode("201356");
        String input = super.mapToJson(signUpDTO);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(input)).andReturn();
        assertEquals(404, mvcResult.getResponse().getStatus());
        assertEquals("{\"error message\":\"Try to sign up again\"}", mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void test_methods_directly() throws MessagingException {
        ResponseEntity<String> request = pc.signUp(null, "yahya912ahmed@gmail.com");
        assertEquals(HttpStatus.OK, request.getStatusCode());
        assertEquals("Email accepted", request.getBody());
        SignUpDTO signUpDTO = new SignUpDTO("Yahya", "Azzam", "yahya912ahmed@gmail.com", "test", "1-2-1999");
        signUpDTO.setCode("testing");
        assertThrowsExactly(WrongDataEnteredException.class, () -> pc.validateOTP(null, signUpDTO));
        assertThrows(HttpClientErrorException.class, () -> pc.googleSignIn(null, "token"));
        //assertThrowsExactly(LoginDataNotValidException.class, () -> pc.logIn(null, "klk", "llo"));
    }
}
