package com.transporte.logistica.service;

import com.transporte.logistica.model.dto.PlanEntregaMaritimoRequest;
import com.transporte.logistica.model.dto.PlanEntregaMaritimoResponse;
import java.util.List;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

/**
 *
 * @author Hector Leon
 */
public interface PlanEntregaMaritimoService {

  public Mono<ResponseEntity<String>> agregarPlanEntrega(Mono<PlanEntregaMaritimoRequest> planEntregaRequest);

  public Mono<PlanEntregaMaritimoRequest> validarPlanEntrega(PlanEntregaMaritimoRequest planEntregaRequest);

  public Mono<ResponseEntity<String>> guardarPlanEntrega(PlanEntregaMaritimoRequest planEntregaRequest);

  public Mono<ResponseEntity<List<PlanEntregaMaritimoResponse>>> listarTodos();
}
