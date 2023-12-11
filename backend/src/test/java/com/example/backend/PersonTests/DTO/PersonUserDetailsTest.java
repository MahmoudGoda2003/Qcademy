package com.example.backend.PersonTests.DTO;
import com.example.backend.person.dto.PersonUserDetails;
import com.example.backend.person.model.Role;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PersonUserDetailsTest {

    @Test
    void getAuthorities() {
        PersonUserDetails userDetails = new PersonUserDetails(1L, Role.STUDENT);
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

        assertEquals(1, authorities.size());
        assertTrue(authorities.contains(new SimpleGrantedAuthority(Role.STUDENT.name())));
    }

    @Test
    void getPassword() {
        PersonUserDetails userDetails = new PersonUserDetails(1L, Role.STUDENT);
        assertEquals("", userDetails.getPassword());
    }

    @Test
    void getUsername() {
        PersonUserDetails userDetails = new PersonUserDetails(1L, Role.STUDENT);
        assertEquals("1", userDetails.getUsername());
    }

    @Test
    void isAccountNonExpired() {
        PersonUserDetails userDetails = new PersonUserDetails(1L, Role.STUDENT);
        assertTrue(userDetails.isAccountNonExpired());
    }

    @Test
    void isAccountNonLocked() {
        PersonUserDetails userDetails = new PersonUserDetails(1L, Role.STUDENT);
        assertTrue(userDetails.isAccountNonLocked());
    }

    @Test
    void isCredentialsNonExpired() {
        PersonUserDetails userDetails = new PersonUserDetails(1L, Role.STUDENT);
        assertTrue(userDetails.isCredentialsNonExpired());
    }

    @Test
    void isEnabled() {
        PersonUserDetails userDetails = new PersonUserDetails(1L, Role.STUDENT);
        assertTrue(userDetails.isEnabled());
    }
}