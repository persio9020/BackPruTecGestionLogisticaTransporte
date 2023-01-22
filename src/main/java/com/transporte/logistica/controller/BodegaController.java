package com.transporte.logistica.controller;

import com.transporte.logistica.model.dto.BodegaRequest;
import com.transporte.logistica.model.dto.BodegaResponse;
import com.transporte.logistica.model.dto.util.OptionResponse;
import com.transporte.logistica.service.BodegaService;
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
 * @author Hector Leon
 */
@RestController
@RequestMapping("${sistema.context-path.api}/bodega")
public class BodegaController {

  @Autowired
  private BodegaService bodegaService;

  @PostMapping("/agregar")
  @PreAuthorize("hasRole('USER')")
  public Mono<ResponseEntity<String>> agregarBodega(@RequestBody BodegaRequest request) {
    Mono<BodegaRequest> bodega = Mono.just(request);
    return bodegaService.agregarBodega(bodega);
  }

  @GetMapping("/listar")
  @PreAuthorize("hasRole('USER')")
  public Mono<ResponseEntity<List<BodegaResponse>>> listar() {
    return bodegaService.listarTodos();
  }

  @GetMapping("/listar-options")
  @PreAuthorize("hasRole('USER')")
  public Mono<ResponseEntity<List<OptionResponse>>> listarOptions() {
    return bodegaService.listarOptions();
  }
}
