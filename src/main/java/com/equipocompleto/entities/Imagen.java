package com.equipocompleto.entities;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "imagen")
public class Imagen {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idImagen;

    private String mime;
    private String nombre;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] imagen;

}
