package com.example.demomodule.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class StudentControllerTest {

    private MockMvc mockMvc;

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
    }

    @Test
    void createStudent() throws Exception {
        // given
        StudentRequestDto requestDto = new StudentRequestDto();
        requestDto.setName("John Doe");
        requestDto.setCode("1232R");

        StudentResponseDto responseDto = new StudentResponseDto();
        responseDto.setId(1L);
        responseDto.setName("John Doe");
        responseDto.setCode("1232R");

        when(studentService.createStudent(any(StudentRequestDto.class))).thenReturn(responseDto);

        // when + then
        mockMvc.perform(post("/student/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"John Doe\",\"code\":\"1232R\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.code").value("1232R"));

        verify(studentService, times(1)).createStudent(any(StudentRequestDto.class));
    }

    @Test
    void getAllStudent() throws Exception {
        // given

        StudentResponseDto student1 = new StudentResponseDto();
        student1.setId(1L);
        student1.setName("Alice");
        student1.setCode("A123");

        StudentResponseDto student2 = new StudentResponseDto();
        student2.setId(1L);
        student2.setName("Bob");
        student2.setCode("B456");

        when(studentService.getAllStudent()).thenReturn(List.of(student1, student2));

        // when + then
        mockMvc.perform(get("/student/get"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Alice"))
                .andExpect(jsonPath("$[1].code").value("B456"));

        verify(studentService, times(1)).getAllStudent();
    }

    @Test
    void deleteStudent() throws Exception {
        // given
        Long studentId = 1L;
        doNothing().when(studentService).deleteStudent(studentId);

        // when + then
        mockMvc.perform(delete("/student/delete")
                        .param("id", studentId.toString()))
                .andExpect(status().isOk());

        verify(studentService, times(1)).deleteStudent(studentId);
    }
}
