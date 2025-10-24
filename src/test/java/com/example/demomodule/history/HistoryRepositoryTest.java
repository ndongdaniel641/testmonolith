package com.example.demomodule.history;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class HistoryRepositoryTest {
    @Autowired
    private HistoryRepository historyRepository;

    @Test
    void shouldFindAllHistory()
    {
        List<History> history = historyRepository.findAll();
        assertNotNull(history);
    }

    @Test
    void shouldFindHistoryById()
    {
        History history = historyRepository.findById(1L).get();
        assertNotNull(history);
    }

    @Test
    void shouldSaveHistory()
    {
        History history = new History();
        history.setId(1L);
        history.setModule("module");
        history.setIdentifier(12L);
        history.setDescription("description");
        history.setTitle("title");
        historyRepository.save(history);

        assertNotNull(historyRepository.findById(1L).get());
        assertEquals("module", history.getModule());
        assertEquals("description", history.getDescription());
        assertEquals("title", history.getTitle());
    }

    @Test
    void shouldDeleteHistoryById()
    {
        History history = new History();
        history.setId(1L);
        history.setModule("module");
        history.setIdentifier(12L);
        history.setDescription("description");
        history.setTitle("title");
        historyRepository.save(history);
        historyRepository.deleteById(1L);

        historyRepository.delete(history);
        assertNull(historyRepository.findByTitle("title"));
    }

}