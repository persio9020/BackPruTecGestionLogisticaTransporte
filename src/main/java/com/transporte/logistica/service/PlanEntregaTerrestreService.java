package com.transporte.logistica.service;

import com.transporte.logistica.model.dto.PlanEntregaTerrestreRequest;
import com.transporte.logistica.model.dto.PlanEntregaTerrestreResponse;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 *
 * @author Hector Leon
 */
public interface PlanEntregaTerrestreService {

  public Mono<ResponseEntity<String>> agregarPlanEntrega(Mono<PlanEntregaTerrestreRequest> planEntregaRequest);

  public Mono<PlanEntregaTerrestreRequest> validarPlanEntrega(PlanEntregaTerrestreRequest planEntregaRequest);

  public Mono<ResponseEntity<String>> guardarPlanEntrega(PlanEntregaTerrestreRequest planEntregaRequest);

  public Mono<ResponseEntity<List<PlanEntregaTerrestreResponse>>> listarTodos();
}
