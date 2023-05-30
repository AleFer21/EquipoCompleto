package com.equipocompleto.entities;

import com.equipocompleto.enums.Rol;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuario")
public class Usuario implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String nombre;
    private String mail;
    private String password;
    
    @OneToOne
    private Imagen fotoPerfil;
    
    @Temporal(TemporalType.DATE)
    private Date fechaAlta;
    
    @Enumerated(EnumType.STRING)
    private Rol rol;
    
    private boolean altaBaja;

}
