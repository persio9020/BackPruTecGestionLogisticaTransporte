package com.transporte.logistica.repository;

import com.transporte.logistica.model.entities.TipoProducto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoProductoRepository extends JpaRepository<TipoProducto, Long> {
  
}
