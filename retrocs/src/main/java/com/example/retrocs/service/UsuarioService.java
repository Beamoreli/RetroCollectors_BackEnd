package com.example.retrocs.service;


import com.example.retrocs.model.Usuario;
import com.example.retrocs.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {


    @Autowired
    private UsuarioRepository userRepository;

    public List<Usuario> findAll() {
        return userRepository.findAll();
    }

    public Usuario findById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public Usuario save(Usuario usuario) {
        return userRepository.save(usuario);
    }

    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }
}
