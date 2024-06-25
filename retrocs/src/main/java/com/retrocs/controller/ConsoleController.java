package com.retrocs.controller;


import com.retrocs.model.Console;
import com.retrocs.service.ConsoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consoles")
public class ConsoleController {

    @Autowired
    private ConsoleService consoleService;

    @GetMapping
    public ResponseEntity<List<Console>> getAllConsoles() {
        List<Console> consoles = consoleService.getAllConsoles();
        return ResponseEntity.ok(consoles);
    }

    @PostMapping
    public ResponseEntity<Console> createConsole(@RequestBody Console console) {
        Console savedConsole = consoleService.saveConsole(console);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedConsole);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Console> updateConsole(@PathVariable Integer id, @RequestBody Console consoleDetails) {
        Console updatedConsole = consoleService.updateConsole(id, consoleDetails);
        return ResponseEntity.ok(updatedConsole);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsole(@PathVariable Integer id) {
        consoleService.deleteConsole(id);
        return ResponseEntity.noContent().build();
    }
}
