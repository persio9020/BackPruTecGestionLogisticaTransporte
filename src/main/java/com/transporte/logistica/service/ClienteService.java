package com.transporte.logistica.service;

import com.transporte.logistica.model.dto.ClienteRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 *
 * @author Hector Leon
 */
public interface ClienteService {

  public Mono<ServerResponse> agregarCliente(Mono<ClienteRequest> clienteRequest);
  
  public Mono<ClienteRequest> validarCliente(ClienteRequest productoRequest);
  
  public Mono<ServerResponse> guardarCliente(ClienteRequest productoRequest);
  
  public Mono<ServerResponse> listarTodos();
}
