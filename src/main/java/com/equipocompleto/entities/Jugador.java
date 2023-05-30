package com.equipocompleto.entities;

import java.util.List;
import javax.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
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
    
}
