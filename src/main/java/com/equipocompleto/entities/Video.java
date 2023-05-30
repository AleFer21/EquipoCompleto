package com.equipocompleto.entities;

import javax.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "video")
public class Video {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVideo;

    private String mime;
    private String nombre;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] video;
    
}
