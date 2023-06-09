package com.equipocompleto.services;

import com.equipocompleto.entities.Imagen;
import com.equipocompleto.entities.Jugador;
import com.equipocompleto.entities.Usuario;
import com.equipocompleto.enums.Rol;
import com.equipocompleto.repositories.JugadorRepositorio;
import com.equipocompleto.repositories.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UsuarioServicio implements UserDetailsService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private JugadorRepositorio jugadorRepositorio;

    @Autowired
    private ImagenServicio imagenServicio;

    @Transactional
    public void crearAdmin(String nombre, String email, String password, MultipartFile fotoPerfil) throws Exception {
        validar(nombre, email, password, fotoPerfil);
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setEmail(email);
        usuario.setPassword(new BCryptPasswordEncoder().encode(password));
        Imagen imagen = imagenServicio.guardarImagen(fotoPerfil);
        usuario.setFotoPerfil(imagen);
        Date fechaAlta = new Date();
        usuario.setFechaAlta(fechaAlta);
        usuario.setRol(Rol.ADMIN);
        boolean alta = Boolean.TRUE;
        usuario.setAltaBaja(alta);
        try {
            usuarioRepositorio.save(usuario);
        } catch (Exception e) {
            throw new Exception("Error al guardar los cambios en la base de datos.");
        }

    }

    @Transactional
    public void bajaJugador(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("El ID debe ser mayor a cero.");
        }
        Optional<Jugador> respuesta = jugadorRepositorio.findById(id);
        if (!respuesta.isPresent()) {
            throw new Exception("No se encontró un jugador con el ID especificado.");
        }
        Jugador jugador = respuesta.get();
        jugador.setAltaBaja(Boolean.FALSE);
        try {
            jugadorRepositorio.save(jugador);
        } catch (Exception e) {
            throw new Exception("Error al guardar los cambios en la base de datos.");
        }
    }

    private void validar(String nombre, String email, String password, MultipartFile fotoPerfil) throws Exception {
        if (nombre == null || nombre.isEmpty()) {
            throw new Exception("Por favor ingrese su nombre.");
        }
        if (email == null || email.isEmpty()) {
            throw new Exception("Por favor ingrese su mail.");
        }
        if (password == null || password.isEmpty() || password.length() <= 7) {
            throw new Exception("Por favor ingrese una contraseña de 8 caracteres o mas.");
        }
        if (fotoPerfil == null) {
            throw new Exception("Por favor agregue una foto de perfil.");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepositorio.buscarPorEmail(email);
        if (usuario != null) {
            List<GrantedAuthority> permisos = new ArrayList();
            GrantedAuthority p = new SimpleGrantedAuthority("ROLE" + usuario.getRol().toString());
            permisos.add(p);
            return new User(usuario.getEmail(), usuario.getPassword(), permisos);
        } else {
            return null;
        }
    }
}
