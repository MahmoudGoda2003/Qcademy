package com.example.backend.PersonTests;

import com.example.backend.exceptions.exception.DataNotFoundException;
import com.example.backend.exceptions.exception.LoginDataNotValidException;
import com.example.backend.exceptions.exception.WrongDataEnteredException;
import com.example.backend.person.dto.SignUpDTO;
import com.example.backend.person.model.Person;
import com.example.backend.person.model.Role;
import com.example.backend.person.repository.PersonRepository;
import com.example.backend.person.service.PersonService;
import com.example.backend.services.CookiesService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.Cookie;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class PersonServiceTest {
    @Autowired
    private PersonService ps;

    @Autowired
    private PersonRepository pr;

    private final BCryptPasswordEncoder encode = new BCryptPasswordEncoder();

    @Autowired
    private CookiesService cookiesService;

    private final String secretKey = new StandardEnvironment().getProperty("QcademyAuthKey");

    @BeforeEach
    public void setup() {
        pr.deleteAll();
    }


    @Test
    void LoginTestNormal() throws Exception {
        String pass = this.encode.encode("test");
        Person p = new Person("Yahya", "Azzam", "test1@gmail.com", pass, "1-2-1999", "photo.jpg");
        pr.save(p);

        MockHttpServletResponse httpResponse = new MockHttpServletResponse();
        ps.login(httpResponse, "test1@gmail.com", "test");
        assertEquals(HttpStatus.OK.value(), httpResponse.getStatus());
    }

    @Test
    void LoginTestWrongPassword() {
        String pass = encode.encode("test");
        Person p = new Person("Yahya", "Azzam", "test1@gmail.com", pass, "1-2-1999", "photo.jpg");
        pr.save(p);
        MockHttpServletResponse httpResponse = new MockHttpServletResponse();
        assertThrowsExactly(LoginDataNotValidException.class, () -> ps.login(httpResponse, "test1@gmail.com", "12345679"));
    }

    @Test
    void LoginTestWrongEmail() {
        // wrong email, or email doesn't exist
        MockHttpServletResponse httpResponse = new MockHttpServletResponse();
        assertThrowsExactly(LoginDataNotValidException.class, () -> ps.login(httpResponse, "test1@gmail.com", "12345679"));
    }

    @Test
    void SendValidationCodeNormal() throws Exception {
        MockHttpServletResponse httpResponse = new MockHttpServletResponse();
        ps.sendValidationCode(httpResponse, "test1@gmail.com", "123456");

        assertEquals(HttpStatus.OK.value(), httpResponse.getStatus());

        Cookie[] cookies = httpResponse.getCookies();

        assertNotNull(cookies);
        assertTrue(cookies.length > 0);
    }

    @Test
    void SendValidationCodeToExistEmail() throws MessagingException {
        Person p = new Person("Yahya", "Azzam", "test1@gmail.com", "123456", "1-2-1999", "photo.jpg");
        pr.save(p);
        MockHttpServletResponse httpResponse = new MockHttpServletResponse();
        assertThrowsExactly(WrongDataEnteredException.class, () -> ps.sendValidationCode(httpResponse, "test1@gmail.com", "123456"));
    }

    @Test
    void SendValidationCodeToNullEmail() throws MessagingException {
        MockHttpServletResponse httpResponse = new MockHttpServletResponse();
        assertThrowsExactly(WrongDataEnteredException.class, () -> ps.sendValidationCode(httpResponse, null, "123456"));
    }

    @Test
    void SendValidationCodeToEmptyEmail() throws MessagingException {
        MockHttpServletResponse httpResponse = new MockHttpServletResponse();
        assertThrowsExactly(WrongDataEnteredException.class, () -> ps.sendValidationCode(httpResponse, "", "123456"));
    }

    @Test
    void SendValidationCodeToWrongEmail() throws Exception {
        MockHttpServletResponse httpResponse = new MockHttpServletResponse();
        assertThrowsExactly(StringIndexOutOfBoundsException.class, () -> ps.sendValidationCode(httpResponse, "test1ahemd", "123456"));
    }

    @Test
    void ValidateOTPNormal() throws Exception {
        SignUpDTO signUpDTO = new SignUpDTO("Yahya", "Azzam", "test1@gmail.com", "test", "1-2-1999", "201356");
        String encodedValidationCode = this.cookiesService.hashCode(signUpDTO.getCode() + signUpDTO.getEmail() + this.secretKey);
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setCookies(new Cookie("validationCode", encodedValidationCode));
        ResponseEntity<String> response = ps.validateOTP(request, signUpDTO);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("SignUp completed", response.getBody());
    }

    @Test
    void ValidateExistEmail() throws Exception {
        Person p = new Person("Yahya", "Azzam", "test1@gmail.com", "123456", "1-2-1999", "photo.jpg");
        pr.save(p);
        SignUpDTO signUpDTO = new SignUpDTO("Yahya", "Azzam", "test1@gmail.com", "test", "1-2-1999", "201356");
        String encodedValidationCode = this.cookiesService.hashCode(signUpDTO.getCode() + signUpDTO.getEmail() + this.secretKey);
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setCookies(new Cookie("validationCode", encodedValidationCode));
        assertThrowsExactly(WrongDataEnteredException.class, () -> ps.validateOTP(request, signUpDTO));
    }

    @Test
    void ValidateOTPWithNullCookie() {
        SignUpDTO signUpDTO = new SignUpDTO("Yahya", "Azzam", "test1@gmail.com", "test", "1-2-1999", "201356");
        MockHttpServletRequest request = new MockHttpServletRequest();
        assertThrowsExactly(DataNotFoundException.class, () -> ps.validateOTP(request, signUpDTO));
    }

    @Test
    void ValidateOTPWithWrongCode() throws Exception {
        SignUpDTO signUpDTO = new SignUpDTO("Yahya", "Azzam", "test1@gmail.com", "test", "1-2-1999", "201356");
        String encodedValidationCode = this.cookiesService.hashCode("21341235" + signUpDTO.getEmail() + this.secretKey);
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setCookies(new Cookie("validationCode", encodedValidationCode));
        assertThrowsExactly(WrongDataEnteredException.class, () -> ps.validateOTP(request, signUpDTO));
    }

    @Test
    void testCreatingPersonFromGoogleObject() throws JSONException {
        JSONObject sampleObject = new JSONObject();
        sampleObject.put("given_name", "John");
        sampleObject.put("family_name", "Doe");
        sampleObject.put("email", "john.doe@example.com");
        sampleObject.put("picture", "https://example.com/picture.jpg");
        sampleObject.put("id", "123456789");
        sampleObject.put("password", "test");
        Person person = new Person(sampleObject);

        assertEquals("John", person.getFirstName());
        assertEquals("Doe", person.getLastName());
        assertEquals("john.doe@example.com", person.getEmail());
        assertEquals("https://example.com/picture.jpg", person.getPhotoLink());
        assertEquals("test", person.getPassword());
    }

    @Test
    void getUserRole() {
        String pass = encode.encode("test");
        Person p = new Person("Yahya", "Azzam", "test1234@gmail.com", pass, "1-2-1999", "photo.jpg");
        Long userId = pr.save(p).getId();
        assertEquals(Role.STUDENT, ps.getUserRole(userId));
    }

    @Test
    void changeUserRole() {
        String pass = encode.encode("test");
        Person p = new Person("Yahya", "Azzam", "test1231234124@gmail.com", pass, "1-2-1999", "photo.jpg");
        Long userId = pr.save(p).getId();
        ps.setUserRole(userId, Role.TEACHER);
        assertEquals(Role.TEACHER, ps.getUserRole(userId));
    }
}