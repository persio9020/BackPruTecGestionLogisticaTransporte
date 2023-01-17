package com.transporte.logistica.service.impl;

import com.transporte.logistica.config.PBKDF2Encoder;
import com.transporte.logistica.model.entities.Rol;
import com.transporte.logistica.model.entities.Usuario;
import com.transporte.logistica.repository.UsuarioRepository;
import com.transporte.logistica.service.RegistreseService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author persi
 */
@Service
public class RegistreseServiceImpl implements RegistreseService {

  @Autowired
  UsuarioRepository usuarioRepository;
  @Autowired
  private PBKDF2Encoder passwordEncoder;

  @Override
  public void guardar(Map<String, Object> datos) {
    Usuario u = new Usuario();
    u.setNombre((String) datos.get("nombreUsuario"));
    u.setContrasenia(passwordEncoder.encode((String) datos.get("contrasenia")));
    u.setCorreo((String) datos.get("correo"));
    usuarioRepository.save(u);
    usuarioRepository.insertUsurRol(u.getId(), Short.valueOf("2"));
    
  }

}
