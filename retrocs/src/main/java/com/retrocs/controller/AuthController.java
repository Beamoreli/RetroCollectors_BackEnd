package com.retrocs.controller;

import com.retrocs.dto.LoginRequestDTO;
import com.retrocs.dto.RegisterRequestDTO;
import com.retrocs.dto.ResponseDTO;
import com.retrocs.model.Role;
import com.retrocs.model.Usuario;
import com.retrocs.repository.RoleRepository;
import com.retrocs.repository.UsuarioRepository;
import com.retrocs.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
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
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO body) {
        System.out.println("Received login request: " + body);

        Usuario usuario = usuarioRepository.findByEmail(body.email())
                .orElseThrow(() -> new RuntimeException("User not found"));

        System.out.println("User found: " + usuario);

        if (passwordEncoder.matches(body.password(), usuario.getSenha())) {
            String token = tokenService.generateToken(usuario);
            return ResponseEntity.ok(new ResponseDTO(usuario.getNome(), token));
        }

        return ResponseEntity.badRequest().body("Invalid password");
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDTO body) {
        System.out.println("Received registration request: " + body);

        if (body.name() == null || body.name().isEmpty()) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", "Name cannot be null or empty"));
        }

        if (body.password() == null || body.password().isEmpty()) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", "Password cannot be null or empty"));
        }

        Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(body.email());

        if (usuarioExistente.isEmpty()) {
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


