package com.example.backend.admin.service;

import com.example.backend.admin.dto.ChangeRoleDTO;
import com.example.backend.admin.model.Admin;
import com.example.backend.admin.repository.AdminRepository;
import com.example.backend.person.repository.PersonRepository;
import com.example.backend.person.service.PersonService;
import com.example.backend.promotion.model.Promotion;
import com.example.backend.promotion.service.PromotionService;
import com.example.backend.student.model.Student;
import com.example.backend.student.repository.StudentRepository;
import com.example.backend.teacher.model.Teacher;
import com.example.backend.teacher.repository.TeacherRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final PromotionService promotionService;
    private final PersonService personService;
    public AdminService(AdminRepository adminRepository, TeacherRepository teacherRepository, StudentRepository studentRepository, PromotionService promotionService, PersonService personService) {
        this.adminRepository = adminRepository;
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
        this.promotionService = promotionService;
        this.personService = personService;
    }

    @Transactional
    public ResponseEntity<String> changePersonRole(Long userId){
        Promotion promotion = promotionService.getAndDeletePromotion(userId);
        personService.setUserRole(userId, promotion.getRole());
        switch (promotion.getRole()) {
            case TEACHER -> {
                if (teacherRepository.existsByUserId(userId))
                    break;
                Teacher teacher = new Teacher(userId);
                teacherRepository.save(teacher);
            }
            case ADMIN -> {
                if (adminRepository.existsByUserId(userId))
                    break;
                Admin admin = new Admin(userId);
                adminRepository.save(admin);
            }
            case STUDENT -> {
                if (studentRepository.existsByUserId(userId))
                    break;
                Student student = new Student(userId);
                studentRepository.save(student);
            }
            default -> throw new RuntimeException("Role doesn't exist");
        }
        return new ResponseEntity<>("Role changing completed", HttpStatus.CREATED);
    }



}
