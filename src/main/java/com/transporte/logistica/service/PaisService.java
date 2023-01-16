package com.transporte.logistica.service;

import com.transporte.logistica.model.dto.PaisResponse;
import java.util.List;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

/**
 *
 * @author Hector Leon
 */
public interface PaisService {

  public Mono<ResponseEntity<List<PaisResponse>>> listarTodos();
}
