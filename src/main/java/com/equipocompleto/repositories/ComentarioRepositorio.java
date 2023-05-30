package com.equipocompleto.repositories;

import com.equipocompleto.entities.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioRepositorio extends JpaRepository<Comentario, Integer> {
    
}
