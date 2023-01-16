package com.transporte.logistica.repository;

import com.transporte.logistica.model.entities.PlanEntrega;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Hector Leon
 */
public interface PlanEntregaRepository extends JpaRepository<PlanEntrega, Long> {
  
}
