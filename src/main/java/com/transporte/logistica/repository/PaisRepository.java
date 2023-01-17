package com.transporte.logistica.repository;

import com.transporte.logistica.model.entities.Pais;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Hector Leon
 */
public interface PaisRepository extends JpaRepository<Pais, Integer> {
  
}
