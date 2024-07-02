package com.retrocs.controller;


import com.retrocs.model.Games;
import com.retrocs.repository.GameRepository;
import com.retrocs.service.GameService;
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

    @Autowired
    private GameRepository gameRepository;


    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Games> createGame(@RequestBody Games games) {
        Games savedGames = gameRepository.save(games);
        return ResponseEntity.ok(savedGames);
    }


    @GetMapping
    public List<Games> getAllGames() {
        return gameService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Games> getGameById(@PathVariable Integer id) {
        Games games = gameService.findById(id);
        return games != null ? ResponseEntity.ok(games) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Games> updateGame(@PathVariable Integer id, @RequestBody Games gamesDetails) {
        Games games = gameService.findById(id);
        if (games != null) {
            games.setNome(gamesDetails.getNome());
            games.setImagem(gamesDetails.getImagem());
            games.setAno_lancamento(gamesDetails.getAno_lancamento());
            games.setConsole(gamesDetails.getConsole());
            games.setDistribuidora(gamesDetails.getDistribuidora());
            games.setGeneros(gamesDetails.getGeneros());
            return ResponseEntity.ok(gameService.save(games));
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