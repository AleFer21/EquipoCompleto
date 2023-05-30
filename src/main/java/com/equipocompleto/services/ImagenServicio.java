package com.equipocompleto.services;

import com.equipocompleto.entities.Imagen;
import com.equipocompleto.repositories.ImagenRepositorio;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImagenServicio {

    @Autowired
    private ImagenRepositorio imagenRepositorio;

    @Transactional
    public Imagen guardarImagen(MultipartFile archivo) throws Exception {
        validar(archivo);
        if (archivo != null) {
            try {
                Imagen imagen = new Imagen();
                imagen.setMime(archivo.getContentType());
                imagen.setNombre(archivo.getName());
                imagen.setImagen(archivo.getBytes());
                return imagenRepositorio.save(imagen);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
        return null;
    }

    @Transactional
    public Imagen actualizarImagen(MultipartFile archivo, Integer idImagen) throws Exception {
        validar(archivo);
        try {
            Imagen imagen = new Imagen();
            if (idImagen != null) {
                Optional<Imagen> respuesta = imagenRepositorio.findById(idImagen);
                if (respuesta.isPresent()) {
                    imagen = respuesta.get();
                }
            }
            imagen.setMime(archivo.getContentType());
            imagen.setNombre(archivo.getName());
            imagen.setImagen(archivo.getBytes());
            return imagenRepositorio.save(imagen);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Transactional
    public void eliminarImagen(int id) {
        Imagen imagen = imagenRepositorio.getById(id);
        imagenRepositorio.delete(imagen);
    }

    @Transactional
    public List<Imagen> listarImagen() {
        return imagenRepositorio.findAll();
    }

    private void validar(MultipartFile archivo) throws Exception {
        if (archivo == null) {
            throw new Exception("Por favor agregue una imagen.");
        }
    }

    public Imagen getOne(int idImagen) {
        return imagenRepositorio.getOne(idImagen);
    }

}
