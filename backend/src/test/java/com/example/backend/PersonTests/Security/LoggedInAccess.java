package com.example.backend.PersonTests.Security;

import com.example.backend.admin.dto.ChangeRoleDTO;
import com.example.backend.person.model.Person;
import com.example.backend.person.model.Role;
import com.example.backend.person.repository.PersonRepository;
import com.example.backend.person.service.PersonService;
import com.example.backend.services.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.Cookie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
public class LoggedInAccess {

    private final BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
    @Autowired
    private PersonService ps;
    @Autowired
    private PersonRepository pr;

    @Autowired
    private JwtService jwtService;

    private Long userId;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() throws Exception {
        pr.deleteAll();
        String pass = encode.encode("test");
        Person p = new Person("Yahya", "Azzam", "sectest11@gmail.com", pass, "1-2-1999", "photo.jpg");
        userId = pr.save(p).getId();
        MockHttpServletResponse httpResponse = new MockHttpServletResponse();
        ps.login(httpResponse, "sectest11@gmail.com", "test");
        assertEquals(HttpStatus.OK.value(), httpResponse.getStatus());
    }

    @Test
    void testStudentEndPointAsStudent() throws Exception {
        ps.setUserRole(userId, Role.STUDENT);
        mockMvc.perform(MockMvcRequestBuilders.post("/student/requestPromotion").cookie(new Cookie("qcademy", jwtService.createToken(Role.STUDENT, userId)))).andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void testAdminEndPointAsStudent() throws Exception {
        ps.setUserRole(userId, Role.STUDENT);
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/test").cookie(new Cookie("qcademy", jwtService.createToken(Role.STUDENT, userId)))).andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    void testTeacherEndPointAsStudent() throws Exception {
        ps.setUserRole(userId, Role.STUDENT);
        mockMvc.perform(MockMvcRequestBuilders.get("/teacher/test").cookie(new Cookie("qcademy", jwtService.createToken(Role.STUDENT, userId)))).andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    void testStudentEndPointAsTeacher() throws Exception {
        ps.setUserRole(userId, Role.TEACHER);
        mockMvc.perform(MockMvcRequestBuilders.get("/student/test").cookie(new Cookie("qcademy", jwtService.createToken(Role.TEACHER, userId)))).andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    void testTeacherEndPointAsTeacher() throws Exception {
        ps.setUserRole(userId, Role.TEACHER);
        mockMvc.perform(MockMvcRequestBuilders.post("/teacher/requestPromotion").cookie(new Cookie("qcademy", jwtService.createToken(Role.TEACHER, userId)))).andExpect(MockMvcResultMatchers.status().isCreated());
    }


    @Test
    void testAdminEndPointAsTeacher() throws Exception {
        ps.setUserRole(userId, Role.TEACHER);
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/test").cookie(new Cookie("qcademy", jwtService.createToken(Role.TEACHER, userId)))).andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    void testStudentEndPointAsAdmin() throws Exception {
        ps.setUserRole(userId, Role.TEACHER);
        mockMvc.perform(MockMvcRequestBuilders.get("/student/test").cookie(new Cookie("qcademy", jwtService.createToken(Role.ADMIN, userId)))).andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    void testTeacherEndPointAsAdmin() throws Exception {
        ps.setUserRole(userId, Role.TEACHER);
        mockMvc.perform(MockMvcRequestBuilders.get("/teacher/test").cookie(new Cookie("qcademy", jwtService.createToken(Role.ADMIN, userId)))).andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    void testAdminEndPointAsAdmin() throws Exception {
        ps.setUserRole(userId, Role.TEACHER);
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/test").cookie(new Cookie("qcademy", jwtService.createToken(Role.ADMIN, userId)))).andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    void testPersonEndPointAsStudent() throws Exception {
        ps.setUserRole(userId, Role.STUDENT);
        mockMvc.perform(MockMvcRequestBuilders.post("/person/login").cookie(new Cookie("qcademy", jwtService.createToken(Role.STUDENT, userId)))).andExpect(MockMvcResultMatchers.status().isInternalServerError());
    }

    @Test
    void testPersonEndPointAsTeacher() throws Exception {
        ps.setUserRole(userId, Role.TEACHER);
        mockMvc.perform(MockMvcRequestBuilders.post("/person/login").cookie(new Cookie("qcademy", jwtService.createToken(Role.TEACHER, userId)))).andExpect(MockMvcResultMatchers.status().isInternalServerError());
    }

    @Test
    void testPersonEndPointAsAdmin() throws Exception {
        ps.setUserRole(userId, Role.ADMIN);
        mockMvc.perform(MockMvcRequestBuilders.post("/person/login").cookie(new Cookie("qcademy", jwtService.createToken(Role.ADMIN, userId)))).andExpect(MockMvcResultMatchers.status().isInternalServerError());
    }


    @Test
    void testAdminEndpoint() throws Exception {
        ps.setUserRole(userId, Role.ADMIN);
        ChangeRoleDTO changeRoleDTO = new ChangeRoleDTO(userId, true);

        mockMvc.perform(MockMvcRequestBuilders.post("/admin/changeRole").cookie(new Cookie("qcademy", jwtService.createToken(Role.ADMIN, userId))).contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(changeRoleDTO))).andExpect(MockMvcResultMatchers.status().isNotFound());
    }


    @Test
    void testTeacherEndPoint() throws Exception {
        ps.setUserRole(userId, Role.TEACHER);
        mockMvc.perform(MockMvcRequestBuilders.post("/teacher/requestPromotion").cookie(new Cookie("qcademy", jwtService.createToken(Role.TEACHER, userId)))).andExpect(MockMvcResultMatchers.status().isCreated()).andExpect(MockMvcResultMatchers.content().string("Promotion request successfully"));
    }

    @Test
    void testStudentEndPoint() throws Exception {
        ps.setUserRole(userId, Role.STUDENT);
        mockMvc.perform(MockMvcRequestBuilders.post("/student/requestPromotion").cookie(new Cookie("qcademy", jwtService.createToken(Role.STUDENT, userId)))).andExpect(MockMvcResultMatchers.status().isCreated()).andExpect(MockMvcResultMatchers.content().string("Promotion request successfully"));
    }

    @Test
    void testWithJWTNull() throws Exception {
        ps.setUserRole(userId, Role.STUDENT);
        mockMvc.perform(MockMvcRequestBuilders.get("/student/").cookie(new Cookie("hello", "world"))).andExpect(MockMvcResultMatchers.status().isForbidden());
    }

}
