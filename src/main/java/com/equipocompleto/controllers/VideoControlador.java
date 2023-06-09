package com.equipocompleto.controllers;

import com.equipocompleto.entities.Video;
import com.equipocompleto.services.VideoServicio;
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
@RequestMapping("/video")
public class VideoControlador {
    
    @Autowired
    private VideoServicio videoServicio;

    @GetMapping("/jugadas/{id}")
    public ResponseEntity<byte[]> jugadas(@PathVariable int id) {
        Video video = videoServicio.getOne(id);
        byte[] jugadas = video.getVideo();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("video/mp4"));
        headers.setContentLength(jugadas.length);
        return new ResponseEntity<>(jugadas, headers, HttpStatus.OK);
    }
    
}
