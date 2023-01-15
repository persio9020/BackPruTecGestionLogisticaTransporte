package com.transporte.logistica.handler;

import com.transporte.logistica.model.dto.ClienteRequest;
import com.transporte.logistica.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 *
 * @author Hector Leon
 */
@Component
public class ClienteHandler {

  @Autowired
  private ClienteService clienteService;

  public Mono<ServerResponse> agregarCliente(ServerRequest request) {
    Mono<ClienteRequest> cliente = request.bodyToMono(ClienteRequest.class);
    return clienteService.agregarCliente(cliente);
  }
  
  public Mono<ServerResponse> listar(ServerRequest request) {
    return clienteService.listarTodos();
  }
}
