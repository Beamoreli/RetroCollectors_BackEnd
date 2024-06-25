package com.retrocs.controller;

import com.retrocs.model.Genero;
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
        List<Genero> generos = generoService.getAllGeneros();

        List<Map<String, Object>> response = new ArrayList<>();
        for (Genero genero : generos) {
            Map<String, Object> generoMap = new LinkedHashMap<>();
            generoMap.put("id", genero.getId());
            generoMap.put("nome", genero.getNome());
            response.add(generoMap);
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Genero> createGenero(@RequestBody Genero genero) {
        Genero savedGenero = generoService.saveGenero(genero);
        return new ResponseEntity<>(savedGenero, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGenero(@PathVariable Long id) {
        generoService.deleteGeneroById(id);
        return ResponseEntity.ok().build();
    }
}
