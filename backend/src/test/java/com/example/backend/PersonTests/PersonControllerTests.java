package com.example.backend.PersonTests;

import com.example.backend.AbstractTest;
import com.example.backend.person.PersonController;
import com.example.backend.person.dto.LoginDTO;
import com.example.backend.person.dto.PersonMainInfoDTO;
import com.example.backend.person.dto.SignUpDTO;
import com.example.backend.person.model.Person;
import com.example.backend.person.repository.PersonRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

@SpringBootTest
@ActiveProfiles("test")
public class PersonControllerTests extends AbstractTest {

    private final BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
    @Autowired
    private PersonController pc;
    @Autowired
    private PersonRepository pr;

    @Override
    @Before
    public void setup() {
        super.setup();
        pr.deleteAll();
    }

    @Test
    public void signUpNormal() throws Exception {
        String uri = "/person/signup", email = "yahya912azzam@gmail.com";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.TEXT_PLAIN).content(email)).andReturn();
        assertEquals(HttpStatus.ACCEPTED.value(), mvcResult.getResponse().getStatus());
        assertEquals("Validation code sent", mvcResult.getResponse().getContentAsString());
    }


    @Test
    public void signUpAlreadySignedUp() throws Exception {
        Person p = new Person("Yahya", "Azzam", "tee2132tstst@gmail.com", "123456789", "1-2-1999", "photo.jpg");
        pr.save(p);
        String uri = "/person/signup", email = "tee2132tstst@gmail.com";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.TEXT_PLAIN).content(email)).andReturn();
        assertEquals(HttpStatus.NOT_ACCEPTABLE.value(), mvcResult.getResponse().getStatus());
        assertEquals("{\"error message\":\"Email already exists\"}", mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void signUpWithInvalidEmail() throws Exception {
        String uri = "/person/signup", email = "yahya912azzam";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.TEXT_PLAIN).content(email)).andReturn();
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), mvcResult.getResponse().getStatus());
        assertEquals("{\"error message\":\"Internal Server Error\"}", mvcResult.getResponse().getContentAsString());
    }


    @Test
    public void singUp_method() throws Exception {
        HttpServletResponse response = mock(HttpServletResponse.class);
        ResponseEntity<String> request = pc.signUp(response, "test1@gmail.com");
        assertEquals(HttpStatus.ACCEPTED, request.getStatusCode());
        assertEquals("Validation code sent", request.getBody());
    }

    @Test
    public void validateOTPNormal() throws Exception {
        String uri = "/person/signup/validate";
        SignUpDTO signUpDTO = new SignUpDTO("Yahya", "Azzam", "test1@gmail.com", "test", "01-02-1999", "201356");

        String encodedValidationCode = encode.encode(signUpDTO.getCode() + signUpDTO.getEmail());

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(super.mapToJson(signUpDTO))
                .cookie(new Cookie("validationCode", encodedValidationCode));

        MvcResult mvcResult = mvc.perform(requestBuilder).andReturn();

        assertEquals(HttpStatus.CREATED.value(), mvcResult.getResponse().getStatus());
        assertEquals("SignUp completed", mvcResult.getResponse().getContentAsString());
    }


    @Test
    public void validateWithoutSignUp() throws Exception {
        String uri = "/person/signup/validate";
        SignUpDTO signUpDTO = new SignUpDTO("Yahya", "Azzam", "test@test.com", "test", "01-02-2002", "201356");
        String input = super.mapToJson(signUpDTO);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(input)).andReturn();
        assertEquals(HttpStatus.NOT_FOUND.value(), mvcResult.getResponse().getStatus());
        assertEquals("{\"error message\":\"Try to sign up again\"}", mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void validateAlreadyExistUser() throws Exception {
        Person p = new Person("Yahya", "Azzam", "test1@gmail.com", "123456789", "01-02-1999", "photo.jpg");
        pr.save(p);
        String uri = "/person/signup/validate";
        SignUpDTO signUpDTO = new SignUpDTO("Yahya", "Azzam", "test1@gmail.com", "test", "01-02-1999", "201356");
        String input = super.mapToJson(signUpDTO);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(input)).andReturn();
        assertEquals(HttpStatus.NOT_ACCEPTABLE.value(), mvcResult.getResponse().getStatus());
        assertEquals("{\"error message\":\"Email already exists\"}", mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void validateWrongCode() throws Exception {
        String uri = "/person/signup/validate";
        SignUpDTO signUpDTO = new SignUpDTO("Yahya", "Azzam", "test1@gmail.com", "test", "01-02-1999", "201356");

        String encodedValidationCode = encode.encode("123456" + signUpDTO.getEmail());

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(super.mapToJson(signUpDTO))
                .cookie(new Cookie("validationCode", encodedValidationCode));

        MvcResult mvcResult = mvc.perform(requestBuilder).andReturn();

        assertEquals(HttpStatus.NOT_ACCEPTABLE.value(), mvcResult.getResponse().getStatus());
        assertEquals("{\"error message\":\"Wrong code, please try again\"}", mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void ValidateOtp_method() {
        SignUpDTO signUpDTO = new SignUpDTO("Yahya", "Azzam", "test1@gmail.com", "test", "01-02-1999", "201356");
        String encodedValidationCode = encode.encode(signUpDTO.getCode() + signUpDTO.getEmail());
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setCookies(new Cookie("validationCode", encodedValidationCode));

        ResponseEntity<String> response = pc.validateOTP(request, signUpDTO);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("SignUp completed", response.getBody());
    }

    @Test
    public void loginNormal() throws Exception {
        String pass = encode.encode("test");
        Person p = new Person("Yahya", "Azzam", "test1@gmail.com", pass, "01-02-1999", "photo.jpg");
        pr.save(p);
        String uri = "/person/login";
        LoginDTO loginDTO = new LoginDTO("test1@gmail.com", "test");
        String input = super.mapToJson(loginDTO);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(input)).andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        assertEquals(HttpStatus.ACCEPTED.value(), response.getStatus());
        assertEquals("{\"firstName\":\"Yahya\",\"lastName\":\"Azzam\",\"email\":\"test1@gmail.com\",\"photoLink\":\"photo.jpg\"}", response.getContentAsString());
    }

    @Test
    public void loginWrongPassword() throws Exception {
        String pass = encode.encode("test");
        Person p = new Person("Yahya", "Azzam", "test1@gmail.com", pass, "01-02-1999", "photo.jpg");
        pr.save(p);
        String uri = "/person/login";
        LoginDTO loginDTO = new LoginDTO("test1@gmail.com", "wrongPassword");
        String input = super.mapToJson(loginDTO);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(input)).andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        assertEquals(HttpStatus.NOT_ACCEPTABLE.value(), response.getStatus());
        assertEquals("{\"error message\":\"password or email isn't valid\"}", response.getContentAsString());
    }

    @Test
    public void loginWrongEmail() throws Exception {
        String pass = encode.encode("test");
        Person p = new Person("Yahya", "Azzam", "test1@gmail.com", pass, "01-02-1999", "photo.jpg");
        pr.save(p);
        String uri = "/person/login";
        LoginDTO loginDTO = new LoginDTO("test123@gmail.com", "test");

        String input = super.mapToJson(loginDTO);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(input)).andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        assertEquals(HttpStatus.NOT_ACCEPTABLE.value(), response.getStatus());
        assertEquals("{\"error message\":\"password or email isn't valid\"}", response.getContentAsString());
    }

    @Test
    public void login_method() {
        String pass = encode.encode("test");
        Person p = new Person("Yahya", "Azzam", "test1@gmail.com", pass, "01-02-1999", "photo.jpg");
        pr.save(p);
        LoginDTO loginDTO = new LoginDTO("test1@gmail.com", "test");

        MockHttpServletResponse httpResponse = new MockHttpServletResponse();
        ResponseEntity<PersonMainInfoDTO> response = pc.logIn(httpResponse, loginDTO);

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals("Yahya", response.getBody().getFirstName());
        assertEquals("Azzam", response.getBody().getLastName());
        assertEquals("test1@gmail.com", response.getBody().getEmail());
        assertEquals("photo.jpg", response.getBody().getPhotoLink());
    }

    //TODO: need to test GoogleSignIn
//    @Test need to know how to test this
//    public void testGoogleSignIn() throws Exception {
//        String accessToken = "your_dummy_access_token";
//
//        // Mock the behavior of the personService.signInUsingGoogle method
//        PersonInfoDTO personInfoDTO = new PersonInfoDTO();
//        personInfoDTO.setFirstName("Yahya");
//        personInfoDTO.setLastName("Azzam");
//        personInfoDTO.setEmail("test@gmail.com");
//        personInfoDTO.setPhotoLink("photo.jpg");
//        personInfoDTO.setBio("Some bio");
//        personInfoDTO.setDateOfBirth("1-2-1999");
//
//        Mockito.when(pc.googleSignIn(Mockito.any(), Mockito.eq(accessToken)))
//                .thenReturn(ResponseEntity.ok(personInfoDTO));
//
//        // Create a MockHttpServletRequest
//        MockHttpServletRequest request = new MockHttpServletRequest();
//        request.setContentType(MediaType.APPLICATION_JSON_VALUE);
//        request.setContent(accessToken.getBytes());
//
//        // Perform the mock request
//        MockHttpServletResponse response = new MockHttpServletResponse();
//        ResponseEntity<PersonInfoDTO> responseEntity = pc.googleSignIn(response, accessToken);
//
//        // Assertions on the responseEntity
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertEquals("Yahya", responseEntity.getBody().getFirstName());
//        assertEquals("Azzam", responseEntity.getBody().getLastName());
//        assertEquals("test@gmail.com", responseEntity.getBody().getEmail());
//        assertEquals("photo.jpg", responseEntity.getBody().getPhotoLink());
//        assertEquals("Some bio", responseEntity.getBody().getBio());
//        assertEquals("1-2-1999", responseEntity.getBody().getDateOfBirth());
//
//        // Assertions on the HttpServletResponse
//        assertEquals(HttpStatus.OK.value(), response.getStatus());
//        assertEquals("{\"firstName\":\"Yahya\",\"lastName\":\"Azzam\",\"email\":\"test@gmail.com\",\"photoLink\":\"photo.jpg\",\"bio\":\"Some bio\",\"dateOfBirth\":\"1-2-1999\"}", response.getContentAsString());
//    }
}
