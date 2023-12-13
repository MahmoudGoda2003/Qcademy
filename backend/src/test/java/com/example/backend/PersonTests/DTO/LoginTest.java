package com.example.backend.PersonTests.DTO;

import com.example.backend.AbstractTest;
import com.example.backend.person.dto.LoginDTO;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest extends AbstractTest {
    @Override
    @Before
    public void setup() throws Exception {
        super.setup();
    }

    @Test
    public void LoginWithNullEmail() throws Exception {
        String uri = "/person/login";
        LoginDTO loginDTO = new LoginDTO(null, "test");
        String input = super.mapToJson(loginDTO);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(input)).andReturn();

        assertEquals(HttpStatus.BAD_REQUEST.value(), mvcResult.getResponse().getStatus());

        String responseContent = mvcResult.getResponse().getContentAsString();
        assertTrue(responseContent.contains("Email cannot be blank"));
    }

    @Test
    public void LoginWithInvalidEmailFormat() throws Exception {
        String uri = "/person/login";
        LoginDTO loginDTO = new LoginDTO("test1gmail.com", "test");
        String input = super.mapToJson(loginDTO);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(input)).andReturn();

        assertEquals(HttpStatus.BAD_REQUEST.value(), mvcResult.getResponse().getStatus());

        String responseContent = mvcResult.getResponse().getContentAsString();
        assertTrue(responseContent.contains("Invalid email format"));
    }

    @Test
    public void LoginWithNullPassword() throws Exception {
        String uri = "/person/login";
        LoginDTO loginDTO = new LoginDTO("test1@gmail.com", null);
        String input = super.mapToJson(loginDTO);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(input)).andReturn();

        assertEquals(HttpStatus.BAD_REQUEST.value(), mvcResult.getResponse().getStatus());

        String responseContent = mvcResult.getResponse().getContentAsString();
        assertTrue(responseContent.contains("Password cannot be blank"));
    }


}
