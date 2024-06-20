package com.example.retrocs.security;

import com.example.retrocs.model.Role;
import com.example.retrocs.model.Usuario;
import com.example.retrocs.repository.RoleRepository;
import com.example.retrocs.repository.UsuarioRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AdminUserInitializer {

    private final UsuarioRepository usuarioRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminUserInitializer(UsuarioRepository usuarioRepository,
                                RoleRepository roleRepository,
                                PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {


        Optional<Role> adminRoleOptional = roleRepository.findByNome("ROLE_ADMIN");
        Role adminRole = adminRoleOptional.orElseGet(() -> roleRepository.save(new Role("ROLE_ADMIN")));


        Optional<Usuario> adminUserOptional = usuarioRepository.findByEmail("admin@admin.com");
        if (adminUserOptional.isEmpty()) {

            Usuario adminUser = new Usuario();
            adminUser.setNome("Admin");
            adminUser.setEmail("admin@admin.com");
            adminUser.setSenha(passwordEncoder.encode("admin123"));
            adminUser.getRoles().add(adminRole);

            usuarioRepository.save(adminUser);
        }
    }
}
