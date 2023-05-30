package com.equipocompleto.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PortalControlador {
    
    @GetMapping("/index")
    public String index(ModelMap modelo) {
        return "index.html";
    }
    
    @GetMapping("/login")
    public String login() {
        return "login.html";
    }
    
}
