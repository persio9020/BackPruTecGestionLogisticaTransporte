package com.transporte.logistica.controller;

import com.transporte.logistica.model.dto.ClienteRequest;
import com.transporte.logistica.model.dto.ClienteResponse;
import com.transporte.logistica.model.dto.util.OptionResponse;
import com.transporte.logistica.service.ClienteService;
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
@RequestMapping("/cliente")
public class ClienteController {

  @Autowired
  private ClienteService clienteService;
  
  @PostMapping("/agregar")
  @PreAuthorize("hasRole('USER')")
  public Mono<ResponseEntity<String>> agregarCliente(@RequestBody ClienteRequest request) {
    Mono<ClienteRequest> cliente = Mono.just(request);
    return clienteService.agregarCliente(cliente);
  }
  
  @GetMapping("/listar")
  @PreAuthorize("hasRole('USER')")
  public Mono<ResponseEntity<List<ClienteResponse>>> listar() {
    return clienteService.listarTodos();
  }
  
  @GetMapping("/listar-options")
  @PreAuthorize("hasRole('USER')")
  public Mono<ResponseEntity<List<OptionResponse>>> listarOptions() {
    return clienteService.listarOptions();
  }
}
