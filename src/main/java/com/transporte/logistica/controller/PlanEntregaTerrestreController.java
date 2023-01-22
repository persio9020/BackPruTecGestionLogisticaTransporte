package com.transporte.logistica.controller;

import com.transporte.logistica.model.dto.PlanEntregaTerrestreRequest;
import com.transporte.logistica.model.dto.PlanEntregaTerrestreResponse;
import com.transporte.logistica.service.PlanEntregaTerrestreService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 *
 * @author persi
 */
@RestController
@RequestMapping("${sistema.context-path.api}/plan-entrega-terrestre")
public class PlanEntregaTerrestreController {

  @Autowired
  private PlanEntregaTerrestreService planEntregaTerrestreService;

  @PostMapping("/agregar")
  @PreAuthorize("hasRole('USER')")
  public Mono<ResponseEntity<String>> agregar(@RequestBody PlanEntregaTerrestreRequest request) {
    Mono<PlanEntregaTerrestreRequest> planEntrega = Mono.just(request);
    return planEntregaTerrestreService.agregarPlanEntrega(planEntrega);
  }

  @GetMapping("/listar")
  @PreAuthorize("hasRole('USER')")
  public Mono<ResponseEntity<List<PlanEntregaTerrestreResponse>>> listar() {
    return planEntregaTerrestreService.listarTodos();
  }
}
