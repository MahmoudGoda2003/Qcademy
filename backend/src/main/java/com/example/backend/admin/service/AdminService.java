package com.example.backend.admin.service;

import com.example.backend.admin.model.Admin;
import com.example.backend.admin.repository.AdminRepository;
import com.example.backend.person.model.Role;
import com.example.backend.person.repository.PersonRepository;
import com.example.backend.person.service.PersonService;
import com.example.backend.teacher.service.TeacherService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final PersonRepository personRepository;
    private final TeacherService teacherService;
    private final PersonService personService;
    public AdminService(AdminRepository aR, PersonRepository pR, PersonService pS, TeacherService tS) {
        this.adminRepository = aR;
        this.personRepository = pR;
        this.personService = pS;
        this.teacherService = tS;
    }

    @Transactional
    public ResponseEntity<String> changePersonRole(Long userId, Role role){
        personService.setUserRole(userId, role);
        switch (role){
            case TEACHER:
                teacherService.addTeacher(userId);
                break;
            case ADMIN:
                addAdmin(userId);
                break;
            case STUDENT:
                //Todo: add adding new student logic (check if student exists, if not add new entry, if yes then do nothing)
                break;
            default:
        }
        return new ResponseEntity<>("Role changing completed", HttpStatus.CREATED);
    }

    private void addAdmin(Long id) {
        Admin admin = new Admin(id);
        adminRepository.save(admin);
    }
}
