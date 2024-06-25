package com.example.retrocs.controller;

import com.example.retrocs.dto.GameDTO;
import com.example.retrocs.model.Usuario;
import com.example.retrocs.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> getAllUsuarios() {
        return usuarioService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioComJogos(@PathVariable("id") Long userId) {
        Usuario usuario = usuarioService.buscarUsuarioComJogos(userId);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    public Usuario createUsuario(@RequestBody Usuario usuario) {
        return usuarioService.save(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Integer id, @RequestBody Usuario usuarioDetails) {
        Usuario usuario = usuarioService.findById(id);
        if (usuario != null) {
            usuario.setNome(usuarioDetails.getNome());
            usuario.setEmail(usuarioDetails.getEmail());
            usuario.setSenha(usuarioDetails.getSenha());
            return ResponseEntity.ok(usuarioService.save(usuario));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Integer id) {
        usuarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{idUsuario}/adicionar-jogo")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Usuario> adicionarJogoParaUsuario(@PathVariable Integer idUsuario, @RequestBody GameDTO gameDTO) {
        try {
            Set<Integer> jogosIds = new HashSet<>();
            jogosIds.add(gameDTO.getId());
            Usuario usuarioAtualizado = usuarioService.adicionarJogosAoUsuario(idUsuario, jogosIds);
            return ResponseEntity.ok(usuarioAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{idUsuario}/remover-jogo/{idJogo}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Usuario> removerJogoDoUsuario(@PathVariable Integer idUsuario, @PathVariable Integer idJogo) {
        try {
            Usuario usuarioAtualizado = usuarioService.removerJogoDoUsuario(idUsuario, idJogo);
            return ResponseEntity.ok(usuarioAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }


}
