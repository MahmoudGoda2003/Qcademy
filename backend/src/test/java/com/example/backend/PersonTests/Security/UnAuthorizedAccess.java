package com.example.backend.PersonTests.Security;

import com.example.backend.person.dto.LoginDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
public class UnAuthorizedAccess {
    @Autowired
    private MockMvc mockMvc;

    protected String mapToJson(Object o) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(o);
    }
    @Test
    void testAdminEndPointUnAuthorized() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/test"))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    void testStudentEndPointUnAuthorized() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/student/test"))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    void testTeacherEndPointUnAuthorized() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/teacher/test"))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }


    @Test
    void testPersonEndPointUnAuthorized() throws Exception {
        LoginDTO loginDTO = new LoginDTO("example", "test");
        String input = mapToJson(loginDTO);
        mockMvc.perform(MockMvcRequestBuilders.post("/person/login")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(input))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
