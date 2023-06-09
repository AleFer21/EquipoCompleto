package com.equipocompleto.controllers;

import com.equipocompleto.entities.Jugador;
import com.equipocompleto.services.JugadorServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class PortalControlador {

    @Autowired
    private JugadorServicio jugadorServicio;

    @GetMapping("/index")
    public String index(ModelMap modelo) {
        List<Jugador> jugadores = jugadorServicio.listarJugadores();
        modelo.addAttribute("jugadores", jugadores);
        return "index.html";
    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, ModelMap modelo) {
        if (error != null) {
            modelo.put("error", "Usuario o contraseña invalidos.");
        }
        return "login.html";
    }

}
