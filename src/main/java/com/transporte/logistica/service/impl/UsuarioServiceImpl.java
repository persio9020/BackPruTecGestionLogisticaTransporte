package com.transporte.logistica.service.impl;

import com.transporte.logistica.config.Role;
import com.transporte.logistica.config.User;
import com.transporte.logistica.model.entities.Rol;
import com.transporte.logistica.model.entities.Usuario;
import com.transporte.logistica.repository.UsuarioRepository;
import com.transporte.logistica.service.UsuarioService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 *
 * @author Hector Leon
 */
@Service
public class UsuarioServiceImpl implements UsuarioService {

  private UsuarioRepository usuarioRepository;
  
  @Autowired
  public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
    this.usuarioRepository = usuarioRepository;
  }

  @Override
  public Mono<User> findByUsername(String username) {
    Usuario usuario = this.usuarioRepository.findByNombre(username);
    User userJWT = new User();
    userJWT.setUsername(usuario.getNombre());
    userJWT.setPassword(usuario.getContrasenia());
    List<Role> listRoles = new ArrayList<>();
    for(Rol rol: usuario.getRolList()){
      if(rol.getNombre().equals(Role.ROLE_ADMIN.name())){
        listRoles.add(Role.ROLE_ADMIN);
      }
      if(rol.getNombre().equals(Role.ROLE_USER.name())){
        listRoles.add(Role.ROLE_USER);
      }
    }
    userJWT.setRoles(listRoles);
    return Mono.just(userJWT);
  }

}
