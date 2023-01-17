package com.transporte.logistica.service;

import com.transporte.logistica.model.dto.PuertoRequest;
import com.transporte.logistica.model.dto.PuertoResponse;
import com.transporte.logistica.model.dto.util.OptionResponse;
import java.util.List;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

/**
 *
 * @author Hector Leon
 */
public interface PuertoService {

  public Mono<ResponseEntity<String>>agregarPuerto(Mono<PuertoRequest> puertoRequest);

  public Mono<PuertoRequest> validarPuerto(PuertoRequest puertoRequest);

  public Mono<ResponseEntity<String>> guardarPuerto(PuertoRequest puertoRequest);

  public Mono<ResponseEntity<List<PuertoResponse>>> listarTodos();
  
  public Mono<ResponseEntity<List<OptionResponse>>> listarOptions();
}
