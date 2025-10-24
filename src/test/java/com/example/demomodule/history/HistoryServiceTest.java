package com.example.demomodule.history;

import com.example.demomodule.notification.HistoryNotification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;

import java.util.List;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HistoryServiceTest {

    @Mock
    private HistoryRepository historyRepository;

    @Mock
    private ApplicationEventPublisher eventPublisher;

    @InjectMocks
    private HistoryService historyService;

    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        modelMapper = new ModelMapper();
        historyService = new HistoryService(historyRepository, modelMapper, eventPublisher);
    }

    @Test
    void shouldCreateHistory() {
        // given
        HistoryNotification notification = new HistoryNotification();
        notification.setModule("student");
        notification.setTitle("Creating Student");
        notification.setDescription("creating of only one student");
        notification.setIdentifier(1L);

        History history = modelMapper.map(notification, History.class);
        history.setId(1L);

        when(historyRepository.save(any(History.class))).thenReturn(history);

        // when
        HistoryDto result = historyService.createHistory(notification);

        // then
        assertNotNull(result);
        assertEquals(history.getDescription(), result.getDescription());
        assertEquals(history.getTitle(), result.getTitle());
        assertEquals(history.getModule(), result.getModule());
        verify(historyRepository, times(1)).save(any(History.class));
    }

    @Test
    void shouldGetAllHistories() {
        // given
        History history1 = new History(1L, "CREATE", 1L, "Student", "Description");
        History history2 = new History(2L, "DELETE", 2L, "Student", "Description");

        List<History> historyList = List.of(history1, history2);
        when(historyRepository.findAll()).thenReturn(historyList);

        // when
        List<HistoryDto> result = historyService.getAllHistories();

        // then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("CREATE", result.get(0).getModule());
        assertEquals("DELETE", result.get(1).getModule());
        verify(historyRepository, times(1)).findAll();
    }

    @Test
    void shouldDeleteHistory() {
        // given
        Long id = 1L;

        // when
        historyService.deleteHistory(id);

        // then
        verify(historyRepository, times(1)).deleteById(id);
    }
}