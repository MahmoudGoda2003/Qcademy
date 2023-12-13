package com.example.backend.course.courseModule.repository;

import com.example.backend.course.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    /**
     * deletes a course with the given courseId
     *
     * @return Course that was deleted
     */
    Course deleteById (int courseId);
}
