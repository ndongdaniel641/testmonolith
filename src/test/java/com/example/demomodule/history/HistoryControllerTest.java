package com.example.demomodule.history;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.MediaType;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class HistoryControllerTest {

    private MockMvc mockMvc;

    @Mock
    private HistoryService historyService;

    @InjectMocks
    private HistoryController historyController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(historyController).build();
    }

    @Test
    void shouldGetAllHistories() throws Exception {
        // given
        HistoryDto history1 = new HistoryDto();
        history1.setId(1L);
        history1.setModule("CREATE");

        HistoryDto history2 = new HistoryDto();
        history2.setId(2L);
        history2.setModule("DELETE");

        List<HistoryDto> historyList = List.of(history1, history2);
        when(historyService.getAllHistories()).thenReturn(historyList);

        // when + then
        mockMvc.perform(MockMvcRequestBuilders.get("/history/get")
                        .contentType(String.valueOf(MediaType.APPLICATION_JSON)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].module").value("CREATE"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].module").value("DELETE"));

        verify(historyService, times(1)).getAllHistories();
    }

    @Test
    void shouldDeleteHistory() throws Exception {
        // given
        Long id = 1L;

        // when + then
        mockMvc.perform(MockMvcRequestBuilders.delete("/history/delete")
                        .param("id", id.toString()))
                .andExpect(status().isOk());

        verify(historyService, times(1)).deleteHistory(id);
    }
}