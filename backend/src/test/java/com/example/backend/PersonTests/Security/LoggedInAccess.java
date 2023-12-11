package com.example.backend.PersonTests.Security;

import com.example.backend.AbstractTest;
import com.example.backend.person.PersonController;
import com.example.backend.person.dto.LoginDTO;
import com.example.backend.person.model.Person;
import com.example.backend.person.model.Role;
import com.example.backend.person.repository.PersonRepository;
import com.example.backend.person.service.PersonService;
import com.example.backend.services.CookiesService;
import com.example.backend.services.JwtService;
import jakarta.servlet.http.Cookie;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureMockMvc
public class LoggedInAccess {

    private final BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
    @Autowired
    private PersonService ps;
    @Autowired
    private PersonRepository pr;

    @Autowired
    private CookiesService cookiesService;

    @Autowired
    private JwtService jwtService;

    private Long userId;
    private String secretKey = new StandardEnvironment().getProperty("QcademyAuthKey");

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() throws Exception {
        pr.deleteAll();
        String pass = encode.encode("test");
        Person p = new Person("Yahya", "Azzam", "test1@gmail.com", pass, "1-2-1999", "photo.jpg");
        userId = pr.save(p).getId();
        MockHttpServletResponse httpResponse = new MockHttpServletResponse();
        ps.login(httpResponse, "test1@gmail.com", "test");
        assertEquals(HttpStatus.OK.value(), httpResponse.getStatus());
    }

    @Test
    void testStudentEndPointAsStudent() throws Exception {
        ps.setUserRole(userId, Role.STUDENT);
        mockMvc.perform(MockMvcRequestBuilders.get("/student/test").cookie(new Cookie("qcademy", jwtService.createToken(Role.STUDENT, userId))))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testAdminEndPointAsStudent() throws Exception {
        ps.setUserRole(userId, Role.STUDENT);
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/test").cookie(new Cookie("qcademy", jwtService.createToken(Role.STUDENT, userId))))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    void testTeacherEndPointAsStudent() throws Exception {
        ps.setUserRole(userId, Role.STUDENT);
        mockMvc.perform(MockMvcRequestBuilders.get("/teacher/test").cookie(new Cookie("qcademy", jwtService.createToken(Role.STUDENT, userId))))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    void testStudentEndPointAsTeacher() throws Exception {
        ps.setUserRole(userId, Role.TEACHER);
        mockMvc.perform(MockMvcRequestBuilders.get("/student/test").cookie(new Cookie("qcademy", jwtService.createToken(Role.TEACHER, userId))))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    void testTeacherEndPointAsTeacher() throws Exception {
        ps.setUserRole(userId, Role.TEACHER);
        mockMvc.perform(MockMvcRequestBuilders.get("/teacher/test").cookie(new Cookie("qcademy", jwtService.createToken(Role.TEACHER, userId))))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    void testAdminEndPointAsTeacher() throws Exception {
        ps.setUserRole(userId, Role.TEACHER);
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/test").cookie(new Cookie("qcademy", jwtService.createToken(Role.TEACHER, userId))))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    void testStudentEndPointAsAdmin() throws Exception {
        ps.setUserRole(userId, Role.TEACHER);
        mockMvc.perform(MockMvcRequestBuilders.get("/student/test").cookie(new Cookie("qcademy", jwtService.createToken(Role.ADMIN, userId))))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    void testTeacherEndPointAsAdmin() throws Exception {
        ps.setUserRole(userId, Role.TEACHER);
        mockMvc.perform(MockMvcRequestBuilders.get("/teacher/test").cookie(new Cookie("qcademy", jwtService.createToken(Role.ADMIN, userId))))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    void testAdminEndPointAsAdmin() throws Exception {
        ps.setUserRole(userId, Role.TEACHER);
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/test").cookie(new Cookie("qcademy", jwtService.createToken(Role.ADMIN, userId))))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    void testPersonEndPointAsStudent() throws Exception {
        ps.setUserRole(userId, Role.STUDENT);
        mockMvc.perform(MockMvcRequestBuilders.post("/person/login").cookie(new Cookie("qcademy", jwtService.createToken(Role.STUDENT, userId))))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError());
    }

    @Test
    void testPersonEndPointAsTeacher() throws Exception {
        ps.setUserRole(userId, Role.TEACHER);
        mockMvc.perform(MockMvcRequestBuilders.post("/person/login").cookie(new Cookie("qcademy", jwtService.createToken(Role.TEACHER, userId))))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError());
    }

    @Test
    void testPersonEndPointAsAdmin() throws Exception {
        ps.setUserRole(userId, Role.ADMIN);
        mockMvc.perform(MockMvcRequestBuilders.post("/person/test").cookie(new Cookie("qcademy", jwtService.createToken(Role.ADMIN, userId))))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError());
    }


    @Test
    void testAdminEndPoint() throws Exception {
        ps.setUserRole(userId, Role.ADMIN);
        assertNotNull(mockMvc.perform(MockMvcRequestBuilders.post("/admin/test").cookie(new Cookie("qcademy", jwtService.createToken(Role.ADMIN, userId)))).andReturn());
    }


    @Test
    void testTeacherEndPoint() throws Exception {
        ps.setUserRole(userId, Role.TEACHER);
        assertNotNull(mockMvc.perform(MockMvcRequestBuilders.post("/teacher/test").cookie(new Cookie("qcademy", jwtService.createToken(Role.TEACHER, userId)))).andReturn());
    }


    @Test
    void testStudentEndPoint() throws Exception {
        ps.setUserRole(userId, Role.STUDENT);
        assertNotNull(mockMvc.perform(MockMvcRequestBuilders.post("/student/test").cookie(new Cookie("qcademy", jwtService.createToken(Role.STUDENT, userId)))).andReturn());
    }
}
