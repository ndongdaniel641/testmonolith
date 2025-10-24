package com.example.demomodule.student;

import com.example.demomodule.notification.HistoryNotification;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    final private static Logger LOGGER = LoggerFactory.getLogger(StudentService.class);

    final private StudentRepository repository;
    final private ModelMapper modelMapper;
    final private ApplicationEventPublisher eventPublisher;

    public StudentService(StudentRepository repository, ModelMapper modelMapper, ApplicationEventPublisher eventPublisher) {
        this.repository = repository;
        this.modelMapper = modelMapper;
        this.eventPublisher = eventPublisher;
    }

    public StudentResponseDto createStudent(StudentRequestDto dto){
        LOGGER.info("creating of student");
        Student student = modelMapper.map(dto, Student.class);
        repository.save(student);
        eventPublisher.publishEvent(new HistoryNotification("student", student.getId(), "Creating Student", "creating of only one student"));
        return modelMapper.map(student, StudentResponseDto.class);
    }

    public List<StudentResponseDto> getAllStudent(){
        List<Student> students = repository.findAll();
        List<StudentResponseDto> responseDtoList;
        responseDtoList = modelMapper.map(
                students, new TypeToken<List<StudentResponseDto>>() {}.getType());
        return responseDtoList;
    }

    public void deleteStudent(Long id){
        LOGGER.info("deleting of Student {}", id);
        repository.deleteById(id);
    }
}
