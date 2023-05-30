package com.equipocompleto.services;

import com.equipocompleto.entities.Imagen;
import com.equipocompleto.entities.Jugador;
import com.equipocompleto.entities.Video;
import com.equipocompleto.enums.Rol;
import com.equipocompleto.repositories.JugadorRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class JugadorServicio {

    @Autowired
    private JugadorRepositorio jugadorRepositorio;

    @Autowired
    private ImagenServicio imagenServicio;

    @Autowired
    private VideoServicio videoServicio;

    @Transactional
    public void crearJugador(String nombre, String mail, String password, MultipartFile archivo, long telefono, String ubicacion,
            String descripcion, List<MultipartFile> videos) throws Exception {
        validar(nombre, mail, password, archivo, telefono, ubicacion, descripcion, videos);
        Jugador jugador = new Jugador();
        jugador.setNombre(nombre);
        jugador.setMail(mail);
//        jugador.setPassword(new BCryptPasswordEncoder().encode(password));
        Imagen fotoPerfil = imagenServicio.guardarImagen(archivo);
        jugador.setFotoPerfil(fotoPerfil);
        jugador.setTelefono(telefono);
        jugador.setUbicacion(ubicacion);
        jugador.setDescripcion(descripcion);
        List<Video> listaVideos = new ArrayList();
        for (MultipartFile video : videos) {
            Video v = videoServicio.guardarVideo(video);
            listaVideos.add(v);
        }
        jugador.setVideos(listaVideos);
        Date fechaAlta = new Date();
        jugador.setFechaAlta(fechaAlta);
        jugador.setRol(Rol.JUGADOR);
        boolean alta = Boolean.TRUE;
        jugador.setAltaBaja(alta);
        jugadorRepositorio.save(jugador);
    }

    @Transactional
    public void actualizarJugador(int id, String nombre, String mail, String password, MultipartFile archivo, long telefono, String ubicacion,
            String descripcion, List<MultipartFile> videos) throws Exception {
        validar(nombre, mail, password, archivo, telefono, ubicacion, descripcion, videos);
        Optional<Jugador> respuesta = jugadorRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Jugador jugador = respuesta.get();
            jugador.setNombre(nombre);
            jugador.setMail(mail);
//            jugador.setPassword(new BCryptPasswordEncoder().encode(password));
            int idImagen = 0;
            if (jugador.getFotoPerfil() != null) {
                idImagen = jugador.getFotoPerfil().getIdImagen();
            }
            Imagen fotoPerfil = imagenServicio.actualizarImagen(archivo, idImagen);
            jugador.setFotoPerfil(fotoPerfil);
            jugador.setTelefono(telefono);
            jugador.setUbicacion(ubicacion);
            jugador.setDescripcion(descripcion);
            List<Video> videosAntiguos = jugador.getVideos();
            for (Video video : videosAntiguos) {
                videoServicio.eliminarVideo(video.getIdVideo());
            }
            List<Video> videosNuevos = new ArrayList();
            for (MultipartFile video : videos) {
                Video v = videoServicio.guardarVideo(video);
                videosNuevos.add(v);
            }
            jugador.setVideos(videosNuevos);
            jugadorRepositorio.save(jugador);
        }
    }

    @Transactional
    public void altaBaja(int id) throws Exception {
        if (id < 0) {
            throw new Exception("Ingrese un id.");
        }
        Optional<Jugador> respuesta = jugadorRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Jugador jugador = respuesta.get();
            boolean baja = Boolean.FALSE;
            jugador.setAltaBaja(baja);
            jugadorRepositorio.save(jugador);
        }
    }

    @Transactional
    public void eliminarJugador(int id) {
        Jugador jugador = jugadorRepositorio.getById(id);
        jugadorRepositorio.delete(jugador);
    }

    @Transactional
    public List<Jugador> listarJugadores() {
        return jugadorRepositorio.findAll();
    }

    @Transactional
    public Jugador getOne(int id) {
        return jugadorRepositorio.getOne(id);
    }

    private void validar(String nombre, String mail, String password, MultipartFile archivo, long telefono, String ubicacion,
            String descripcion, List<MultipartFile> videos) throws Exception {
        if (nombre == null || nombre.isEmpty()) {
            throw new Exception("Por favor ingrese su nombre.");
        }
        if (mail == null || mail.isEmpty()) {
            throw new Exception("Por favor ingrese su mail.");
        }
        if (password == null || password.isEmpty()) {
            throw new Exception("Por favor ingrese una contraseña.");
        }
        if (archivo == null) {
            throw new Exception("Por favor agregue una foto de perfil.");
        }
        if (telefono < 0) {
            throw new Exception("Por favor ingrese su numero de telefono.");
        }
        if (ubicacion == null || ubicacion.isEmpty()) {
            throw new Exception("Por favor ingrese su ubicacion.");
        }
        if (descripcion == null || descripcion.isEmpty()) {
            throw new Exception("Por favor ingrese una ubicacion.");
        }
        if (videos == null) {
            throw new Exception("Por favor agregue sus videos.");
        }
    }

}
