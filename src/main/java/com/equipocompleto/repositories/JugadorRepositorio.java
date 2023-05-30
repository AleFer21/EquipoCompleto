package com.equipocompleto.repositories;

import com.equipocompleto.entities.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JugadorRepositorio extends JpaRepository<Jugador, Integer>{
    
}
