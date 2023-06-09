package com.equipocompleto.controllers;

import com.equipocompleto.entities.Imagen;
import com.equipocompleto.entities.Jugador;
import com.equipocompleto.services.ImagenServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/imagen")
public class ImagenControlador {

    @Autowired
    private ImagenServicio imagenServicio;

    @GetMapping("/imgJugador/{id}")
    public ResponseEntity<byte[]> imagenJugador(@PathVariable int id) {
        Imagen imagen = imagenServicio.getOne(id);
        byte[] imagenJugador = imagen.getImagen();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(imagenJugador, headers, HttpStatus.OK);
    }

}
