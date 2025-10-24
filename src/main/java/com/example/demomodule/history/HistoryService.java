package com.example.demomodule.history;

import com.example.demomodule.notification.HistoryNotification;
import com.example.demomodule.student.Student;
import com.example.demomodule.student.StudentRequestDto;
import com.example.demomodule.student.StudentResponseDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryService {
    final private HistoryRepository repository;
    final private ModelMapper modelMapper;
    final private ApplicationEventPublisher eventPublisher;

    public HistoryService(HistoryRepository repository, ModelMapper modelMapper, ApplicationEventPublisher eventPublisher) {
        this.repository = repository;
        this.modelMapper = modelMapper;
        this.eventPublisher = eventPublisher;
    }

    @EventListener
    public HistoryDto createHistory(HistoryNotification notification){
        History history = modelMapper.map(notification, History.class);
        repository.save(history);
        return modelMapper.map(history, HistoryDto.class);
    }

    public List<HistoryDto> getAllHistories(){
        List<History> histories = repository.findAll();
        List<HistoryDto> responseDtoList;
        responseDtoList = modelMapper.map(
                histories, new TypeToken<List<HistoryDto>>() {}.getType());
        return responseDtoList;
    }

    public void deleteHistory(Long id){
        repository.deleteById(id);
    }
}
