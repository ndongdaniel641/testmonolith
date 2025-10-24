package com.example.demomodule.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    private ModelMapper modelMapper;

    @Mock
    private ApplicationEventPublisher eventPublisher;

    @InjectMocks
    private StudentService studentService;

    // ✅ Cette méthode s’exécute AVANT chaque test
    @BeforeEach
    void setUp() {
        modelMapper = new ModelMapper(); // vrai objet
        studentService = new StudentService(studentRepository, modelMapper, eventPublisher);
    }

    @Test
    void shouldCreateStudent() {
        // given
        StudentRequestDto dto = new StudentRequestDto();
        dto.setCode("1232R");
        dto.setName("John Doe");
        Student student = new Student(1L, "John Doe", "1232R");
        StudentResponseDto responseDto = new StudentResponseDto();
        responseDto.setId(1L);
        responseDto.setName("John Doe");
        responseDto.setCode("1232R");

        when(studentRepository.save(any(Student.class))).thenReturn(student);

        StudentResponseDto created = studentService.createStudent(dto);

        assertNotNull(created);
        assertEquals("John Doe", created.getName());
        assertEquals("1232R", created.getCode());
        verify(studentRepository).save(any(Student.class));
    }

    @Test
    void shouldGetAllStudent() {
        Student student = new Student(1L, "Alice", "XYZ123");
        when(studentRepository.findAll()).thenReturn(List.of(student));

        var result = studentService.getAllStudent();

        assertEquals(1, result.size());
        assertEquals("Alice", result.get(0).getName());
    }

    @Test
    void shouldDeleteStudent() {
        // given
        Long studentId = 1L;

        // when
        studentService.deleteStudent(studentId);

        // then
        verify(studentRepository).deleteById(studentId);
    }


}
