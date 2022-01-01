package com.juansaldana.registroUsuarios.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegistroController {
    @GetMapping("/login")
    public String iniciarSesion() {
        return "login";
    }

    @GetMapping("/")
    public String verPaginaInicio() {
        return "index";
    }
}