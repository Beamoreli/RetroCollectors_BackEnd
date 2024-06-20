package com.example.retrocs.controller;

import com.example.retrocs.dto.LoginRequestDTO;
import com.example.retrocs.dto.RegisterRequestDTO;
import com.example.retrocs.dto.ResponseDTO;
import com.example.retrocs.model.Role;
import com.example.retrocs.model.Usuario;
import com.example.retrocs.repository.RoleRepository;
import com.example.retrocs.repository.UsuarioRepository;
import com.example.retrocs.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO body){
        Usuario usuario = usuarioRepository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("User not found"));
        if(passwordEncoder.matches(body.password(), usuario.getSenha())) {
            String token = tokenService.generateToken(usuario);
            return ResponseEntity.ok(new ResponseDTO(usuario.getNome(), token));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRequestDTO body){
        Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(body.email());

        if(usuarioExistente.isEmpty()) {
            Usuario newUser = new Usuario();
            newUser.setSenha(passwordEncoder.encode(body.password()));
            newUser.setEmail(body.email());
            newUser.setNome(body.name());

            Role roleUser = roleRepository.findByNome("ROLE_USER")
                    .orElseGet(() -> roleRepository.save(new Role("ROLE_USER")));
            newUser.getRoles().add(roleUser);

            usuarioRepository.save(newUser);

            String token = tokenService.generateToken(newUser);
            return ResponseEntity.ok(new ResponseDTO(newUser.getNome(), token));
        }
        return ResponseEntity.badRequest().build();
    }
}


