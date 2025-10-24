package com.example.demomodule.history;

import com.example.demomodule.student.StudentRequestDto;
import com.example.demomodule.student.StudentResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/history")
public class HistoryController {
    final private HistoryService historyService;

    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @GetMapping("/get")
    public ResponseEntity<List<HistoryDto>> getAllHistories(){
        return ResponseEntity.ok(historyService.getAllHistories());
    }

    @DeleteMapping("/delete")
    public void deleteHistory(@RequestParam Long id){
        historyService.deleteHistory(id);
    }
}
