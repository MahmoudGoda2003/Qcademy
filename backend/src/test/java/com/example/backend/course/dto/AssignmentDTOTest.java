package com.example.backend.course.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.backend.course.model.Assignment;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class AssignmentDTOTest {

    @Test
    void testConvert() {

        Assignment assignment = new Assignment();
        assignment.setAssignmentURL("https://example.org/example");
        assignment.setName("Name");
        assignment.setSolvedAssignment(new ArrayList<>());

        AssignmentDTO actualConvertResult = AssignmentDTO.convert(assignment);

        assertEquals("Name", actualConvertResult.getName());
        assertEquals("https://example.org/example", actualConvertResult.getAssignmentURL());
        assertEquals((short) 0, actualConvertResult.getNumber());
    }

    @Test
    void testConvert2() {
        Assignment assignment = new Assignment();
        assignment.setAssignmentURL("Assignment URL");
        assignment.setName("Name");
        assignment.setSolvedAssignment(new ArrayList<>());

        AssignmentDTO actualConvertResult = AssignmentDTO.convert(assignment);

        assertEquals("Assignment URL", actualConvertResult.getAssignmentURL());
        assertEquals("Name", actualConvertResult.getName());
        assertEquals((short) 0, actualConvertResult.getNumber());
    }

    @Test
    void testConvert3() {
        Assignment assignment = new Assignment();
        assignment.setAssignmentURL("com.example.backend.course.model.Assignment");
        assignment.setName("com.example.backend.course.model.Assignment");
        assignment.setSolvedAssignment(new ArrayList<>());

        AssignmentDTO actualConvertResult = AssignmentDTO.convert(assignment);

        assertEquals("com.example.backend.course.model.Assignment", actualConvertResult.getAssignmentURL());
        assertEquals("com.example.backend.course.model.Assignment", actualConvertResult.getName());
        assertEquals((short) 0, actualConvertResult.getNumber());
    }
}
