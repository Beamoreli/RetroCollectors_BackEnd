package com.retrocs.controller;

import com.retrocs.model.Generos;
import com.retrocs.service.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/generos")
public class GeneroController {

    @Autowired
    private GeneroService generoService;

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getAllGeneros() {
        List<Generos> generos = generoService.getAllGeneros();

        List<Map<String, Object>> response = new ArrayList<>();
        for (Generos genero : generos) {
            Map<String, Object> generoMap = new LinkedHashMap<>();
            generoMap.put("id", genero.getId());
            generoMap.put("nome", genero.getNome());
            response.add(generoMap);
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Generos> createGenero(@RequestBody Generos generos) {
        Generos savedGeneros = generoService.saveGenero(generos);
        return new ResponseEntity<>(savedGeneros, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGenero(@PathVariable Long id) {
        generoService.deleteGeneroById(id);
        return ResponseEntity.ok().build();
    }
}
