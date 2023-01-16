package com.transporte.logistica.repository;

import com.transporte.logistica.model.entities.Bodega;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Hector Leon
 */
public interface BodegaRepository extends JpaRepository<Bodega, Long> {
  
}
