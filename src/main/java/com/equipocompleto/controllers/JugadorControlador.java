package com.equipocompleto.controllers;

import com.equipocompleto.services.JugadorServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/jugador")
public class JugadorControlador {

    @Autowired
    private JugadorServicio jugadorServicio;

    @GetMapping("/registroJugador")
    public String crearJugador() {
        return "registroJugador.html";
    }

    @PostMapping("registrarJugador")
    public String crearJugador(@RequestParam("nombre") String nombre, @RequestParam("mail") String mail, @RequestParam("password") String password,
            @RequestParam("archivo") MultipartFile archivo, @RequestParam("telefono") long telefono, @RequestParam("ubicacion") String ubicacion,
            @RequestParam("descripcion") String descripcion, @RequestParam("videos") List<MultipartFile> videos, ModelMap modelo) {
        try {
//            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//            String nombreUsuario = userDetails.getUsername();
//            System.out.println("El jugador logueado es " + nombreUsuario);
            jugadorServicio.crearJugador(nombre, mail, password, archivo, telefono, ubicacion, descripcion, videos);
            modelo.put("exito", "Jugador registrado con exito!");
            return "redirect:/login.html";
        } catch (Exception e) {
            modelo.put("error", e.getMessage());
            modelo.put("nombre", nombre);
            modelo.put("mail", mail);
            modelo.put("password", password);
            modelo.put("archivo", archivo);
            modelo.put("telefono", telefono);
            modelo.put("ubicacion", ubicacion);
            modelo.put("descripcion", descripcion);
            modelo.put("videos", videos);
            return "redirect:/registroJugador.html";
        }
    }

}
