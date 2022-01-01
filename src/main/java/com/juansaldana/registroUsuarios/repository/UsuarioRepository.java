package com.juansaldana.registroUsuarios.repository;

import com.juansaldana.registroUsuarios.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    public Usuario findByEmail(String email);
}