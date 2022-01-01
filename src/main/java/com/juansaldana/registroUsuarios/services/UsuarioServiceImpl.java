package com.juansaldana.registroUsuarios.services;

import com.juansaldana.registroUsuarios.dto.UsuarioRegistroDTO;
import com.juansaldana.registroUsuarios.entity.Rol;
import com.juansaldana.registroUsuarios.entity.Usuario;
import com.juansaldana.registroUsuarios.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Service
public class UsuarioServiceImpl implements UsuarioService {
    private UsuarioRepository usuarioRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Usuario saveUser(UsuarioRegistroDTO registroDTO) {
        Usuario usuario = new Usuario(registroDTO.getName(), registroDTO.getSurname(), registroDTO.getEmail(), passwordEncoder.encode(registroDTO.getPassword()), List.of(new Rol("Admin")));
        return usuarioRepository.save(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario == null) {
            throw new UsernameNotFoundException("usuario o contraseña inválidos");
        }
        return new User(usuario.getEmail(), usuario.getPassword(), mapearAutoridadesARoles(usuario.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapearAutoridadesARoles(Collection<Rol> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}