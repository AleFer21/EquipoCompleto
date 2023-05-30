package com.equipocompleto.services;

import com.equipocompleto.entities.Video;
import com.equipocompleto.repositories.VideoRepositorio;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class VideoServicio {

    @Autowired
    private VideoRepositorio videoRepositorio;

    @Transactional
    public Video guardarVideo(MultipartFile archivo) throws Exception {
        validar(archivo);
        if (archivo != null) {
            try {
                Video video = new Video();
                video.setMime(archivo.getContentType());
                video.setNombre(archivo.getName());
                video.setVideo(archivo.getBytes());
                return videoRepositorio.save(video);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
        return null;
    }

    @Transactional
    public Video actualizarVideo(MultipartFile archivo, Integer idVideo) throws Exception {
        validar(archivo);
        try {
            Video video = new Video();
            if (idVideo != null) {
                Optional<Video> respuesta = videoRepositorio.findById(idVideo);
                if (respuesta.isPresent()) {
                    video = respuesta.get();
                }
                video.setMime(archivo.getContentType());
                video.setNombre(archivo.getName());
                video.setVideo(archivo.getBytes());
                return videoRepositorio.save(video);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Transactional
    public void eliminarVideo(int id) {
        Video video = videoRepositorio.getById(id);
        videoRepositorio.delete(video);
    }

    @Transactional
    public List<Video> listarVideo() {
        return videoRepositorio.findAll();
    }

    private void validar(MultipartFile archivo) throws Exception {
        if (archivo == null) {
            throw new Exception("Por favor agregue un video.");
        }
    }

    public Video getOne(int idVideo) {
        return videoRepositorio.getOne(idVideo);
    }

}
