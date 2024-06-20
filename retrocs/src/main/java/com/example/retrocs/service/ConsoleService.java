package com.example.retrocs.service;

import com.example.retrocs.model.Console;
import com.example.retrocs.repository.ConsoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsoleService {

    @Autowired
    private ConsoleRepository consoleRepository;

    public List<Console> getAllConsoles() {
        return consoleRepository.findAll();
    }

    public Console saveConsole(Console console) {
        return consoleRepository.save(console);
    }

    public Console updateConsole(Integer id, Console consoleDetails) {
        Console console = consoleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Console não encontrado"));
        console.setNome(consoleDetails.getNome());
        return consoleRepository.save(console);
    }

    public void deleteConsole(Integer id) {
        consoleRepository.deleteById(id);
    }
}
