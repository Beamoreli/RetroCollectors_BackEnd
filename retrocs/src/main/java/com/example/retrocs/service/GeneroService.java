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

    public List<String> getAllGeneros() {
        return generoRepository.findAll().stream()
                .map(Genero::getNome)
                .collect(Collectors.toList());
    }
}
