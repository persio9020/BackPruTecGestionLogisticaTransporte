package com.transporte.logistica.repository;

import com.transporte.logistica.model.entities.PlanEntrega;
import com.transporte.logistica.model.entities.TipoLogisticaTransporte;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Hector Leon
 */
public interface PlanEntregaRepository extends JpaRepository<PlanEntrega, Long> {
  
  List<PlanEntrega> findPlanEntregaBytipoLogisticaTransporteId(TipoLogisticaTransporte tipoLogisticaTransporteId);
  
  Integer countByNumeroGuia(String numeroGuia);
}
