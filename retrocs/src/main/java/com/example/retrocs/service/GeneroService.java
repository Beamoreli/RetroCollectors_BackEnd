package com.example.retrocs.service;

import com.example.retrocs.model.Genero;
import com.example.retrocs.repository.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GeneroService {

    @Autowired
    private GeneroRepository generoRepository;

    public List<Genero> getAllGeneros() {
        return generoRepository.findAll();
    }

    public Genero saveGenero(Genero genero) {
        return generoRepository.save(genero);
    }
}

