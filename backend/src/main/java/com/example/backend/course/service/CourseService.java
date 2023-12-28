package com.example.backend.course.service;

import com.example.backend.course.model.Assignment;
import com.example.backend.course.model.CourseModule;
import com.example.backend.course.model.SolvedAssignment;
import com.example.backend.course.repository.AssignmentRepository;
import com.example.backend.course.repository.CourseRepository;
import com.example.backend.course.dto.CourseMainInfoDTO;
import com.example.backend.course.dto.CourseModuleDTO;
import com.example.backend.course.model.Course;
import com.example.backend.course.repository.SolvedAssignmentId;
import com.example.backend.course.repository.SolvedAssignmentRepository;
import com.example.backend.student.model.Student;
import com.example.backend.student.repository.StudentRepository;
import com.example.backend.teacher.dto.GradingDTO;
import com.example.backend.teacher.model.Teacher;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseModuleService courseModuleService;
    private final SolvedAssignmentRepository solvedAssignmentRepository;
    private final AssignmentRepository assignmentRepository;
    private final StudentRepository studentRepository;

    public Course createCourse(CourseMainInfoDTO courseMainInfoDTO, Teacher teacher) {
        Course course = Course.convert(courseMainInfoDTO);
        course.setTeacher(teacher);
        this.courseRepository.save(course);
        return course;
    }

    public Course enrollInCourse(int courseId, Student student){
        Course course = this.courseRepository.getByCourseId(courseId);
        course.getStudents().add(student);
        this.courseRepository.save(course);
        return course;
    }

    public CourseModule addModule(CourseModuleDTO courseModuleDTO){
         Course course = this.courseRepository.getByCourseId(courseModuleDTO.getCourseId());
         CourseModule courseModule = this.courseModuleService.createCourseModule(courseModuleDTO);
         course.getModules().add(courseModule);
         this.courseRepository.save(course);
         return courseModule;
    }

    public ResponseEntity<List<CourseModuleDTO>> getCourseModules(int courseId){
        Course course = this.courseRepository.getByCourseId(courseId);
        List<CourseModuleDTO> courseModulesDTO = new ArrayList<>();
        for (CourseModule courseModule : course.getModules()) {
            courseModulesDTO.add(CourseModuleDTO.convert(courseModule));
        }
        return new ResponseEntity<>(courseModulesDTO, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<List<SolvedAssignment>> getAssignmentSolutions(int assignmentNumber){
        Assignment assignment = assignmentRepository.getAssignmentByNumber(assignmentNumber);
        List<SolvedAssignment> solvedAssignments = solvedAssignmentRepository.findSolvedAssignmentsByAssignment(assignment);
        return new ResponseEntity<>(solvedAssignments, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<String> setSolvedAssignmentGrade(GradingDTO gradingDTO){
        Assignment assignment = assignmentRepository.getAssignmentByNumber(gradingDTO.getAssignmentNumber());
        Student student = studentRepository.getByUserId(gradingDTO.getStudentId());
        SolvedAssignmentId solvedAssignmentId = new SolvedAssignmentId(assignment, student);
        SolvedAssignment solvedAssignment = solvedAssignmentRepository.getReferenceById(solvedAssignmentId);
        solvedAssignment.setGrade(gradingDTO.getGrade());
        solvedAssignmentRepository.save(solvedAssignment);
        return new ResponseEntity<>("Updated assignment grade", HttpStatus.OK);
    }
}
