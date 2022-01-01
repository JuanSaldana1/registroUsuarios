package com.juansaldana.registroUsuarios.services;

import com.juansaldana.registroUsuarios.dto.UsuarioRegistroDTO;
import com.juansaldana.registroUsuarios.entity.Usuario;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsuarioService extends UserDetailsService {
    Usuario saveUser(UsuarioRegistroDTO registroDTO);
}