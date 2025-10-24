package com.example.demomodule.student;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;

    @Test
    void shouldFindAllStudents() {
        List<Student> students = studentRepository.findAll();

        Assertions.assertNotNull(students);
        Long idStudent = students.get(0).getId();
        Assertions.assertNotNull(idStudent);
        Assertions.assertEquals(1, students.get(0).getId());
    }

    @Test
    void shouldFindStudentById() {
        Student student = studentRepository.findById(1L).get();
        Assertions.assertNotNull(student);
        Assertions.assertEquals("Alice Dupont",  student.getName());
        Assertions.assertEquals("STU001",  student.getCode());
    }

    @Test
    void shouldFindStudentByName() {
        Student student = studentRepository.findByName("Alice Dupont");
        Assertions.assertNotNull(student);
        Assertions.assertEquals("Alice Dupont",  student.getName());
        Assertions.assertEquals("STU001",  student.getCode());
    }

    @Test
    void shouldCreateStudent() {
        Student student = new Student();
        student.setName("Dupont");
        student.setCode("STU020");
        studentRepository.save(student);
        Assertions.assertNotNull(studentRepository.findByName("Dupont"));
    }

    @Test
    void shouldUpdateStudent() {
        Student student = studentRepository.findById(1L).get();
        student.setName("Alice Dupont Marine");
        student.setCode("STU001");
        studentRepository.save(student);
        Assertions.assertNotNull(studentRepository.findByName("Alice Dupont Marine"));
        Assertions.assertEquals("Alice Dupont Marine",  student.getName());
        Assertions.assertEquals("STU001",  student.getCode());
    }

    @Test
    void shouldDeleteStudent() {
        Student student = studentRepository.findById(1L).get();
        studentRepository.delete(student);
        Assertions.assertNull(studentRepository.findByName("Alice Dupont"));
    }

}