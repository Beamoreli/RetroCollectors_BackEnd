package com.retrocs.service;

import com.retrocs.model.Games;
import com.retrocs.model.Usuario;
import com.retrocs.repository.GameRepository;
import com.retrocs.repository.UsuarioRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

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

    public void favoriteGame(Integer gameId, Integer userId) {
        Games game = gameRepository.findById(gameId).orElseThrow(() -> new RuntimeException("Game not found"));
        Usuario usuario = usuarioRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        usuario.getFavoritos().add(game);
        usuarioRepository.save(usuario);
    }

    public void unfavoriteGame(Integer gameId, Integer userId) {
        Games game = gameRepository.findById(gameId).orElseThrow(() -> new RuntimeException("Game not found"));
        Usuario usuario = usuarioRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        usuario.getFavoritos().remove(game);
        usuarioRepository.save(usuario);
    }
}
