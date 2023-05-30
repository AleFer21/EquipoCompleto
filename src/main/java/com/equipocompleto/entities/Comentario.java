package com.equipocompleto.entities;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comentario")
public class Comentario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idComentario;
    
    @ManyToOne
    private Jugador jugador;
    
    private String comentario;
    private int calificacion;

}
