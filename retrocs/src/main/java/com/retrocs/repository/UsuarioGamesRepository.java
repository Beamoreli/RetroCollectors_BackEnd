package com.retrocs.repository;


import com.retrocs.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioGamesRepository extends JpaRepository<Usuario, Integer> {
}
