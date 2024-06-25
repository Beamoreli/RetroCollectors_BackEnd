package com.retrocs.service;


import com.retrocs.model.Game;
import com.retrocs.repository.GameRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public List<Game> findAll() {
        return gameRepository.findAll();
    }

    public Game findById(Integer id) {
        return gameRepository.findById(id)
                .map(game -> {
                    Hibernate.initialize(game.getGeneros()); // Garante que os gêneros sejam carregados
                    return game;
                })
                .orElse(null);
    }

    public Game save(Game game) {
        return gameRepository.save(game);
    }

    public void deleteById(Integer id) {
        gameRepository.deleteById(id);
    }
}
