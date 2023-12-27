//package com.example.backend.PersonTests.Security;
//
//import com.example.backend.person.model.Role;
//import com.example.backend.services.JwtService;
//import io.jsonwebtoken.Claims;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.Date;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class JwtServiceTest {
//
//
//    @Mock
//    private Claims claims;
//
//    @InjectMocks
//    private JwtService jwtService;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void createToken() {
//        // Replace with your actual test data
//        Role role = Role.ADMIN;
//        Long id = 1L;
//
//        String token = jwtService.createToken(role, id);
//
//        assertNotNull(token);
//        // Add more assertions as needed
//    }
//
//    @Test
//    void isTokenValid() {
//        // Replace with your actual test data
//        String token = jwtService.createToken(Role.STUDENT, 123L);
//        Role actualRole = Role.STUDENT;
//
//        Mockito.when(claims.getSubject()).thenReturn(actualRole.name());
//        Mockito.when(claims.getExpiration()).thenReturn(new Date(System.currentTimeMillis() + 1000 * 60 * 24));
//
//        boolean isValid = jwtService.isTokenValid(token, actualRole);
//
//        assertTrue(isValid);
//        // Add more assertions as needed
//    }
//
//    @Test
//    void extractUserId() {
//        // Replace with your actual test data
//        String token = jwtService.createToken(Role.STUDENT, 123L);
//        Long expectedUserId = 123L;
//
//        Mockito.when(claims.getId()).thenReturn(expectedUserId.toString());
//
//        Long userId = jwtService.extractUserId(token);
//
//        assertEquals(expectedUserId, userId);
//        // Add more assertions as needed
//    }
//
//}