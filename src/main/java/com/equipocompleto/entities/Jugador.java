package com.equipocompleto.entities;

import com.equipocompleto.enums.Rol;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
public class Jugador extends Usuario {
    
    private long telefono;
    private String ubicacion;
    private String descripcion;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "jugador_id")
    private List<Video> videos;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "jugador_id")
    private List<Comentario> comentarios;

    public Jugador(long telefono, String ubicacion, String descripcion, List<Video> videos, List<Comentario> comentarios) {
        this.telefono = telefono;
        this.ubicacion = ubicacion;
        this.descripcion = descripcion;
        this.videos = videos;
        this.comentarios = comentarios;
    }

    public Jugador(long telefono, String ubicacion, String descripcion, List<Video> videos, List<Comentario> comentarios, Integer id, String nombre, String email, String password, Imagen fotoPerfil, Date fechaAlta, Rol rol, boolean altaBaja) {
        super(id, nombre, email, password, fotoPerfil, fechaAlta, rol, altaBaja);
        this.telefono = telefono;
        this.ubicacion = ubicacion;
        this.descripcion = descripcion;
        this.videos = videos;
        this.comentarios = comentarios;
    }
    
}
