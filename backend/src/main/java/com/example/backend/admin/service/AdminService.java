package com.example.backend.admin.service;

import com.example.backend.admin.repository.AdminRepository;
import com.example.backend.person.model.Role;
import com.example.backend.person.repository.PersonRepository;
import com.example.backend.person.service.PersonService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final PersonRepository personRepository;
    private final PersonService personService;
    public AdminService(AdminRepository adminRepository, PersonRepository personRepository, PersonService personService) {
        this.adminRepository = adminRepository;
        this.personRepository = personRepository;
        this.personService = personService;
    }

    @Transactional
    public ResponseEntity<String> changePersonRole(Long userId, Role role){
        personService.setUserRole(userId, role);
        switch (role){
            case TEACHER:
                //Todo: add adding new teacher logic (check if teacher exists, if not add new entry, if yes then do nothing)
                break;
            case ADMIN:
                //Todo: add adding new admin logic (check if admin exists, if not add new entry, if yes then do nothing)
                break;
            case STUDENT:
                //Todo: add adding new student logic (check if student exists, if not add new entry, if yes then do nothing)
                break;
            default:
        }
        return new ResponseEntity<>("Role changing completed", HttpStatus.CREATED);
    }
}
