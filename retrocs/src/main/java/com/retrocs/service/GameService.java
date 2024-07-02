package com.retrocs.service;


import com.retrocs.model.Games;
import com.retrocs.repository.GameRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public List<Games> findAll() {
        return gameRepository.findAll();
    }

    public Games findById(Integer id) {
        return gameRepository.findById(id)
                .map(game -> {
                    Hibernate.initialize(game.getGeneros());
                    return game;
                })
                .orElse(null);
    }

    public Games save(Games games) {
        return gameRepository.save(games);
    }

    public void deleteById(Integer id) {
        gameRepository.deleteById(id);
    }
}
