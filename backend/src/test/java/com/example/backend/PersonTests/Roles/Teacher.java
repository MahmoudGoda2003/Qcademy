package com.example.backend.PersonTests.Roles;

import com.example.backend.admin.dto.ChangeRoleDTO;
import com.example.backend.admin.repository.AdminRepository;
import com.example.backend.person.model.Person;
import com.example.backend.person.model.Role;
import com.example.backend.person.service.PersonService;
import com.example.backend.promotion.repository.PromotionRepository;
import com.example.backend.services.JwtService;
import com.example.backend.student.repository.StudentRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.Cookie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class Teacher {
    @Autowired
    private PersonService ps;


    @Autowired
    private StudentRepository studentRepository;


    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PromotionRepository promotionRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    protected String mapToJson(Object o) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(o);
    }

    @Test
    void testPromotingTeacher() throws Exception {
        Person p = new Person("Yahya", "Azzam", "sectest1132326@gmail.com", "test", "1-2-1999", "photo.jpg");
        Long userId = ps.savePerson(p).getId();
        assertTrue(studentRepository.existsByUserId(userId));
        ps.setUserRole(userId, Role.TEACHER);

        Person adminMock = new Person("admin", "admin", "admin65464564@gmail.com", "test", "1-2-1999", "photo.jpg");
        Long adminId = ps.savePerson(adminMock).getId();
        assertTrue(studentRepository.existsByUserId(adminId));
        ps.setUserRole(adminId, Role.ADMIN);


        mockMvc.perform(MockMvcRequestBuilders.post("/teacher/requestPromotion").cookie(new Cookie("qcademy", jwtService.createToken(Role.TEACHER, userId)))).andExpect(MockMvcResultMatchers.status().isCreated());
        assertTrue(promotionRepository.existsById(userId));


        mockMvc.perform(MockMvcRequestBuilders.post("/admin/changeRole").contentType(MediaType.APPLICATION_JSON_VALUE).content(mapToJson(new ChangeRoleDTO(userId, true))).cookie(new Cookie("qcademy", jwtService.createToken(Role.ADMIN, adminId)))).andExpect(MockMvcResultMatchers.status().isCreated());
        assertFalse(promotionRepository.existsById(userId));
        assertSame(ps.getUserRole(userId), Role.ADMIN);
        assertTrue(adminRepository.existsByUserId(userId));
    }


    @Test
    void testRequestPromotionAgain() throws Exception {
        Person p = new Person("Yahya", "Azzam", "sectest5645646545@gmail.com", "test", "1-2-1999", "photo.jpg");
        Long userId = ps.savePerson(p).getId();
        assertTrue(studentRepository.existsByUserId(userId));
        ps.setUserRole(userId, Role.TEACHER);

        mockMvc.perform(MockMvcRequestBuilders.post("/teacher/requestPromotion").cookie(new Cookie("qcademy", jwtService.createToken(Role.TEACHER, userId)))).andExpect(MockMvcResultMatchers.status().isCreated());
        assertTrue(promotionRepository.existsById(userId));

        mockMvc.perform(MockMvcRequestBuilders.post("/teacher/requestPromotion").cookie(new Cookie("qcademy", jwtService.createToken(Role.TEACHER, userId))))
                .andExpect(MockMvcResultMatchers.status().isAlreadyReported());

        assertTrue(promotionRepository.existsById(userId));
    }
}
