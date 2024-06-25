package com.retrocs.service;

import com.retrocs.model.Genero;
import com.retrocs.repository.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneroService {

    @Autowired
    private GeneroRepository generoRepository;

    public List<Genero> getAllGeneros() {
        List<Genero> generos = generoRepository.findAll();

        return generos;
    }

    public Genero saveGenero(Genero genero) {
        Genero savedGenero = generoRepository.save(genero);

        return savedGenero;
    }

    public void deleteGeneroById(Long id) {
        generoRepository.deleteById(id);
    }
}


