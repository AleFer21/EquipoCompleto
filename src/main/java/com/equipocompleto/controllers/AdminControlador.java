package com.equipocompleto.controllers;

import com.equipocompleto.entities.Jugador;
import com.equipocompleto.services.JugadorServicio;
import com.equipocompleto.services.UsuarioServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/admin")
public class AdminControlador {
    
    @Autowired
    private UsuarioServicio usuarioServicio;
    
    @Autowired
    private JugadorServicio jugadorServicio;
    
    @GetMapping("/dashboard")
    public String dashboard(ModelMap modelo) {
        List<Jugador> jugadores = jugadorServicio.listarJugadores();
        modelo.addAttribute("jugadores", jugadores);
        return "dashboard.html";
    }
    
    @GetMapping("/registroAdmin")
    public String crearAdmin(ModelMap modelo) {
        return "registroAdmin.html";
    }
    
    @PostMapping("/registrarAdmin")
    public String crearAdmin(@RequestParam("nombre") String nombre, @RequestParam("email") String email, @RequestParam("password") String password, 
            @RequestParam("fotoPerfil") MultipartFile fotoPerfil, ModelMap modelo) throws Exception {
        try {
            usuarioServicio.crearAdmin(nombre, email, password, fotoPerfil);
            modelo.put("exito", "Admin creadao con exito.");
            return "login.html";
        } catch (Exception e) {
            modelo.put("error", e.getMessage());
            modelo.put("nombre", nombre);
            modelo.put("email", email);
            modelo.put("password", password);
            modelo.put("fotoPerfil", fotoPerfil);
            return "registroAdmin.html";
        }
    }
    
}
