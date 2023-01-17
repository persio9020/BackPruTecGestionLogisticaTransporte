package com.transporte.logistica.service;

import com.transporte.logistica.model.dto.BodegaRequest;
import com.transporte.logistica.model.dto.BodegaResponse;
import com.transporte.logistica.model.dto.util.OptionResponse;
import java.util.List;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

/**
 *
 * @author Hector Leon
 */
public interface BodegaService {

  public Mono<ResponseEntity<String>> agregarBodega(Mono<BodegaRequest> bodegaRequest);

  public Mono<BodegaRequest> validarBodega(BodegaRequest bodegaRequest);

  public Mono<ResponseEntity<String>> guardarBodega(BodegaRequest bodegaRequest);

  public Mono<ResponseEntity<List<BodegaResponse>>> listarTodos();

  public Mono<ResponseEntity<List<OptionResponse>>> listarOptions();
}
