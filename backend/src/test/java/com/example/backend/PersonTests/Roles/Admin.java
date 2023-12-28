package com.example.backend.PersonTests.Roles;

import com.example.backend.admin.dto.ChangeRoleDTO;
import com.example.backend.admin.repository.AdminRepository;
import com.example.backend.admin.service.AdminService;
import com.example.backend.person.model.Person;
import com.example.backend.person.model.Role;
import com.example.backend.person.repository.PersonRepository;
import com.example.backend.person.service.PersonService;
import com.example.backend.promotion.model.Promotion;
import com.example.backend.promotion.repository.PromotionRepository;
import com.example.backend.services.JwtService;
import com.example.backend.student.repository.StudentRepository;
import com.example.backend.student.service.StudentService;
import com.example.backend.teacher.repository.TeacherRepository;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class Admin {
    private final BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
    @Autowired
    private PersonService ps;
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private StudentService studentService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PromotionRepository promotionRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private MockMvc mockMvc;

    protected String mapToJson(Object o) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(o);
    }

    private void promoteUser(Long adminId, Long userId, Role role, String uri) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(uri).cookie(new Cookie("qcademy", jwtService.createToken(role, userId)))).andExpect(MockMvcResultMatchers.status().isCreated());
        assertTrue(promotionRepository.existsById(userId));

        mockMvc.perform(MockMvcRequestBuilders.post("/admin/changeRole").contentType(MediaType.APPLICATION_JSON_VALUE).content(mapToJson(new ChangeRoleDTO(userId, true))).cookie(new Cookie("qcademy", jwtService.createToken(Role.ADMIN, adminId)))).andExpect(MockMvcResultMatchers.status().isCreated());
        assertFalse(promotionRepository.existsById(userId));
    }

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testChangeRole() throws Exception {
        Person p = new Person("Yahya", "Azzam", "sectest1122@gmail.com", "test", "1-2-1999", "photo.jpg");
        Long userId = ps.savePerson(p).getId();
        assertTrue(studentRepository.existsByUserId(userId));

        mockMvc.perform(MockMvcRequestBuilders.post("/student/requestPromotion").cookie(new Cookie("qcademy", jwtService.createToken(Role.STUDENT, userId)))).andExpect(MockMvcResultMatchers.status().isCreated());
        assertTrue(promotionRepository.existsById(userId));

        adminService.changePersonRole(userId, true);

        assertFalse(promotionRepository.existsById(userId));
        assertSame(ps.getUserRole(userId), Role.TEACHER);
        assertTrue(teacherRepository.existsByUserId(userId));
    }


    @Test
    void testChangeRoleUserIdWrong() throws Exception {
        Person adminMock = new Person("admin", "admin", "admin1@gmail.com", "test", "1-2-1999", "photo.jpg");
        Long adminId = ps.savePerson(adminMock).getId();
        assertTrue(studentRepository.existsByUserId(adminId));
        ps.setUserRole(adminId, Role.ADMIN);
        Long userId = 123L;
        mockMvc.perform(MockMvcRequestBuilders.post("/admin/changeRole").contentType(MediaType.APPLICATION_JSON_VALUE).content(mapToJson(new ChangeRoleDTO(userId, true))).cookie(new Cookie("qcademy", jwtService.createToken(Role.ADMIN, adminId)))).andExpect(MockMvcResultMatchers.status().isNotFound());

        assertFalse(promotionRepository.existsById(userId));
        assertFalse(adminRepository.existsByUserId(userId));
        assertFalse(studentRepository.existsByUserId(userId));
        assertFalse(teacherRepository.existsByUserId(userId));
    }


    @Test
    void testChangeRoleUserExistsButDidntRequestPromotion() throws Exception {
        Person p = new Person("Yahya", "Azzam", "sectest12222@gmail.com", "test", "1-2-1999", "photo.jpg");
        Long userId = ps.savePerson(p).getId();
        assertTrue(studentRepository.existsByUserId(userId));

        Person adminMock = new Person("admin", "admin", "admin2@gmail.com", "test", "1-2-1999", "photo.jpg");
        Long adminId = ps.savePerson(adminMock).getId();
        assertTrue(studentRepository.existsByUserId(adminId));
        ps.setUserRole(adminId, Role.ADMIN);

        mockMvc.perform(MockMvcRequestBuilders.post("/admin/changeRole").contentType(MediaType.APPLICATION_JSON_VALUE).content(mapToJson(new ChangeRoleDTO(userId, true))).cookie(new Cookie("qcademy", jwtService.createToken(Role.ADMIN, adminId)))).andExpect(MockMvcResultMatchers.status().isNotFound());

        assertFalse(promotionRepository.existsById(userId));
        assertFalse(adminRepository.existsByUserId(userId));
        assertTrue(studentRepository.existsByUserId(userId));
        assertFalse(teacherRepository.existsByUserId(userId));
    }

    @Test
    void testChangingRoleFromTeacherToStudent() throws Exception {
        Person p = new Person("Yahya", "Azzam", "sectest123@gmail.com", "test", "1-2-1999", "photo.jpg");
        Long userId = ps.savePerson(p).getId();
        assertTrue(studentRepository.existsByUserId(userId));

        Person adminMock = new Person("admin", "admin", "admin4@gmail.com", "test", "1-2-1999", "photo.jpg");
        Long adminId = ps.savePerson(adminMock).getId();
        assertTrue(studentRepository.existsByUserId(adminId));
        ps.setUserRole(adminId, Role.ADMIN);

        promoteUser(adminId, userId, Role.STUDENT, "/student/requestPromotion");

        assertSame(ps.getUserRole(userId), Role.TEACHER);

        Promotion promotion = new Promotion(userId, Role.STUDENT);
        promotionRepository.save(promotion);
        mockMvc.perform(MockMvcRequestBuilders.post("/admin/changeRole").contentType(MediaType.APPLICATION_JSON_VALUE).content(mapToJson(new ChangeRoleDTO(userId, true))).cookie(new Cookie("qcademy", jwtService.createToken(Role.ADMIN, adminId)))).andExpect(MockMvcResultMatchers.status().isCreated());
        assertFalse(promotionRepository.existsById(userId));
        assertSame(ps.getUserRole(userId), Role.STUDENT);
        assertTrue(teacherRepository.existsByUserId(userId));
        assertTrue(studentRepository.existsByUserId(userId));
    }

    @Test
    void testChangingRoleFromAdminToTeacher() throws Exception {
        Person p = new Person("Yahya", "Azzam", "sectest122@gmail.com", "test", "1-2-1999", "photo.jpg");
        Long userId = ps.savePerson(p).getId();
        assertTrue(studentRepository.existsByUserId(userId));

        Person adminMock = new Person("admin", "admin", "admin123@gmail.com", "test", "1-2-1999", "photo.jpg");
        Long adminId = ps.savePerson(adminMock).getId();
        assertTrue(studentRepository.existsByUserId(adminId));
        ps.setUserRole(adminId, Role.ADMIN);

        promoteUser(adminId, userId, Role.STUDENT, "/student/requestPromotion");
        promoteUser(adminId, userId, Role.TEACHER, "/teacher/requestPromotion");

        assertSame(ps.getUserRole(userId), Role.ADMIN);

        Promotion promotion = new Promotion(userId, Role.TEACHER);
        promotionRepository.save(promotion);
        mockMvc.perform(MockMvcRequestBuilders.post("/admin/changeRole").contentType(MediaType.APPLICATION_JSON_VALUE).content(mapToJson(new ChangeRoleDTO(userId, true))).cookie(new Cookie("qcademy", jwtService.createToken(Role.ADMIN, adminId)))).andExpect(MockMvcResultMatchers.status().isCreated());
        assertFalse(promotionRepository.existsById(userId));
        assertSame(ps.getUserRole(userId), Role.TEACHER);
        assertTrue(teacherRepository.existsByUserId(userId));
        assertTrue(studentRepository.existsByUserId(userId));
        assertTrue(adminRepository.existsByUserId(userId));
    }


    @Test
    void testChangingRoleFromAdminToTeacherAndToAdmin() throws Exception {
        Person p = new Person("Yahya", "Azzam", "sectest112312@gmail.com", "test", "1-2-1999", "photo.jpg");
        Long userId = ps.savePerson(p).getId();
        assertTrue(studentRepository.existsByUserId(userId));

        Person adminMock = new Person("admin", "admin", "admin234234@gmail.com", "test", "1-2-1999", "photo.jpg");
        Long adminId = ps.savePerson(adminMock).getId();
        assertTrue(studentRepository.existsByUserId(adminId));
        ps.setUserRole(adminId, Role.ADMIN);

        Promotion promotion = new Promotion(userId, Role.ADMIN);
        promotionRepository.save(promotion);
        mockMvc.perform(MockMvcRequestBuilders.post("/admin/changeRole").contentType(MediaType.APPLICATION_JSON_VALUE).content(mapToJson(new ChangeRoleDTO(userId, true))).cookie(new Cookie("qcademy", jwtService.createToken(Role.ADMIN, adminId)))).andExpect(MockMvcResultMatchers.status().isCreated());
        assertSame(ps.getUserRole(userId), Role.ADMIN);

        promotion = new Promotion(userId, Role.STUDENT);
        promotionRepository.save(promotion);
        mockMvc.perform(MockMvcRequestBuilders.post("/admin/changeRole").contentType(MediaType.APPLICATION_JSON_VALUE).content(mapToJson(new ChangeRoleDTO(userId, true))).cookie(new Cookie("qcademy", jwtService.createToken(Role.ADMIN, adminId)))).andExpect(MockMvcResultMatchers.status().isCreated());
        assertSame(ps.getUserRole(userId), Role.STUDENT);

        promotion = new Promotion(userId, Role.ADMIN);
        promotionRepository.save(promotion);
        mockMvc.perform(MockMvcRequestBuilders.post("/admin/changeRole").contentType(MediaType.APPLICATION_JSON_VALUE).content(mapToJson(new ChangeRoleDTO(userId, true))).cookie(new Cookie("qcademy", jwtService.createToken(Role.ADMIN, adminId)))).andExpect(MockMvcResultMatchers.status().isCreated());

        assertFalse(promotionRepository.existsById(userId));
        assertSame(ps.getUserRole(userId), Role.ADMIN);
        assertFalse(teacherRepository.existsByUserId(userId));
        assertTrue(studentRepository.existsByUserId(userId));
        assertTrue(adminRepository.existsByUserId(userId));
    }

    @Test
    void testChangingRoleFromAdminToStudent() throws Exception {
        Person p = new Person("Yahya", "Azzam", "sectest3123123@gmail.com", "test", "1-2-1999", "photo.jpg");
        Long userId = ps.savePerson(p).getId();
        assertTrue(studentRepository.existsByUserId(userId));

        Person adminMock = new Person("admin", "admin", "admin3123123@gmail.com", "test", "1-2-1999", "photo.jpg");
        Long adminId = ps.savePerson(adminMock).getId();
        assertTrue(studentRepository.existsByUserId(adminId));
        ps.setUserRole(adminId, Role.ADMIN);

        promoteUser(adminId, userId, Role.STUDENT, "/student/requestPromotion");
        promoteUser(adminId, userId, Role.TEACHER, "/teacher/requestPromotion");

        assertSame(ps.getUserRole(userId), Role.ADMIN);

        Promotion promotion = new Promotion(userId, Role.STUDENT);
        promotionRepository.save(promotion);
        mockMvc.perform(MockMvcRequestBuilders.post("/admin/changeRole").contentType(MediaType.APPLICATION_JSON_VALUE).content(mapToJson(new ChangeRoleDTO(userId, true))).cookie(new Cookie("qcademy", jwtService.createToken(Role.ADMIN, adminId)))).andExpect(MockMvcResultMatchers.status().isCreated());
        assertFalse(promotionRepository.existsById(userId));
        assertSame(ps.getUserRole(userId), Role.STUDENT);
        assertTrue(teacherRepository.existsByUserId(userId));
        assertTrue(studentRepository.existsByUserId(userId));
        assertTrue(adminRepository.existsByUserId(userId));
    }


    @Test
    void testChangingToStudentWithNoStudent() throws Exception {
        Person p = new Person("Yahya", "Azzam", "sectest2131231@gmail.com", "test", "1-2-1999", "photo.jpg");
        Long userId = personRepository.save(p).getId();
        assertFalse(studentRepository.existsByUserId(userId));

        Person adminMock = new Person("admin", "admin", "admin3213123123@gmail.com", "test", "1-2-1999", "photo.jpg");
        Long adminId = ps.savePerson(adminMock).getId();
        assertTrue(studentRepository.existsByUserId(adminId));
        ps.setUserRole(adminId, Role.ADMIN);

        promoteUser(adminId, userId, Role.STUDENT, "/student/requestPromotion");
        assertSame(ps.getUserRole(userId), Role.TEACHER);

        Promotion promotion = new Promotion(userId, Role.STUDENT);
        promotionRepository.save(promotion);
        mockMvc.perform(MockMvcRequestBuilders.post("/admin/changeRole").contentType(MediaType.APPLICATION_JSON_VALUE).content(mapToJson(new ChangeRoleDTO(userId, true))).cookie(new Cookie("qcademy", jwtService.createToken(Role.ADMIN, adminId)))).andExpect(MockMvcResultMatchers.status().isCreated());
        assertFalse(promotionRepository.existsById(userId));
        assertSame(ps.getUserRole(userId), Role.STUDENT);
        assertTrue(teacherRepository.existsByUserId(userId));
        assertTrue(studentRepository.existsByUserId(userId));
        assertFalse(adminRepository.existsByUserId(userId));
    }
}
