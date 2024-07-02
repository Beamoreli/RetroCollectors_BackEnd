package com.retrocs.repository;

import com.retrocs.model.Generos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneroRepository extends JpaRepository<Generos, Long> {}




