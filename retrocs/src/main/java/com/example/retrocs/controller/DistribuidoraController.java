package com.example.retrocs.controller;

import com.example.retrocs.model.Distribuidora;
import com.example.retrocs.service.DistribuidoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/distribuidoras")
public class DistribuidoraController {

    @Autowired
    private DistribuidoraService distribuidoraService;

    @GetMapping
    public ResponseEntity<List<Distribuidora>> getAllDistribuidoras() {
        List<Distribuidora> distribuidoras = distribuidoraService.getAllDistribuidoras();
        return ResponseEntity.ok(distribuidoras);
    }

    @PostMapping
    public ResponseEntity<Distribuidora> createDistribuidora(@RequestBody Distribuidora distribuidora) {
        Distribuidora savedDistribuidora = distribuidoraService.saveDistribuidora(distribuidora);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDistribuidora);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Distribuidora> updateDistribuidora(@PathVariable Integer id, @RequestBody Distribuidora distribuidoraDetails) {
        Distribuidora updatedDistribuidora = distribuidoraService.updateDistribuidora(id, distribuidoraDetails);
        return ResponseEntity.ok(updatedDistribuidora);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDistribuidora(@PathVariable Integer id) {
        distribuidoraService.deleteDistribuidora(id);
        return ResponseEntity.noContent().build();
    }
}
