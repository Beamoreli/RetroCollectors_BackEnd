package com.example.retrocs.controller;

import com.example.retrocs.dto.GameDTO;
import com.example.retrocs.model.Usuario;
import com.example.retrocs.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<Void> adicionarJogoParaUsuario(@PathVariable Integer idUsuario, @RequestBody GameDTO gameDTO) {

        return ResponseEntity.ok().build();
    }


}