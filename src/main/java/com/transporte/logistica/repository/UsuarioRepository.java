package com.transporte.logistica.repository;

import com.transporte.logistica.model.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author persi
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

  Usuario findByNombre(String nombre);
}
