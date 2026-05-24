package com.antiprag.prag.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;
import com.antiprag.prag.domain.History;
import com.antiprag.prag.repository.HistoryRepository;

@Service
public class HistoryService {

    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    AuthenticationManager authManager;

    public HistoryService(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    public History getHistory(Integer id) {
        return historyRepository.findById(id).orElse(null);
    }

    public List<History> ListHistory() {
        return historyRepository.findAll();
    }

    public void deletarHistory(int idHistory) {
        historyRepository.deleteById(idHistory);
    }

    public void alterarHistory(History History) {
        historyRepository.save(History);
    }

    public void inserirHistory(History history) {
        historyRepository.save(history);
    }
}
