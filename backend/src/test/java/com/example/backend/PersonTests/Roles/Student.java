package com.example.backend.PersonTests.Roles;

import com.example.backend.admin.model.Admin;
import com.example.backend.admin.repository.AdminRepository;
import com.example.backend.person.model.Person;
import com.example.backend.person.model.Role;
import com.example.backend.person.repository.PersonRepository;
import com.example.backend.person.service.PersonService;
import com.example.backend.promotion.repository.PromotionRepository;
import com.example.backend.services.CookiesService;
import com.example.backend.services.JwtService;
import com.example.backend.student.repository.StudentRepository;
import com.example.backend.student.service.StudentService;
import com.example.backend.teacher.repository.TeacherRepository;
import jakarta.servlet.http.Cookie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.annotation.CrossOrigin;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@CrossOrigin(allowCredentials = "true", origins = "")
@AutoConfigureMockMvc
public class Student {

    private final BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
    @Autowired
    private PersonService ps;
    @Autowired
    private PersonRepository pr;

    @Autowired
    private StudentService studentService;

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
    private String secretKey = new StandardEnvironment().getProperty("QcademyAuthKey");

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testStudentCreation() throws Exception {
        pr.deleteAll();
        studentRepository.deleteAll();
        Person p = new Person("Yahya", "Azzam", "sectest11@gmail.com", "test", "1-2-1999", "photo.jpg");
        Long userId = ps.savePerson(p).getId();

        assertTrue(pr.existsById(userId));
        assertTrue(studentRepository.existsByUserId(userId));
    }


    @Test
    void testStudentPromotion() throws Exception {
        pr.deleteAll();
        studentRepository.deleteAll();
        promotionRepository.deleteAll();

        Person p = new Person("Yahya", "Azzam", "sectest11@gmail.com", "test", "1-2-1999", "photo.jpg");
        Long userId = ps.savePerson(p).getId();
        assertTrue(studentRepository.existsByUserId(userId));

        mockMvc.perform(MockMvcRequestBuilders.post("/student/requestPromotion").cookie(new Cookie("qcademy", jwtService.createToken(Role.STUDENT, userId))))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        assertTrue(promotionRepository.existsById(userId));
    }

    @Test
    void testStudentPromotion() throws Exception {
        pr.deleteAll();
        studentRepository.deleteAll();
        promotionRepository.deleteAll();

        Person p = new Person("Yahya", "Azzam", "sectest11@gmail.com", "test", "1-2-1999", "photo.jpg");
        Long userId = ps.savePerson(p).getId();
        assertTrue(studentRepository.existsByUserId(userId));

        mockMvc.perform(MockMvcRequestBuilders.post("/student/requestPromotion").cookie(new Cookie("qcademy", jwtService.createToken(Role.STUDENT, userId))))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        assertTrue(promotionRepository.existsById(userId));
    }


    @Test
    void testPromotingStudent() throws Exception {
        pr.deleteAll();
        studentRepository.deleteAll();
        promotionRepository.deleteAll();

        Person p = new Person("Yahya", "Azzam", "sectest11@gmail.com", "test", "1-2-1999", "photo.jpg");
        Long userId = ps.savePerson(p).getId();
        assertTrue(studentRepository.existsByUserId(userId));

        Person adminMock = new Person("admin", "admin", "admin@gmail.com", "test", "1-2-1999", "photo.jpg");
        Long adminId = ps.savePerson(adminMock).getId();
        assertTrue(studentRepository.existsByUserId(adminId));
        ps.setUserRole(adminId, Role.ADMIN);


        mockMvc.perform(MockMvcRequestBuilders.post("/student/requestPromotion").cookie(new Cookie("qcademy", jwtService.createToken(Role.STUDENT, userId))))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        assertTrue(promotionRepository.existsById(userId));


        mockMvc.perform(MockMvcRequestBuilders.post("/admin/changeRole").content(String.valueOf(userId)).cookie(new Cookie("qcademy", jwtService.createToken(Role.ADMIN, adminId))))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        assertFalse(promotionRepository.existsById(userId));
        assertSame(ps.getUserRole(userId), Role.TEACHER);
        assertTrue(teacherRepository.existsByUserId(userId));
    }



}

