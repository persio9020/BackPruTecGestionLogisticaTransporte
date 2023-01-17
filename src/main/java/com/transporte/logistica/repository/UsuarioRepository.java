package com.transporte.logistica.repository;

import com.transporte.logistica.model.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author persi
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

  Usuario findByNombre(String nombre);

  @Query(value = "insert into usuario_rol (usuario_id, rol_id ) VALUES (?1, ?2)", 
  nativeQuery = true)
  void insertUsurRol(Long idUsuario, Short idRol);
}
