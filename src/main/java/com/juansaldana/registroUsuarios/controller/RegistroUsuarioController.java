package com.juansaldana.registroUsuarios.controller;

import com.juansaldana.registroUsuarios.dto.UsuarioRegistroDTO;
import com.juansaldana.registroUsuarios.services.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registro")
public class RegistroUsuarioController {
    private final UsuarioService usuarioService;

    public RegistroUsuarioController(UsuarioService userService) {
        super();
        this.usuarioService = userService;
    }

    @ModelAttribute("usuario")
    public UsuarioRegistroDTO retornarNuevoUsuarioRegistroDTO() {
        return new UsuarioRegistroDTO();
    }

    @GetMapping
    public String mostrarFormularioRegistro() {
        return "registro";
    }

    //TODO: Añadir petición POST
    @PostMapping
    public String registrarNuevaCuentaUsuario(@ModelAttribute("usuario") UsuarioRegistroDTO registroDTO) {
        usuarioService.saveUser(registroDTO);
        return "redirect:/registro?exito";
    }
}