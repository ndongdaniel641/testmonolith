package com.example.demomodule.history;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {
    History findByTitle(String title);
    History findByModuleAndIdentifier(String module, String identifier);
}
