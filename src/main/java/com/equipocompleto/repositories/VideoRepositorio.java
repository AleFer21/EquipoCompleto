package com.equipocompleto.repositories;

import com.equipocompleto.entities.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepositorio extends JpaRepository<Video, Integer>{
    
}
