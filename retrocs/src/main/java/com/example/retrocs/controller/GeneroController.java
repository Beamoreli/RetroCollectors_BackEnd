package com.example.retrocs.controller;

import com.example.retrocs.model.Genero;
import com.example.retrocs.service.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/generos")
public class GeneroController {

    @Autowired
    private GeneroService generoService;

    @GetMapping
    public ResponseEntity<List<String>> getAllGeneros() {
        List<String> generos = generoService.getAllGeneros();
        return ResponseEntity.ok(generos);
    }

    @PostMapping
    public ResponseEntity<Genero> createGenero(@RequestBody Genero genero) {
        Genero savedGenero = generoService.saveGenero(genero);
        return new ResponseEntity<>(savedGenero, HttpStatus.CREATED);
    }
}
