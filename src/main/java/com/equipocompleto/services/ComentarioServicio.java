package com.equipocompleto.services;

import com.equipocompleto.entities.Comentario;
import com.equipocompleto.entities.Jugador;
import com.equipocompleto.repositories.ComentarioRepositorio;
import com.equipocompleto.repositories.JugadorRepositorio;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComentarioServicio {

    @Autowired
    private ComentarioRepositorio comentarioRepositorio;

    @Autowired
    private JugadorRepositorio jugadorRepositorio;

    @Transactional
    public void crearComentario(int idJugador, String comentario, int calificacion) throws Exception {
        Optional<Jugador> jugadorOpt = jugadorRepositorio.findById(idJugador);
        if (!jugadorOpt.isPresent() || !(jugadorOpt.get() instanceof Jugador)) {
            throw new Exception("No se encontro el jugador.");
        }
        Jugador jugador = jugadorOpt.get();
        if (jugador.isAltaBaja() != true) {
            throw new Exception("El jugador no esta habilitado.");
        }
        Comentario nuevoComentario = new Comentario();
        nuevoComentario.setComentario(comentario);
        nuevoComentario.setCalificacion(calificacion);
        nuevoComentario.setJugador(jugador);
        comentarioRepositorio.save(nuevoComentario);
        jugador.getComentarios().add(nuevoComentario);
        jugadorRepositorio.save(jugador);
    }

    @Transactional
    public List<Comentario> listarComentaris() {
        return comentarioRepositorio.findAll();
    }

    @Transactional
    public void eliminarComentario(int id) {
        Comentario comentario = comentarioRepositorio.getById(id);
        comentarioRepositorio.delete(comentario);
    }

}
