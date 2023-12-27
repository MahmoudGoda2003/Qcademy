package com.example.backend.admin.service;

import com.example.backend.exceptions.exception.NoPromotionRequestedException;
import com.example.backend.promotion.dto.PromotionDTO;
import com.example.backend.admin.model.Admin;
import com.example.backend.admin.repository.AdminRepository;
import com.example.backend.person.service.PersonService;
import com.example.backend.promotion.model.Promotion;
import com.example.backend.promotion.service.PromotionService;
import com.example.backend.student.model.Student;
import com.example.backend.student.repository.StudentRepository;
import com.example.backend.teacher.model.Teacher;
import com.example.backend.teacher.repository.TeacherRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final PromotionService promotionService;
    private final PersonService personService;

    @Autowired
    public AdminService(AdminRepository adminRepository, TeacherRepository teacherRepository, StudentRepository studentRepository, PromotionService promotionService, PersonService personService) {
        this.adminRepository = adminRepository;
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
        this.promotionService = promotionService;
        this.personService = personService;
    }

    @Transactional
    public ResponseEntity<String> changePersonRole(Long userId, boolean status){
        Promotion promotion = promotionService.getAndDeletePromotion(userId);
        if(promotion == null)
            throw new NoPromotionRequestedException();
        if(!status)
            return new ResponseEntity<>("Request refused successfully", HttpStatus.CREATED);
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
        }
        return new ResponseEntity<>("Role changing completed", HttpStatus.CREATED);
    }

    private void addAdmin(Long id) {
        Admin admin = new Admin(id);
        adminRepository.save(admin);
    }

    @Transactional
    public ResponseEntity<List<PromotionDTO>> getPromotionRequests(){
        return new ResponseEntity<>(this.promotionService.getPromotionRequests(), HttpStatus.OK);
    }
}
