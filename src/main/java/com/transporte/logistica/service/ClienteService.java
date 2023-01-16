package com.transporte.logistica.service;

import com.transporte.logistica.model.dto.ClienteRequest;
import com.transporte.logistica.model.dto.ClienteResponse;
import java.util.List;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

/**
 *
 * @author Hector Leon
 */
public interface ClienteService {

  public Mono<ResponseEntity<String>> agregarCliente(Mono<ClienteRequest> clienteRequest);
  
  public Mono<ClienteRequest> validarCliente(ClienteRequest clienteRequest);
  
  public Mono<ResponseEntity<String>> guardarCliente(ClienteRequest clienteRequest);
  
  public Mono<ResponseEntity<List<ClienteResponse>>> listarTodos();
}
