package com.transporte.logistica.repository;

import com.transporte.logistica.model.entities.Puerto;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Hector Leon
 */
public interface PuertoRepository extends JpaRepository<Puerto, Long>  {
  
}
