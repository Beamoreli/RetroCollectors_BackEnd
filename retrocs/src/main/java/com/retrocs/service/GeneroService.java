package com.retrocs.service;

import com.retrocs.model.Generos;
import com.retrocs.repository.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneroService {

    @Autowired
    private GeneroRepository generoRepository;

    public List<Generos> getAllGeneros() {
        List<Generos> generos = generoRepository.findAll();

        return generos;
    }

    public Generos saveGenero(Generos generos) {
        Generos savedGeneros = generoRepository.save(generos);

        return savedGeneros;
    }

    public void deleteGeneroById(Long id) {
        generoRepository.deleteById(id);
    }
}


