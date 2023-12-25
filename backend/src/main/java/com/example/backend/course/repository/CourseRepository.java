package com.example.backend.course.repository;

import com.example.backend.course.model.Course;
import com.example.backend.student.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    /**
     * deletes a course with the given courseId
     *
     * @return Course that was deleted
     */
    Course deleteById (int courseId);
    /**
     * find a course with the given courseId
     *
     * @return Course that has this id
     */
    Course getByCourseId(int courseId);

    @Query("SELECT c FROM Course c WHERE c NOT IN (SELECT s.courses FROM Student s WHERE s = :student)")
    List<Course> findCoursesNotEnrolledByStudent(@Param("student") Student student);
}
