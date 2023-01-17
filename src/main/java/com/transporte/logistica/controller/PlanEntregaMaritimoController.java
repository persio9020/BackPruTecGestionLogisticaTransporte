package com.transporte.logistica.controller;

import com.transporte.logistica.model.dto.PlanEntregaMaritimoRequest;
import com.transporte.logistica.model.dto.PlanEntregaMaritimoResponse;
import com.transporte.logistica.model.dto.PlanEntregaTerrestreRequest;
import com.transporte.logistica.model.dto.PlanEntregaTerrestreResponse;
import com.transporte.logistica.service.PlanEntregaMaritimoService;
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
@RequestMapping("/plan-entrega-maritimo")
public class PlanEntregaMaritimoController {
    @Autowired
  private PlanEntregaMaritimoService planEntregaMaritimoService;
  
  @PostMapping("/agregar")
  @PreAuthorize("hasRole('USER')")
  public Mono<ResponseEntity<String>> agregar(@RequestBody PlanEntregaMaritimoRequest request) {
    Mono<PlanEntregaMaritimoRequest> planEntrega = Mono.just(request);
    return planEntregaMaritimoService.agregarPlanEntrega(planEntrega);
  }
  
  @GetMapping("/listar")
  @PreAuthorize("hasRole('USER')")
  public Mono<ResponseEntity<List<PlanEntregaMaritimoResponse>>> listar() {
    return planEntregaMaritimoService.listarTodos();
  }
}
