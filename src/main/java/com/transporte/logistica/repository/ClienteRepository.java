package com.transporte.logistica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.transporte.logistica.model.entities.Cliente;
/**
 *
 * @author persi
 */

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
  
}
