package com.example.demomodule.student;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    final private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/create")
    public ResponseEntity<StudentResponseDto> createStudent(StudentRequestDto requestDto){
        return ResponseEntity.ok(studentService.createStudent(requestDto));
    }

    @GetMapping("/get")
    public ResponseEntity<List<StudentResponseDto>> getAllStudent(){
        return ResponseEntity.ok(studentService.getAllStudent());
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteStudent(Long id){
        studentService.deleteStudent(id);
        return null;
    }
}
