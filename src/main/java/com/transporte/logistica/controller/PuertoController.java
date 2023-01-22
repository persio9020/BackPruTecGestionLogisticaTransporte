package com.transporte.logistica.controller;

import com.transporte.logistica.model.dto.PuertoRequest;
import com.transporte.logistica.model.dto.PuertoResponse;
import com.transporte.logistica.model.dto.util.OptionResponse;
import com.transporte.logistica.service.PuertoService;
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
@RequestMapping("${sistema.context-path.api}/puerto")
public class PuertoController {

  @Autowired
  private PuertoService puertoService;

  @PostMapping("/agregar")
  @PreAuthorize("hasRole('USER')")
  public Mono<ResponseEntity<String>> agregar(@RequestBody PuertoRequest request) {
    Mono<PuertoRequest> puerto = Mono.just(request);
    return puertoService.agregarPuerto(puerto);
  }

  @GetMapping("/listar")
  @PreAuthorize("hasRole('USER')")
  public Mono<ResponseEntity<List<PuertoResponse>>> listar() {
    return puertoService.listarTodos();
  }

  @GetMapping("/listar-options")
  @PreAuthorize("hasRole('USER')")
  public Mono<ResponseEntity<List<OptionResponse>>> listarOptions() {
    return puertoService.listarOptions();
  }
}
