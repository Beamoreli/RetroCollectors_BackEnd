package com.example.retrocs.controller;


import com.example.retrocs.model.Game;
import com.example.retrocs.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {
    @Autowired
    private GameService gameService;

    @GetMapping
    public List<Game> getAllGames() {
        return gameService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable Integer id) {
        Game game = gameService.findById(id);
        return game != null ? ResponseEntity.ok(game) : ResponseEntity.notFound().build();
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Game createGame(@RequestBody Game game) {
        return gameService.save(game);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Game> updateGame(@PathVariable Integer id, @RequestBody Game gameDetails) {
        Game game = gameService.findById(id);
        if (game != null) {
            game.setNome(gameDetails.getNome());
            game.setImagem(gameDetails.getImagem());
            game.setAno_lancamento(gameDetails.getAno_lancamento());
            game.setConsole(gameDetails.getConsole());
            game.setDistribuidora(gameDetails.getDistribuidora());
            game.setGeneros(gameDetails.getGeneros());
            return ResponseEntity.ok(gameService.save(game));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteGame(@PathVariable Integer id) {
        gameService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}