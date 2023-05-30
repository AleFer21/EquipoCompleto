package com.equipocompleto.services;

import com.equipocompleto.entities.Imagen;
import com.equipocompleto.entities.Usuario;
import com.equipocompleto.enums.Rol;
import com.equipocompleto.repositories.UsuarioRepositorio;
import java.util.Date;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UsuarioServicio  {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private ImagenServicio imagenServicio;

    @Transactional
    public void crearUsuario(String nombre, String mail, String password, MultipartFile archivo) throws Exception {
        validar(nombre, mail, password, archivo);
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setMail(mail);
//        usuario.setPassword(new BCryptPasswordEncoder().encode(password));
        Imagen fotoPerfil = imagenServicio.guardarImagen(archivo);
        usuario.setFotoPerfil(fotoPerfil);
        Date fechaAlta = new Date();
        usuario.setFechaAlta(fechaAlta);
        usuario.setRol(Rol.ADMIN);
        boolean alta = Boolean.TRUE;
        usuario.setAltaBaja(alta);
        usuarioRepositorio.save(usuario);
    }

    private void validar(String nombre, String mail, String password, MultipartFile archivo) throws Exception {
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
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

}
